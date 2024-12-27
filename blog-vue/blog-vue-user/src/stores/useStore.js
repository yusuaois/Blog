// src/stores/useStore.js
import { defineStore } from 'pinia'

export const useStore = defineStore('main', {
  state: () => ({
    hasLogin: false,
    userInfo: null,
    classListObj: [],
    activeIndex: '/Home',
    input: '',
    themeObj: {
      top_image: '', 
      head_portrait: '', 
      autograph: '',
    }
  }),

  actions: {
    setLoginStatus(status) {
      this.hasLogin = status
    },
    setUserInfo(userInfo) {
      this.userInfo = userInfo
    },
    setCategoryList(classList) {
      this.classListObj = classList
    },
    setActiveIndex(index) {
      this.activeIndex = index
    },
    setInput(value) {
      this.input = value
    }
  }
})
