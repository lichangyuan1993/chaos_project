import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/home/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/department-management',
      name: 'department-management',
      component: () => import('../views/department/DepartmentManagementView.vue'),
    },
    {
      path: '/member-management',
      name: 'member-management',
      component: () => import('../views/member/MemberManagementView.vue'),
    },
  ],
})

export default router
