<script>
import Header from './components/Header.vue'
// import Main from './components/Main.vue'
import Footer from './components/Footer.vue'

import { store } from "./data/store"

export default {
  name: 'App',

  components:{
    Header,
    Footer,
  },

  data(){
    return{
      store
    }
  }
}
</script>

<template>
  <Header />
  <main style="min-height: calc(100vh - 60px - 90px - 150px); max-width: 90%;" class="p-md-5 py-5 mx-auto">
    <router-view>
    </router-view>
  </main>
  <Footer />
</template>

<style lang='scss'>
@use './scss/main.scss' as *;


</style>