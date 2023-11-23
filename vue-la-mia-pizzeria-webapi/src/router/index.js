import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'

const router = createRouter({
  // per navigare avanti e indietro tra le pagine già navigate
  // history: createWebHistory(import.meta.env.BASE_URL),
  history: createWebHistory(),
  
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    
  ]
})

export default router
