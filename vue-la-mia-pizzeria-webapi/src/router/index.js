import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import CreatePizza from '../views/CreatePizza.vue'
import EditPizza from '../views/EditPizza.vue'
import Contacts from '../views/Contacts.vue'
import PizzaDetail from '../views/PizzaDetail.vue'
import Error404 from '../views/Error404.vue'

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
      path: '/edit/:id',
      name: 'edit',
      component: EditPizza
    },
    {
      path: '/contacts',
      name: 'contacts',
      component: Contacts
    },
    {
      path: '/pizzas/:id',
      name: 'pizzaDetail',
      component: PizzaDetail
    },

    // deve essere sempre l'ultima
    {
      path: '/:pathMatch(.*)*',
      name: 'error404',
      component: Error404
    }
  ]
})

export {router}
