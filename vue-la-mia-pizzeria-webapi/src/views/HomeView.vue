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
      axios.get(store.apiUrl)
        .then(response => {
          store.pizzas = response.data; // Memorizza i dati delle pizze nella variabile 'pizzas' del componente
          console.log(store.pizzas);
          //* per far scomparire il loader
          store.loaded = true;
        })
        .catch(error => {
          console.error('Error in requesting pizzas: ', error);
        });
    },


  },

  mounted() {
    this.getPizzas();

  
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
      <div v-for="pizza in store.pizzas" :key="pizza.id" class="pizza-card me-3 mb-3">
        <div class="pizza-details ps-2 py-2">
          <h4>{{ pizza.name }}</h4>
          <p>{{ pizza.description }}</p>
          <p>Price: € {{ pizza.price }}</p>
        </div>
        <div class="pizza-image d-flex align-items-center justify-content-end ">
          <!-- //* soluzione @error con vue -->
          <img 
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