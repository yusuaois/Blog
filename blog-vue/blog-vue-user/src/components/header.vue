<template>
    <div class="">
        <div class="headBack">
            <el-row class="container">
                <el-col :span="24">
                    <!-- pc端导航 -->
                    <div class="headBox">
                        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal"
                            @select="handleSelect" :router="true">
                            <el-menu-item index="/Home"><el-icon>
                                    <HomeFilled />
                                </el-icon>首页</el-menu-item>
                            <el-sub-menu index="/Share">
                                <template #title><el-icon color="#fff">
                                        <Box />
                                    </el-icon>分类</template>
                                <!-- TODO 循环实现-->
                                <el-menu-item v-for="(item, index) in classListObj" :key="'class1' + index"
                                    :index="'/Share?classId=' + item.id">{{
                                        item.name }}</el-menu-item>
                            </el-sub-menu>
                            <el-menu-item index="/Reward"><el-icon>
                                    <Money />
                                </el-icon>赞赏</el-menu-item>
                            <el-menu-item index="/Friendslink"><el-icon>
                                    <Promotion />
                                </el-icon>友链</el-menu-item>

                            <div class="userInfo">
                                <!-- TODO 登陆注册 -->
                                <div v-show="!haslogin" class="nologin">
                                    <a href="javascript:void(0);" @click="logoinFun(1)">登录&nbsp;</a>|<a
                                        href="javascript:void(0);" @click="logoinFun(0)">&nbsp;注册</a>
                                </div>
                                <div v-show="haslogin" class="haslogin">
                                    <el-icon>
                                        <User />
                                    </el-icon>
                                    <ul class="haslogin-info">
                                        <li>
                                            <a href="#/UserInfo">个人中心</a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0);" @click="userlogout">退出登录</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </el-menu>
                    </div>
                </el-col>
            </el-row>
        </div>
        <!-- TODO 大头像盒 -->
        <div class="headImgBox">
            <!-- <div class="headImgBox"
			:style="{ backgroundImage: this.$store.state.themeObj.top_image ? 'url(' + this.$store.state.themeObj.top_image + ')' : 'url(static/img/headbg05.jpg)' }"> -->
            <div class="scene">
                <div><span id="luke"></span></div>
            </div>
            <div class="h-information">

                <img src="../../static/img/tou.jpg" alt="">
                <!-- TODO -->
                <!-- <img :src="this.$store.state.themeObj.head_portrait ? this.$store.state.themeObj.head_portrait : 'static/img/tou.png'"
					alt=""> -->

                <h2 class="h-description">
                    <!-- TODO -->
                    <!-- {{ this.$store.state.themeObj.autograph ? this.$store.state.themeObj.autograph : "三更灯火五更鸡，正是男儿读书时"
					}} -->
                </h2>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive, watch, onMounted, nextTick } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useStore } from '@/stores/useStore' // 导入 Pinia store
// import { logout } from '../api/user'
// import { removeToken } from '../utils/auth'
// import { getCategoryList } from '../api/category'

// 引用 Pinia store
const store = useStore()

// 获取路由对象
const route = useRoute()
const router = useRouter()

// 获取分类列表
const getCategoryListData = () => {
    getCategoryList().then((response) => {
        store.setCategoryList(response) // 更新 Pinia store
    })
}

// 用户登录或注册跳转
const logoinFun = (msg) => {
    localStorage.setItem('logUrl', route.fullPath)
    if (msg === 0) {
        router.push({ path: '/Login?login=0' })
    } else {
        router.push({ path: '/Login?login=1' })
    }
}

// 用户退出登录
const userlogout = () => {
    window.$confirm('是否确认退出?', '退出提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
    }).then(() => {
        logout().then(() => {
            removeToken()
            localStorage.removeItem('userInfo')
            store.setLoginStatus(false) // 更新登录状态
            store.setUserInfo(null) // 清空用户信息
            window.location.reload()
            window.$message({
                type: 'success',
                message: '退出成功!',
            })
            if (route.path === '/UserInfo') {
                router.push({ path: '/' })
            }
        })
    })
}

// 路由变化处理
const routeChange = () => {
    store.setActiveIndex(route.path === '/' ? '/Home' : route.path)

    // 获取存储的用户信息
    const savedUserInfo = localStorage.getItem('userInfo')
    if (savedUserInfo) {
        store.setLoginStatus(true)
        store.setUserInfo(JSON.parse(savedUserInfo))
    } else {
        store.setLoginStatus(false)
    }

    getCategoryListData()

    if ((route.name === 'Share' || route.name === 'Home') && window.$store.state.keywords) {
        store.setInput(window.$store.state.keywords) // 设置输入框关键词
    } else {
        store.setInput('')
        window.$store.state.keywords = ''
    }
}

// 打字机效果
onMounted(() => {
    nextTick(() => {
        const timer = setTimeout(() => {
            Typeit(store.themeObj.user_start, '#luke')
            clearTimeout(timer)
        }, 500)
    })
})

// 监听路由变化
watch(() => route.path, routeChange)
</script>


<style>
/*********头部导航栏********/

/*头部导航栏盒子*/

.headBack {
    width: 100%;
    background: rgba(40, 42, 44, 0.6);
    /*margin-bottom:30px;*/
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .12), 0 0 6px 0 rgba(0, 0, 0, .04);
    position: fixed;
    left: 0;
    top: 0;
    right: 0;
    z-index: 100;
}

.el-menu--horizontal {
    --el-menu-horizontal-height: 38px;
}

.headBox li.is-active {
    /*background: #48456C;*/
    background: rgba(73, 69, 107, 0.7);
}

.el-menu--horizontal>.el-sub-menu.is-active .el-sub-menu__title {
    border-bottom: none !important;
}

.headBox .el-menu {
    background: transparent;
    border-bottom: none !important;
}

.headBox .el-menu-demo li.el-menu-item .headBox .el-sub-menu .el-sub-menu__title {
    height: 38px;
    line-height: 38px;
    border-bottom: none !important;
}

.headBox .el-sub-menu li.el-menu-item {
    height: 38px;
    line-height: 38px;
}

/* .headBox li .el-icon {
        vertical-align: baseline;
} */

.headBox ul li.el-menu-item,
.headBox ul li.el-menu-item.is-active,
.headBox ul li.el-menu-item:hover,
.headBox .el-sub-menu div.el-sub-menu__title,
.headBox .el-sub-menu__title i.el-sub-menu__icon-arrow {
    color: #fff;
}


.headBox .el-menu--horizontal .el-sub-menu .el-menu {
    top: 38px;
    border: none;
    padding: 0;
}

.headBox>ul li.el-menu-item:hover,
.headBox>ul li.el-sub-menu:hover .el-sub-menu__title {
    background: #48456C;
    border-bottom: none;
}

.headBox>ul .el-sub-menu .el-menu,
.headBox>ul .el-sub-menu .el-menu .el-menu-item {
    background: #48456C;
}

.headBox>ul .el-sub-menu .el-menu .el-menu-item {
    min-width: 0;
}

.headBox>ul .el-sub-menu .el-menu .el-menu-item:hover {
    background: #64609E;
}

/*pc搜索框*/

.headBox .pcsearchbox {
    padding: 0;
    max-width: 170px;
    /*min-width: 30px;*/
    height: 100%;
    line-height: 38px;
    position: absolute;
    top: 0;
    right: 0;
    cursor: pointer;
}

.headBox .pcsearchbox:hover .pcsearchinput {
    opacity: 1;
    /*transform: scaleX(1);*/
    visibility: visible;
}

.headBox .pcsearchbox i.pcsearchicon {
    color: #fff;
    padding-left: 10px;
}

.headBox .pcsearchbox .pcsearchinput {
    width: 180px;
    padding: 10px 20px 10px 20px;
    background: rgba(40, 42, 44, 0.6);
    border-radius: 0 0 2px 2px;
    position: absolute;
    right: 0;
    top: 38px;
    opacity: 0;
    visibility: hidden;
    /*transform: scaleX(0);*/
    transform-origin: right;
    transition: all 0.3s ease-out;
}

.headBox .pcsearchbox .hasSearched {
    opacity: 1;
    /*transform: scaleX(1);*/
    visibility: visible;
}

.headBox .pcsearchbox .el-input {
    width: 100%;
}

.headBox .el-input__inner {
    height: 30px;
    border: none;
    background: #fff;
    /*border: 1px solid #333;*/
    border-radius: 2px;
    padding-right: 10px;
}

.headBox .userInfo {
    height: 100%;
    line-height: 38px;
    position: absolute;
    right: 30px;
    top: 0;
    color: #fff;
}

.headBox .userInfo a {
    color: #fff;
    font-size: 13px;
    transition: all 0.2s ease-out;
}

.headBox .userInfo a:hover {
    color: #48456C;
}

.headBox .nologin {
    text-align: right;
}

.headBox .haslogin {
    text-align: right;
    position: relative;
    min-width: 80px;
    cursor: pointer;
}

.headBox .haslogin:hover ul {
    visibility: visible;
    opacity: 1;
}

.headBox .haslogin ul {
    background: rgba(40, 42, 44, 0.6);
    padding: 5px 10px;
    position: absolute;
    right: 0;
    visibility: hidden;
    opacity: 0;
    transition: all 0.3s ease-out;
}

.headBox .haslogin ul li {
    border-bottom: 1px solid #48456C;
}

.headBox .haslogin ul li:last-child {
    border-bottom: 1px solid transparent;
}

/*******移动端*******/

.mobileBox {
    position: relative;
    height: 38px;
    line-height: 38px;
    color: #fff;
}

.hideMenu {
    position: relative;
    width: 100%;
    height: 100%;
    line-height: 38px;
}

.hideMenu ul.mlistmenu {
    width: 100%;
    position: absolute;
    left: 0;
    top: 100%;
    box-sizing: border-box;
    z-index: 999;
    box-shadow: 0 2px 6px 0 rgba(0, 0, 0, .12), 0 0 8px 0 rgba(0, 0, 0, .04);
    background: #48456C;
    color: #fff;
    border-right: none;
}

.hideMenu .el-sub-menu .el-menu {
    background: #64609E;
}

.hideMenu .el-menu-item,
.hideMenu .el-sub-menu__title {
    color: #fff;
}

.hideMenu>i {
    position: absolute;
    left: 10px;
    top: 12px;
    width: 30px;
    height: 30px;
    font-size: 16px;
    color: #fff;
    cursor: pointer;
}

.hideMenu .el-menu-item,
.el-sub-menu__title {
    height: 40px;
    line-height: 40px;
}

.mobileBox .searchBox {
    padding-left: 40px;
    width: 100%;
    box-sizing: border-box;
}

.mobileBox .searchBox .el-input__inner {
    display: block;
    border-radius: 2px;
    border: none;
    height: 25px;
}

.hideMenu ul.mlistmenu.pshow {
    display: block;
}

.hideMenu ul.mlistmenu .el-sub-menu__icon-arrow,
.mobileBox li.el-menu-item a {
    color: #fff;
}

.hideMenu>ul li.el-menu-item:hover,
.hideMenu>ul li.el-menu-item.is-active {
    background: #48576a;
}



/*头部背景图*/

.headImgBox {
    height: 650px;
    position: relative;
    width: 100%;
    background-size: cover;
    background-position: center 50%;
    background-repeat: no-repeat;
    margin-bottom: 90px;
}

.h-information {
    text-align: center;
    width: 70%;
    margin: auto;
    position: relative;
    top: 480px;
    padding: 40px 0;
    font-size: 16px;
    opacity: 0.98;
    background: rgba(230, 244, 249, 0.8);
    border-radius: 5px;
    z-index: 1;
    animation: b 1s ease-out;
    -webkit-animation: b 1s ease-out;
}

@-webkit-keyframes b {
    0% {
        -webkit-transform: translateY(90px);
        transform: translateY(90px);
    }

    80% {
        -webkit-transform: translateY(5px);
        transform: translateY(5px)
    }

    90% {
        -webkit-transform: translateY(-5px);
        transform: translateY(-5px)
    }

    to {
        -webkit-transform: translateY(0);
        transform: translateY(0)
    }
}

@keyframes b {
    0% {
        -webkit-transform: translateY(90px);
        transform: translateY(90px);
    }

    80% {
        -webkit-transform: translateY(5px);
        transform: translateY(5px)
    }

    90% {
        -webkit-transform: translateY(-5px);
        transform: translateY(-5px)
    }

    to {
        -webkit-transform: translateY(0);
        transform: translateY(0)
    }
}

.h-information img {
    width: 100px;
    height: 100px;
    border-radius: 100%;
    transition: all .4s ease-in-out;
    -webkit-transition: all .4s ease-in-out;
    object-fit: cover;
}

.h-information img:hover {
    transform: rotate(360deg);
    -webkit-transform: rotate(360deg);
}

.h-information h2 {
    margin-top: 20px;
    font-size: 18px;
    font-weight: 700;
    /*font-family: 'Sigmar One';*/
}

.h-information h2 a {
    background: linear-gradient(to right, #DF2050, #48456D);
    -webkit-background-clip: text;
    color: transparent;
}

.headImgBox .scene {
    width: 100%;
    /*height:300px;*/
    text-align: center;
    font-size: 100px;
    font-weight: 200;
    color: #fff;
    position: absolute;
    left: 0;
    top: 160px;
    font-family: 'Sigmar One', Arial;
    text-shadow: 0 2px 2px #47456d;

}

.headImgBox .scene span {
    transform: matrix(1, 0, 0, 1, 0, 0);
    -webkit-transform: matrix(1, 0, 0, 1, 0, 0);
    text-shadow: 1px 1px 0 #ff3f1a, -1px -1px 0 #00a7e0;
}

.saying:after {
    content: "|";
    font-family: Arial, sans-serif;
    font-size: 1em;
    /*line-height: 0;*/
    display: inline-block;
    vertical-align: baseline;
    opacity: 1;
    text-shadow: 1px 1px 0 #ff3f1a, -1px -1px 0 #00a7e0;
    animation: caret 500ms infinite;
}

@keyframes caret {

    0%,
    100% {
        opacity: 1;
    }

    50% {
        opacity: 0;
    }
}
</style>