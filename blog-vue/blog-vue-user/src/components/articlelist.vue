<script setup>
import { useRoute, useRouter } from 'vue-router'; // Vue Router 4 API
import { ref, onMounted, watch } from 'vue';
import { useArticleStore } from '../stores/articleStore'; // 引入 Pinia Store

// 初始化 Pinia store
const articleStore = useArticleStore();

// 获取当前路由对象
const route = useRoute();
const router = useRouter();

// 处理路由变化时的操作
const routeChange = () => {
    articleStore.queryParams.categoryId = route.query.classId ? parseInt(route.query.classId) : 0;
    articleStore.articleListData = [];  // 清空列表，重新加载
    articleStore.getList();  // 获取新的文章列表
};

// 组件挂载时，初始化获取文章列表
onMounted(() => {
    routeChange();
});

// 监听路由路径变化
watch(() => route.path, routeChange);
</script>
<template>
    <el-row class="sharelistBox">
        <el-col :span="24" class="s-item tcommonBox" v-for="(item, index) in articleStore.articleListData"
            :key="'article' + index">
            <span class="s-round-date">
                <span class="month" v-html="showInitDate(item.createTime, 'month') + '月'"></span>
                <span class="day" v-html="showInitDate(item.createTime, 'date')"></span>
            </span>
            <header>
                <h1>
                    <a :href="'#/DetailArticle?aid=' + item.id" target="_blank">{{ item.title }}</a>
                </h1>
                <h2>
                    <el-icon><User /></el-icon>发表于
                    <el-icon><Clock /></el-icon><span>
                        <span v-html="showInitDate(item.createTime, 'all')"></span> •
                    </span>
                    <el-icon><View /></el-icon>{{ item.viewCount }} 次围观 •
                </h2>
                <div class="ui label">
                    <a :href="'#/Share?classId=' + item.class_id">{{ item.categoryName }}</a>
                </div>
            </header>
            <div class="article-content">
                <p style="text-indent:2em;">{{ item.summary }}</p>
                <p style="max-height:300px;overflow:hidden;text-align:center;">
                    <img :src="item.thumbnail" alt="" class="maxW">
                </p>
            </div>
            <div class="viewdetail">
                <a class="tcolors-bg" :href="'#/DetailArticle?aid=' + item.id" target="_blank">
                    阅读全文>>
                </a>
            </div>
        </el-col>

        <el-col class="viewmore">
            <a v-show="articleStore.hasMore" class="tcolors-bg" href="javascript:void(0);"
                @click="articleStore.getList">
                点击加载更多
            </a>
            <a v-show="!articleStore.hasMore" class="tcolors-bg" href="javascript:void(0);">
                暂无更多数据
            </a>
        </el-col>
    </el-row>
</template>
<style>
/*分享标题*/
.shareTitle {
    margin-bottom: 40px;
    position: relative;
    border-radius: 5px;
    background: #fff;
    padding: 15px;
}

.shareclassTwo {
    width: 100%;
}

.shareclassTwo li {
    display: inline-block;
}

.shareclassTwo li a {
    display: inline-block;
    padding: 3px 7px;
    margin: 5px 10px;
    color: #fff;
    border-radius: 4px;
    background: #64609E;
    border: 1px solid #64609E;
    transition: transform 0.2s linear;
    -webkit-transition: transform 0.2s linear;
}

.shareclassTwo li a:hover {
    transform: translate(0, -3px);
    -webkit-transform: translate(0, -3px);
}

.shareclassTwo li a.active {
    background: #fff;
    color: #64609E;

}

/*文章列表*/
.sharelistBox {
    transition: all 0.5s ease-out;
    font-size: 15px;
}


/*.sharelistBox .viewmore a:hover,.s-item .viewdetail a:hover{
        background: #48456C;
    }*/
</style>