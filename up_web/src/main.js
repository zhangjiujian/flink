import Vue from 'vue'
import router from './Router'

import ElementUI from 'element-ui'
import "./styles/element-variables.scss"
import "./styles/index.scss"

import App from './App.vue'

Vue.use(ElementUI)

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
