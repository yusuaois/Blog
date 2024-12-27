// src/store/themeStore.js
import { defineStore } from 'pinia';

export const useThemeStore = defineStore('theme', {
  state: () => ({
    themeObj: {
      user_start: 1, // 用户状态，例如：0表示未登录，1表示已登录
      center_smailimg: '', // 用户头像
      right_img: '', // 右侧图片
    },
  }),
  getters: {
    // 可定义计算属性
    isUserLoggedIn: (state) => state.themeObj.user_start !== 0,
  },
  actions: {
    // 可定义修改状态的函数
    setUserStatus(status) {
      this.themeObj.user_start = status;
    },
    setThemeImage(image) {
      this.themeObj.right_img = image;
    },
  },
});
