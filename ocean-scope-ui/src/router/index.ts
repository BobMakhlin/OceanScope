import { createRouter, createWebHistory } from 'vue-router'
import OceanScopeDashboardView from '@/views/OceanScopeDashboardView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: OceanScopeDashboardView,
    },
  ],
})

export default router
