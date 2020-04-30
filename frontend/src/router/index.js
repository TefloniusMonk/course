import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/views/main.vue'
import Profile from '@/views/profile/profile.vue'
import Basket from '@/views/basket/basket.vue'
import Bills from '@/views/bill/bill-list.vue'
import Catalog from '@/views/catalog/catalog.vue'
import Login from '@/views/login.vue'
import SignUp from '@/views/sign-up.vue'

Vue.use(VueRouter)


const routes = [
    {
        path: '/login',
        name: 'login',
        component: Login,
    },
    {
        path: '/sign-up',
        name: 'sign-up',
        component: SignUp,
    },
    {
        path: '/',
        name: 'main',
        component: Main,
        children: [
            {
                path: '/profile',
                name: 'profile',
                component: Profile,
            },
            {
                path: '/bills',
                name: 'bill-list',
                component: Bills,
            },
            {
                path: '/catalog',
                name: 'catalog',
                component: Catalog,
            },
            {
                path: '/basket',
                name: 'basket',
                component: Basket,
            }
        ],
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
