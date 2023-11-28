<script>
import axios from 'axios'
import { store } from '../data/store'
import Loader from "../components/partials/Loader.vue"

export default {
  name: 'HomeView',

  data() {
    return {
      store,

    }
  },

  components: {
    Loader,

  },

  methods: {
    getPizzas() {
      store.toSearch = '',      
      store.loaded = false;
      store.searched = false;
      axios.get(store.apiUrl)
        .then(response => {
          store.pizzas = response.data; // Memorizza i dati delle pizze nella variabile 'pizzas' del componente
          console.log('Home: ', store.pizzas);
          //* per far scomparire il loader
          store.loaded = true;
        })
        .catch(error => {
          console.error('Error in requesting pizzas: ', error);
        });
    },
    formatNumber(number) {
        if (typeof number === 'number') {
          return number.toLocaleString('it-IT', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
        } else {
          return number; // gestione di casi non numerici
        }
    },

  },

  mounted() {
    if(store.searched === false){

      this.getPizzas();
    }

  
  }

}
</script>

<template>
  <div class="container-inner d-flex flex-column align-items-center justify-content-start" style=" max-width: 90%;">
  <!-- oppure con mx-auto -->
  <!-- <div class="container-inner mx-auto"> -->
    
    <h2 class="mb-5 text-center">Home</h2>

    <Loader v-if="!store.loaded"/>

    <h1 v-if="store.loaded && store.pizzas.length < 1" class="text-danger text-center">
      There aren't pizzas
    </h1>

    <div v-if="store.loaded" class="pizza-cards d-flex justify-content-center flex-wrap">
      <div v-for="pizza in store.pizzas" :key="pizza.id" class="pizza-card me-3 mb-3 shadow-sm">
        <div class="pizza-details ps-2 py-2">
          <h4>{{ pizza.name }}</h4>
          <p>{{ pizza.description }}</p>
          <p>Price: € {{ formatNumber(pizza.price) }}</p>
        </div>
        <div class="pizza-image d-flex align-items-center justify-content-end ">
          <!-- //* soluzione @error con vue -->
            <!-- v-if="pizza.photo.includes('/img/')" -->
          <img 
            v-if="pizza.photo.startsWith('https://') || pizza.photo.startsWith('http://')"
            :src="pizza.photo ? pizza.photo : `src/assets/img/placeholder-img.png`" 
            :alt="pizza.name"
            @error="$event.target.src=`src/assets/img/placeholder-img.png`"
          >
          <img 
            v-else
            :src="pizza.photo ? `http://localhost:8080/img/${pizza.photo}` : `src/assets/img/placeholder-img.png`"
            :alt="pizza.name"
            @error="$event.target.src=`src/assets/img/placeholder-img.png`"
          >
          <!-- //* soluzione onerror con javascript puro -->
          <!-- <img 
            :src="pizza.photo ? `http://localhost:8080/img/${pizza.photo}` : `src/assets/img/placeholder-img.png`"
            :alt="pizza.name"
            onerror="this.src=`src/assets/img/placeholder-img.png`"
          > -->
        </div>
      </div>
    </div>
  
    <button v-if="store.searched === true" type="submit" @click="getPizzas" :btn-danger="store.loaded" class="btn btn-danger-c d-flex justify-content-center align-items-center mt-4">All pizzas</button>
  </div>
</template>

<style lang="scss" scoped>

.pizza-card {
  display: flex;
  width: 340px;
  border: 1px solid #ccc;
  border-radius: 5px;
  overflow: hidden;
  height: 192px;
}

.pizza-details {
  width: 185px;

}

.pizza-image img {
  width: 155px;
  height: 192px;
  object-fit: cover;
}
</style>