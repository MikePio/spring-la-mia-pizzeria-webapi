<script>
import Header from './components/Header.vue'
import Sidebar from './components/partials/Sidebar.vue'
import Footer from './components/Footer.vue'

import { store } from "./data/store"

export default {
  name: 'App',

  components:{
    Header,
    Footer,
    Sidebar,

  },

  data(){
    return{
      store
    }
  }
}
</script>

<template>
<div :class="store.showSidebar ? 'overflow-y-hidden' : ''">
    <transition name="sidebar-transition" mode="out-in">
      <Sidebar/>
    </transition>
  <Header />
  <main>
  <!-- <main style="min-height: calc(100vh - 60px - 90px - 150px); max-width: 90%;" class="p-md-5 py-5 d-flex flex-column align-items-center justify-content-start"> -->
    <router-view v-slot="{ Component, route }" style="min-height: calc(100vh - 60px - 90px - 150px); max-width: 90%;" class="p-md-5 py-5 mx-auto">
    <!-- <router-view v-slot="{ Component, route }" style="min-height: calc(100vh - 60px - 90px - 150px); max-width: 90%;" class="mx-auto"> -->
      <transition name="fade" mode="out-in">
        <div :key="route.name"> 
          <component :is="Component"/>
        </div>
      </transition>
    </router-view>
  </main>
  <Footer />
</div>

</template>

<style lang='scss'>
@use './scss/main.scss' as *;
body{
  // todo da eliminare provvisorio
  // background-color: #4338CA;  
  // overflow-x: hidden;
  .bg-dark{
    background-color: rgba(255,  0, 30, 1) !important;
  }
}

.overflow-y-hidden{
  overflow-y: hidden;
  // height: 100vh;
  // oppure
  max-height: 100vh;
}

//* transizioni quando si passa da una pagina all'altra
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

//* transizioni quando si passa da una pagina all'altra
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

//* transition per la sidebar
.sidebar-transition-enter-active,
.sidebar-transition-leave-active {
  transition: transform 0.4s ease-in-out;
}

.sidebar-transition-enter-from,
.sidebar-transition-leave-to {
  transform: translateX(-100%);
}
</style>