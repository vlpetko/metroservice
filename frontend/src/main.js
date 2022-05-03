import Vue from 'vue'
import App from './App.vue'
import store from './store/store'
import { router } from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import VeeValidate from 'vee-validate'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt
} from '@fortawesome/free-solid-svg-icons'

import moment from 'moment-timezone'
moment.tz.setDefault('Asia/Jakarta')

Vue.prototype.moment = moment;
moment.locale('ru');


export const eventBus = new Vue();


library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.component('font-awesome-icon', FontAwesomeIcon);

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.config.productionTip = false

Vue.use(VeeValidate, {
  inject: true,
  fieldsBagName: 'veeFields'
});


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

