import { createRouter, createWebHistory } from 'vue-router'
import ShipDashboard from '@/views/ShipDashboard.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: ShipDashboard,
    },
  ],
})

export default router
