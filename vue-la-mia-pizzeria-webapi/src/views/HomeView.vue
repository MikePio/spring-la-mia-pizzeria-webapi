<script>
import axios from 'axios'
import { store } from '../data/store'

import Header from '../components/Header.vue'
import Footer from '../components/Footer.vue'

export default {
  name: 'HomeView',

  data() {
    return {}
  },

  components: {},

  methods: {}
}
</script>

<template>
  <div class="container-inner d-flex flex-column align-items-center justify-content-start" style=" max-width: 90%;">
  <!-- oppure con mx-auto -->
  <!-- <div class="container-inner mx-auto"> -->
    <h1>Home</h1>
  </div>

</template>

<style lang="scss" scoped>
// @use '../scss/main.scss' as *;

h1 {
  color: black;
}
</style>