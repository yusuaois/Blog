import axios from 'axios'
import { ElNotification, ElMessageBox, ElMessage } from 'element-plus'
import router from '@/router'
import { useStore } from '../stores/store'  // 引入 Pinia store
import { getToken } from '@/utils/auth'
import errorCode from '@/utils/errorCode'

// 从 Pinia store 获取 baseURL 和其他状态
const store = useStore()

// 导入静态图片路径
import errorImgPath from '../../static/img/tou.jpg'

// 设置 axios 默认配置
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// 创建 axios 实例
const service = axios.create({
  baseURL: store.baseURL,  // 使用 Pinia store 中的 baseURL
  timeout: 10000  // 设置请求超时
})

// request 拦截器
service.interceptors.request.use(config => {
  // 是否需要设置 token
  const isToken = (config.headers || {}).isToken === false
  if (getToken() && !isToken) {
    config.headers['token'] = getToken()  // 请求中添加 token
  }
  // get 请求映射 params 参数
  if (config.method === 'get' && config.params) {
    let url = config.url + '?'
    for (const propName of Object.keys(config.params)) {
      const value = config.params[propName]
      let part = encodeURIComponent(propName) + '='
      if (value !== null && typeof value !== 'undefined') {
        if (typeof value === 'object') {
          for (const key of Object.keys(value)) {
            if (value[key] !== null && typeof value[key] !== 'undefined') {
              const params = propName + '[' + key + ']'
              const subPart = encodeURIComponent(params) + '='
              url += subPart + encodeURIComponent(value[key]) + '&'
            }
          }
        } else {
          url += part + encodeURIComponent(value) + '&'
        }
      }
    }
    url = url.slice(0, -1)  // 删除最后的 '&'
    config.params = {}
    config.url = url
  }
  return config
}, error => {
  console.log(error)
  return Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use(res => {
  // 默认的成功状态码是 200
  const code = res.data.code || 200
  const msg = errorCode[code] || res.data.msg || errorCode['default']

  if (code === 401) {
    ElMessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
      confirmButtonText: '重新登录',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      localStorage.setItem('logUrl', router.currentRoute.fullPath)
      router.push({ path: '/Login?login=1' })
    }).catch(() => {})
    return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
  } else if (code === 500) {
    ElMessage({
      message: msg,
      type: 'error'
    })
    return Promise.reject(new Error(msg))
  } else if (code !== 200) {
    ElNotification.error({
      title: msg
    })
    return Promise.reject('error')
  } else {
    // 转换字符串 total 为数字
    if (res.data.data && res.data.data.total) {
      res.data.data.total = parseInt(res.data.data.total)
    }
    return res.data.data
  }
}, error => {
  let { message } = error
  if (message === 'Network Error') {
    message = '后端接口连接异常'
  } else if (message.includes('timeout')) {
    message = '系统接口请求超时'
  } else if (message.includes('Request failed with status code')) {
    message = '系统接口' + message.substr(message.length - 3) + '异常'
  }
  ElMessage({
    message: message,
    type: 'error',
    duration: 5 * 1000
  })
  return Promise.reject(error)
})

export default service
