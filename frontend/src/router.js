import Vue from 'vue';
import Router from 'vue-router';
import MainPage from './views/MainPage.vue';
// import Login from './views/Login.vue';
// import Register from './views/Register.vue';

Vue.use(Router);

export const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'main',
            component: MainPage
        },
        {
            path: '/main',
            component: MainPage,
                    meta: {
                        title: 'Справочник'
                    }
        },

        {
            path: '/creator',
            name: 'creator',
            meta: {
                title: 'Редактор'
            },
            // lazy-loaded
            component: () => import('./views/LineCreator.vue')
        },

    //     {
    //         path: '/register',
    //         component: Register,
    //         meta: {
    //             auth: true,
    //             title: 'Регистрация'
    //         }
    //     },
    //     {
    //         path: '/profile',
    //         name: 'profile',
    //         meta: {
    //             auth: true,
    //             title: 'Профиль'
    //         },
    //         // lazy-loaded
    //         component: () => import('./views/Profile.vue')
    //     },
    //     // {
    //     //     path: '/admin',
    //     //     name: 'admin',
    //     //     // lazy-loaded
    //     //     component: () => import('./views/BoardAdmin.vue')
    //     // },
    //     // {
    //     //     path: '/mod',
    //     //     name: 'moderator',
    //     //     // lazy-loaded
    //     //     component: () => import('./views/BoardModerator.vue')
    //     // },
    //     // {
    //     //     path: '/user',
    //     //     name: 'user',
    //     //     // lazy-loaded
    //     //     component: () => import('./views/BoardUser.vue')
    //     // },
    //     {
    //         path: '/tasks',
    //         name: 'adminTasks',
    //         meta: {
    //             auth: true,
    //             title: 'Задачи'
    //         },
    //         // lazy-loaded
    //         component: () => import('./views/AdminTasks.vue')
    //     },
    //
        {
            path: '/line/:Pid',
            name: 'currentLine',
            meta: {
                title: 'Справочник'
            },
            // lazy-loaded
            component: () => import('./views/CurrentLine.vue')
        },

        {
            path: '/editor/:Pid',
            name: 'editor',
            meta: {
                title: 'Редактор'
            },
            // lazy-loaded
            component: () => import('./views/LineCreator.vue')
        }
    ]
});