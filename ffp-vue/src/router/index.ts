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
      path: '/member-management',
      name: 'member-management',
      component: () => import('../views/member/MemberManagementView.vue'),
    },
    {
      path: '/member-enrollment',
      name: 'member-enrollment',
      component: () => import('../views/member/MemberEnrollmentView.vue'),
    },
    {
      path: '/member-search',
      name: 'member-search',
      component: () => import('../views/member/MemberSearchView.vue'),
    },
    {
      path: '/member-profile',
      name: 'member-profile',
      component: () => import('../views/member/MemberProfileView.vue'),
    },{
      path: '/member-nominee',
      name: 'member-nominee',
      component: () => import('../views/member/MemberNomineeView.vue'),
    },{
      path: '/user-management',
      name: 'user-management',
      component: () => import('../views/user/UserManagementView.vue'),
    }

  ],
})

export default router
