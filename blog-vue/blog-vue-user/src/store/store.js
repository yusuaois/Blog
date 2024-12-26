import { defineStore } from 'pinia'

export const useStore = defineStore('main', {
  state: () => ({
    loading: false,
    themeObj: 0,
    keywords: '',
    errorImg: 'this.onerror=null;this.src="' + require('../../static/img/tou.jpg') + '"',
    baseURL: 'http://localhost:7777/'
  }),
})
