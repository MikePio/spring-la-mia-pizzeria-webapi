<script>
import axios from 'axios';
import { store } from '../../data/store';

export default {
  name: 'SearchBar',
  data(){
    return{
      // toSearch: '',
      // searched: false,
      store,
      
    }
  },
  methods:{
    getApiSearch(){
      //* fa in modo che la funzione getPizzas della home non si avvii dopo aver effettuato una ricerca e quindi i dati cercati restano salvati nella home  
      store.searched = true;
      //! aggiungere obbligatoriamente un loader perché, ad esempio, se richiamo this.pizza.name prima che venga effettuata la chiamata api si genera un errore 
      store.loaded = false;
      axios.get(store.apiUrl, { params: { q: store.toSearch }}) // utilizza i parametri per passare la query di ricerca al backend
      //* OPPURE + semplice e comprensibile
      // axios.get(store.apiUrl + '?q=' + store.toSearch) // utilizza i parametri per passare la query di ricerca al backend
        .then(result => {
          store.pizzas = result.data;
          console.log('search: ');
          console.log(store.pizzas);
          store.loaded = true;
          // store.toSearch = ''; //* serve se vuoi cancellare ciò che hai cercato nella barra di ricerca
        })    
        .catch(error => {
        console.error('Error in requesting pizzas: ', error);
        store.loaded = true;
      });
    },
    redirectToHomePage() {
      //* fa in modo che la funzione getPizzas della home non si avvii dopo aver effettuato una ricerca e quindi i dati cercati restano salvati nella home  
      store.searched = true;
      this.$router.push('/'); //* this.$router è un'istanza fornita da Vue Router (Vue Router gestisce la navigazione all'interno di un'applicazione Vue) 
    },

  },
  mounted() {
  
  
  }

  }
</script>

<template>
  <div class="container-inner d-flex" style="height: 33px">

    <!-- form utilizzato per reindirizzare alla pagina home -->
    <!--//* @submit.prevent="redirectToHomePage" nel form impedisce il ricaricamento della pagina quando viene inviato e, allo stesso tempo, chiama la funzione redirectToHomePage -->
    <form class="d-flex" @submit.prevent="redirectToHomePage"> 
      <!-- //! NON INSERIRE MAI LA FUNZIONE DENTRO L'INPUT SE è GIà DICHIARATA NEL BUTTON SUBMIT -->
      <!-- input per la ricerca delle pizze per nome -->
      <input
        class="form-control me-2"
        v-model.trim="store.toSearch"
        placeholder="Search a pizza"
        type="text"
      >
      <button type="submit" @click="getApiSearch" :btn-danger="store.loaded" class="btn btn-danger-c d-flex justify-content-center align-items-center"><i class="fa-solid fa-magnifying-glass text-white"></i></button>
    </form>

  </div>
  
</template>

<style lang="scss" scoped>
@use '../../scss/main.scss' as *;


</style>