<template>
  <div class="dashboard-container">
    <div class="dashboard-text">博客管理系统</div>
    <div>个人博客管理系统</div>
    <div>当前博客在线人数：{{ total }} 人</div>
    <el-divider />

    <div style="font-size: 36px; font-weight: larger; color: #409eff">
      博文一览：
    </div>

    <el-row class="sharelistBox">
      <el-col
        :span="24"
        class="s-item tcommonBox"
        v-for="(item, index) in articleList"
        :key="'article' + index"
      >
        <header>
          <h1>
            <a :href="'#/DetailArticle?aid=' + item.id" target="_blank">
              {{ item.title }}
            </a>
          </h1>
          <h2>
            <i class="fa fa-fw fa-user"></i>发表于
            <i class="fa fa-fw fa-clock-o"></i
            ><span v-html="showInitDate(item.createTime, 'all')">{{
              showInitDate(item.createTime, "all")
            }}</span>
            • <i class="fa fa-fw fa-eye"></i>{{ item.viewCount }} 次围观 •
            {{ item.categoryName }}
          </h2>
        </header>
        <div class="article-content">
          <p style="text-indent: 2em">
            {{ item.summary }}
          </p>
          <p style="max-height: 300px; overflow: hidden; text-align: center">
            <img :src="item.thumbnail" alt="" class="maxW" />
          </p>
        </div>
        <el-divider />
      </el-col>
      <el-col class="viewmore">
        <a
          v-show="hasMore"
          class="tcolors-bg"
          href="javascript:void(0);"
          @click="addMoreFun"
          >点击加载更多</a
        >
        <a v-show="!hasMore" class="tcolors-bg" href="javascript:void(0);"
          >暂无更多数据</a
        >
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { aliveUser } from "@/api/system/user";
import { initDate } from "@/utils/server.js";
import { articleList } from "@/api/content/article";
export default {
  name: "Dashboard",
  data() {
    return {
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryId: 0,
      },
      articleList: [],
      hasMore: true,
    };
  },
  computed: {
    ...mapGetters(["name"]),
  },
  created() {
    this.getAliveUser();
    this.routeChange();
  },
  methods: {
    getAliveUser() {
      aliveUser().then((response) => {
        this.total = response;
      });
    },
    showInitDate: function (oldDate, full) {
      return initDate(oldDate, full);
    },
    getList() {
      articleList(this.queryParams).then((response) => {
        this.articleList = this.articleList.concat(response.rows);
        if (response.total <= this.articleList.length) {
          this.hasMore = false;
        } else {
          this.hasMore = true;
          this.queryParams.pageNum++;
        }
      });
    },
    showSearchShowList: function (initData) {
      //展示数据
      if (initData) {
        this.articleList = [];
      }
      this.getList();
    },
    addMoreFun: function () {
      //查看更多
      this.showSearchShowList(false);
    },
    routeChange: function () {
      this.queryParams.categoryId =
        this.$route.query.classId == undefined
          ? 0
          : parseInt(this.$route.query.classId); //获取传参的classId
      this.showSearchShowList(true);
    },
  },
  watch: {
    // 如果路由有变化，会再次执行该方法
    $route: "routeChange",
    "$store.state.keywords": "routeChange",
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
  overflow: hidden;
}
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
  background: #64609e;
  border: 1px solid #64609e;
  transition: transform 0.2s linear;
  -webkit-transition: transform 0.2s linear;
}

.shareclassTwo li a:hover {
  transform: translate(0, -3px);
  -webkit-transform: translate(0, -3px);
}

.shareclassTwo li a.active {
  background: #fff;
  color: #64609e;
}

/*文章列表*/
.sharelistBox {
  transition: all 0.5s ease-out;
  font-size: 15px;
}
</style>
