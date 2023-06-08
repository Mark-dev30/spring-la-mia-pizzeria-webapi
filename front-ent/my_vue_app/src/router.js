import { createRouter, createWebHistory } from "vue-router";
import HomePage from './pages/HomePage.vue';
import Create from './pages/Create.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'homepage',
            component: HomePage
        },
        {
            path: '/create',
            name: 'create',
            component: Create
        }

    ]
});
export { router }