// stores/articleStore.js
import { defineStore } from 'pinia';
import { ref, reactive } from 'vue';
// import { articleList } from '../api/article';  // 假设这是你的 API 调用

export const useArticleStore = defineStore('article', () => {
  // 定义状态
  const articleListData = ref([]);
  const hasMore = ref(true);
  const queryParams = reactive({
    pageNum: 1,
    pageSize: 10,
    categoryId: 0,
  });

  // 获取文章列表的方法
  const getList = async () => {
    try {
      const response = await articleList(queryParams); // 调用接口获取数据
      articleListData.value = [...articleListData.value, ...response.rows]; // 拼接新的数据
      hasMore.value = response.total > articleListData.value.length; // 判断是否有更多数据
      if (hasMore.value) queryParams.pageNum++; // 如果有更多，增加页码
    } catch (error) {
      console.error("获取文章列表失败:", error);
    }
  };

  return {
    articleListData,
    hasMore,
    queryParams,
    getList,
  };
});
