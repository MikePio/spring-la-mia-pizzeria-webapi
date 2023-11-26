<script>
import axios from 'axios';
import { store } from '../../data/store';

export default {
  name: 'SearchBar',
  data(){
    return{
      // toSearch: '',
      store
      
    }
  },
  methods:{
    getApiSearch(){
      //! aggiungere obbligatoriamente un loader perché, ad esempio, se richiamo this.pizza.name prima che venga effettuata la chiamata api si genera un errore 
      store.loaded = false;
      axios.get(store.apiUrl + '' + store.toSearch)
        .then(result => {
          // da scrivere il resto della funzione
          store.loaded = true;
          store.toSearch = '';
        });
    },
    redirectToHomePage() {
      this.$router.push('/'); //* this.$router è un'istanza fornita da Vue Router (Vue Router gestisce la navigazione all'interno di un'applicazione Vue) 
    },



  },

  }
</script>

<template>
  <div class="container-inner d-flex" style="height: 33px">

    <!-- form utilizzato per reindirizzare alla pagina home -->
    <!--//* @submit.prevent="redirectToHomePage" nel form impedisce il ricaricamento della pagina quando viene inviato e, allo stesso tempo, chiama la funzione redirectToHomePage -->
    <form class="d-flex" @submit.prevent="redirectToHomePage"> 
      <!-- input per la ricerca delle pizze per nome -->
      <input
        class="form-control me-2"
        v-model.trim="store.toSearch"
        placeholder="Search a pizza"
        type="text"
        @keyup.enter="getApiSearch"
      >
      <button type="submit" @click="getApiSearch" :btn-danger="store.loaded" class="btn btn-danger-c d-flex justify-content-center align-items-center"><i class="fa-solid fa-magnifying-glass text-white"></i></button>
    </form>

  </div>
  
</template>

<style lang="scss" scoped>
@use '../../scss/main.scss' as *;


</style>