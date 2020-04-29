import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import axios from 'axios';
import AxiosInterceptor from '@/plugins/axios-interceptor'

Vue.config.productionTip = false

axios.defaults.baseURL = 'http://localhost:8100/';
axios.defaults.withCredentials = true;

AxiosInterceptor();

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
