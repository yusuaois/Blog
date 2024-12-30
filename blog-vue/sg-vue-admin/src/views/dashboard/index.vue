<template>
  <div class="dashboard-container">
    <div class="dashboard-text">博客管理系统</div>
    <div>个人博客管理系统</div>
    <div>当前博客在线人数：{{ total }} 人</div>
    <div>博文一览：</div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { aliveUser } from "@/api/system/user";
export default {
  name: "Dashboard",
  data() {
    return {
      total: 0,
    };
  },
  computed: {
    ...mapGetters(["name"]),
  },
  created() {
    this.getAliveUser();
  },
  methods: {
    getAliveUser() {
      aliveUser().then((response) => {
        this.total = response;
      });
    },
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
}
</style>
