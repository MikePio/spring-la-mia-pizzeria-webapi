import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import CreatePizza from '../views/CreatePizza.vue'
import EditPizza from '../views/EditPizza.vue'
import Contacts from '../views/Contacts.vue'

const router = createRouter({
  // per navigare avanti e indietro tra le pagine già navigate
  // history: createWebHistory(import.meta.env.BASE_URL),
  history: createWebHistory(),
  // serve per mantenere il link (con la classe css active) attivo della pagina corrente durante la navigazione delle pagine
  linkExactActiveClass: 'active',
  
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/create',
      name: 'create',
      component: CreatePizza
    },
    {
      path: '/edit',
      // path: '/edit/:id',
      name: 'edit',
      component: EditPizza
    },
    {
      path: '/contacts',
      name: 'contacts',
      component: Contacts
    },

  ]
})

export {router}
