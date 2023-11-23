<script>
import { store } from "../../data/store"

export default {
  name: 'Sidebar',

  components:{  

  },

  data(){
    return{
      store,
      // showSidebar: false,

    }
  },

  methods: {
    // toggleSidebar() {
    //   store.showSidebar = !store.showSidebar;
    //   // console.log(store.showSidebar);
    // }
  }

}
</script>

<template>
<!-- //! v-show non funziona se c'è lo style inline es: style="width: 280px; height: 100vh;" -->
<div v-show="store.showSidebar">
<!-- oppure -->
<!-- <div :class="{ 'd-block': store.showSidebar, 'd-none': !store.showSidebar }"> -->
  <div class="d-flex sidebar-custom">
    <div class="d-flex flex-column flex-shrink-0 p-3 text-dark bg-white shadow-top rounded-end" style="width: 280px; height: 100vh;">
      <router-link :to="{name: 'home'}" class="d-flex align-items-center justify-content-between mb-md-0 me-md-auto link-dark text-decoration-none">
        <h2 class="my-2">Pizzeria Italia</h2>
        <button class="btn-dark-c" @click.prevent="store.toggleSidebar()"><i class="fa fa-times"></i></button>
      </router-link>
      <hr>
      <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
          <router-link :to="{name: 'home'}" @click.prevent="store.toggleSidebar()" class="nav-link text-dark " aria-current="page">
            <svg class="bi me-2" width="16" height="16"></svg>
            <i class="fa-solid fa-house"></i> Home
          </router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{name: 'create'}" @click.prevent="store.toggleSidebar()" class="nav-link text-dark " aria-current="page">
            <svg class="bi me-2" width="16" height="16"></svg>
            <i class="fa-solid fa-list-ul"></i> Add a New Pizza
          </router-link>
        </li>
        <li class="nav-item">
          <router-link :to="{name: 'contacts'}" @click.prevent="store.toggleSidebar()" class="nav-link text-dark " aria-current="page">
            <svg class="bi me-2" width="16" height="16"></svg>
            <i class="fa-solid fa-address-card"></i> Contacts
          </router-link>
        </li>
      </ul>
    </div>
    <div class="bg-black opacity-50" style="width: calc(100vw - 280px);" @click.prevent="store.toggleSidebar()"></div>
  </div>
</div>
</template>

<style lang="scss" scoped>
@use '../../scss/main.scss' as *;

.sidebar-custom{
  position: absolute;
  top: 0px;
  left: 0px;
  // bottom: 0px;
  // right: 0px;
  z-index: 10;
  // height: max-content;
}

.btn-dark-c{
  // background-color: #212529 !important;
  background-color: #1A1E21 !important;
  display: inline-block;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: 0.25rem;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  color: #fff;
  border: 1px solid transparent;
  text-decoration: none;
  transition: background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
}

.nav-item{
  .active{
    background-color: #212529 !important;
    // background-color: #1A1E21 !important;
    color: #fff !important;
  }
}
</style>