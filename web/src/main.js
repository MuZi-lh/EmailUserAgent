import Vue from 'vue'
import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import { router } from './router';
import { useRouter } from 'vue-router';
import { axios } from 'axios';
import App from './App.vue';

const app = createApp(App);
app.use(router);
app.use(ElementPlus);
app.use(axios);
app.use(useRouter);

app.mount('#app');

new Vue({
    router,
    data: function(){
        return {
            mailBox: '',
            authorizationCode: ''
        }
    },
    render: h => h(App)
}).$mount('#app');