import { createApp } from 'vue'
import App from './App.vue'

import router from './router'

// import './assets/main.css'

// senza router (esercizio precedente che funziona senza il router) //* decommenta questa riga e rinomina _VecchioApp.vue in App.vue -> otterrai una pagina con tutti i dati dei progetti dalle'api es. name, start_date, type, technologies... 
// createApp(App).mount('#app')
// con il router
createApp(App).use(router).mount('#app')