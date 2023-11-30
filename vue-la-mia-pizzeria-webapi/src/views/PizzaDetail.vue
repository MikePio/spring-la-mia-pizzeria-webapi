<script>
import axios from 'axios'
import { store } from '../data/store'
import Loader from "../components/partials/Loader.vue"

export default {
  name: 'PizzaDetail',
  components: {
    Loader
  },
  data() {
    return {
      store,
      pizza: null,
      loaded: false
    }
  },
  methods: {
    getApi() {
      this.loaded = false;
      //*rotta definita in PizzaApiController del progetto Spring
      // axios.get(store.apiUrl + "/" + this.$route.params.id)
      // oppure
      axios.get(`${store.apiUrl}/${this.$route.params.id}`)
        .then(result => {
          this.pizza = result.data;
          this.loaded = true;
        })
        .catch(error => {
          console.error('Error fetching pizza details:', error);
          this.loaded = true;
        });
    },
    // funzione per eliminare una pizza
    deletePizza() {
      this.loaded = false;
      //*rotta definita in PizzaApiController del progetto Spring
      axios.delete(`${store.apiUrl}/delete/${this.$route.params.id}`)
        .then(response => {
          console.log('Successfully deleted pizza:', response.data);
          // reindirizzo alla pagina home dove ci sono tutte le pizze
          this.$router.push('/'); //* this.$router è un'istanza fornita da Vue Router (Vue Router gestisce la navigazione all'interno di un'applicazione Vue) 
        })
        .catch(error => {
          console.error('Error while deleting the pizza:', error);
        })
        .finally(() => {
          // codice che deve essere eseguito indipendentemente dal successo o dall'errore della richiesta
          this.loaded = true;
        });
    }
  },
  mounted() {
    this.getApi();
  }
}
</script>

<template>
  <div class="container-inner mx-auto">
    <div v-if="loaded && pizza" class="d-flex align-items-center justify-content-center mb-5">
      <h2 class="text-center me-2">{{ pizza.name }}</h2>
      <router-link class="def-link" :to="{ name: 'edit', params:{ id: pizza.id } }">
        <button style="min-height: 40px;" class="btn-icon btn btn-warning-c fw-custom d-flex align-items-center justify-content-center me-1" title="Edit offer"><i class="fa-solid fa-pencil"></i></button>
      </router-link>
      <!-- funzione per eliminare una pizza //* salvato in pagina (quella salvata nello store viene utilizzata in EditPizza) -->
      <button @click="deletePizza" style="min-height: 40px" class="btn-icon btn btn-danger-c fw-custom d-flex align-items-center justify-content-center " title="Delete pizza"><i class="fa-solid fa-trash"></i></button>
    </div>
    <img 
      v-if="pizza && (pizza.photo.startsWith('https://') || pizza.photo.startsWith('http://'))"
      :src="pizza.photo ? pizza.photo : `src/assets/img/placeholder-img.png`" 
      :alt="pizza.name"
      @error="$event.target.src=`src/assets/img/placeholder-img.png`"
    >
    <img 
      v-if="pizza && !(pizza.photo.startsWith('https://') || pizza.photo.startsWith('http://'))"
      :src="pizza.photo ? `http://localhost:8080/img/${pizza.photo}` : `src/assets/img/placeholder-img.png`"
      :alt="pizza.name"
      @error="$event.target.src=`src/assets/img/placeholder-img.png`"
    >

    <div v-if="loaded && pizza" class="mt-3">
      <!-- informazioni base sulla pizza -->
      <div class="d-flex align-items-center">
        <h3 class="me-2">Price: </h3>
        <span class="">€ {{ pizza.formattedPrice || 'No price' }}</span> 
      </div>
      <h3>Description: </h3>
      <p>{{ pizza.description || 'No description' }}</p>
    </div>

    <!-- gestione degli ingredienti -->
    <div v-if="loaded && pizza && pizza.ingredients && pizza.ingredients.length > 0">
      <h3>Ingredients:</h3>
      <ul>
        <li v-for="ingredient in pizza.ingredients" :key="ingredient.id">
          {{ ingredient.nameWithInitialCapital }}
        </li>
      </ul>
    </div>

    <!-- gestione delle offerte speciali -->
    <div v-if="loaded && pizza && pizza.specialOffers && pizza.specialOffers.length > 0">
      <h3>Special Offers:</h3>
      <ul>
        <li v-for="offer in pizza.specialOffers" :key="offer.id">
          {{ offer.title }} - From {{ offer.formattedStartDate }} to {{ offer.formattedEndDate }}
        </li>
      </ul>
    </div>

    <Loader v-if="!loaded"/>
  </div>
</template>

<style lang="scss" scoped>

img{
  max-width: 100%;
}

.badge-c{
  line-height: normal;
  // padding: 5px !important;
  // background-color: #1A1E21;
  // background-color: #212529;
  //background-color: #272c31;
  // background-color: rgba(255,  0, 30, 1) !important;
  background-color: #ff001e !important;
  // background-color: #4338CA;
}

a{
  // text-decoration: none !important;
  color: inherit; // colore ereditato
  // stesso colore di
  // color: #212529;
  &:hover{
    // text-decoration: none !important;
    // color: inherit; // colore ereditato
    // stesso colore di
    // color: #212529;
  }
}
</style>