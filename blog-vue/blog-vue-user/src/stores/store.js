import { defineStore } from 'pinia'
import errorImgPath from '../../static/img/tou.jpg'

export const useStore = defineStore('main', {
  state: () => ({
    loading: false,
    themeObj: 0,
    keywords: '',
    errorImg: `this.onerror=null;this.src="${errorImgPath}"`,
    baseURL: 'http://localhost:7777/'
  }),
})
