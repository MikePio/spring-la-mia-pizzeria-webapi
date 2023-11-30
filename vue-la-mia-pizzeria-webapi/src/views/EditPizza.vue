<script>
import axios from 'axios';
import { store } from '../data/store';
import Loader from "../components/partials/Loader.vue"

export default {
    name:'EditPizza',

    data(){
        return{
            name: '',
            price: '',
            photo: '',
            description: '',
            pizza: null,
            store,
            loaded: false,
            sending: false,
            success: false,
            errors: {},
            errorMessage: false,

        }
    },

    components:{
        Loader
    
    },

    methods:{
        getOldPizza() {
            this.loaded = false;
            //*rotta definita in PIzzaApiController del progetto Spring
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
        sendForm() {
            this.sending = true;
            this.errors = {};
            this.errorMessage = false;
            this.success = false;

            const newPizza = {
                name: this.pizza.name,
                price: this.pizza.price,
                photo: this.pizza.photo,
                description: this.pizza.description
            };

            // axios.put(`${store.apiUrl}/edit/${this.$route.params.id}`, newPizza)
            // oppure
            axios.put(store.apiUrl + "/edit/" + this.$route.params.id, newPizza)
                .then(result => { 
                    this.success = result.status == 200;
                    console.log('result.status', result.status);
                    // ulteriore controllo per gli errori
                    if(!this.success){
                        this.errorMessage = true;
                        this.errors = result.data.errors;
                        console.log("prova 1", this.success, this.errors);
                    }else{
                        this.errors = {};
                        this.errorMessage = false;
                        console.log("prova 2", this.success, this.errors);
                    }
                    this.sending = false;
                    console.log('Pizza created: ', result); 
                    console.log("prova 3", this.success, this.errors);
                })
                .catch(error => {
                    this.sending = false;
                    this.errorMessage = true;
                    console.log("error ", this.success, this.errors);
                    if (error.response && error.response.data && error.response.data.errors) {
                        // se ci sono errori nella risposta, assegna gli errori alla variabile errors
                        this.errors = error.response.data.errors;
                        console.log("error 1", this.success, this.errors);
                        // this.errorMessage = true;
                    } else {
                        console.log("error 2", this.success, this.errors);
                        // console.error('Error creating pizza: ', this.errors);
                        console.log(error);
                        // this.errorMessage = true;
                    }
                });
        },       
    },
    mounted() {
        this.getOldPizza();
    }
}
</script>

<template>
    <div class="container-inner d-flex flex-column align-items-center justify-content-start" style=" max-width: 90%;">
    <!-- oppure con mx-auto -->
    <!-- <div class="container-inner mx-auto"> -->

        <div v-if="loaded && pizza" class="d-flex flex-column align-items-center justify-content-start">
            <div v-if="loaded && pizza" class="d-flex align-items-center justify-content-center mb-4">
                <h2 class="text-center me-2">Edit {{ pizza.name }}</h2>
                <router-link class="def-link" :to="{ name: 'pizzaDetail', params:{ id: pizza.id } }">
                    <button style="min-height: 40px;" class="btn-icon btn btn-warning-c fw-custom d-flex align-items-center justify-content-center me-1" title="Edit offer"><i class="fa-solid fa-eye"></i></button>
                </router-link>
                <!-- funzione per eliminare una pizza //* DALLO STORE -->
                <button @click="store.deletePizza(pizza.id, this.$router.push('/'))" style="min-height: 40px" class="btn-icon btn btn-danger-c fw-custom d-flex align-items-center justify-content-center " title="Delete pizza"><i class="fa-solid fa-trash"></i></button>
            </div>

            <div v-if="success" class="alert alert-success mt-4" role="alert">
                <h5 class="text-success p-0 m-0">Pizza Edited! Check the menu</h5>
            </div>

            <div v-if="errorMessage === true" class="alert alert-danger p-0 mt-4" role="alert">
                <h4 class="text-danger text-center mt-2">Error</h4>
                <div class="text-danger text-center my-2 mx-4"><strong>Some values entered in the form are incorrect</strong></div>
                <div v-for="(error,index) in errors" :key="index" class="text-danger mx-4 my-2">- {{ error[0] }}</div> 
            </div>

            <div class="d-flex flex-column align-items-center justify-content-center">
                <!--//* @submit.prevent="sendForm()" nel form impedisce il ricaricamento della pagina quando viene inviato e, allo stesso tempo, chiama la funzione sendForm -->
                <form @submit.prevent="sendForm()">
                    <div class="mb-3" style="width: 80vw">
                        <label for="name" class="fw-bold form-label">Name</label>
                        <input :class="{ 'is-invalid' : errors.name }" v-model.trim="pizza.name" type="text" name="name" class="form-control" placeholder="Margherita"> 
                        <!-- //* CONTROLLO LATO FRONTEND (viene effettuato prima di un controllo tramite backend) - minlength potrebbe non funzionare come previsto con v-model quindi bisogna fare una funzione apposita in js -->
                        <!-- //! DA DECOMMENTARE (LASCIATO COMMENTATO PER FAR VEDERE CHE FUNZIONA IL CONTROLLO DEGLI ERRORI LATO BACKEND)  -->
                        <!-- <input :class="{ 'is-invalid' : errors.name }" v-model.trim="pizza.name" required minlength="2" type="text" name="name" class="form-control" placeholder="Margherita">  -->
                        <div v-for="(error,index) in errors.name" :key="index" class="text-danger mt-1">{{ error }}</div>
                    </div>
                    <div class="mb-3" style="width: 80vw">
                        <label for="price" class="fw-bold form-label">Price</label>
                        <input :class="{ 'is-invalid' : errors.price }" v-model.trim="pizza.price" pattern="[0-9.]*" type="number" step="0.10" min="1" class="form-control" name="price" placeholder="10.50"> 
                        <!-- //* CONTROLLO LATO FRONTEND (viene effettuato prima di un controllo tramite backend) - minlength potrebbe non funzionare come previsto con v-model quindi bisogna fare una funzione apposita in js -->
                        <!-- //! DA DECOMMENTARE (LASCIATO COMMENTATO PER FAR VEDERE CHE FUNZIONA IL CONTROLLO DEGLI ERRORI LATO BACKEND)  -->
                        <!-- <input :class="{ 'is-invalid' : errors.price }" v-model.trim="pizza.price" required minlength="1" pattern="[0-9.]*" type="number" step="0.10" min="1" class="form-control" name="price" placeholder="10.50">  -->
                        <div v-for="(error,index) in errors.price" :key="index" class="text-danger mt-1">{{ error }}</div>
                    </div>
                    <div class="mb-3" style="width: 80vw">
                        <label for="photo" class="fw-bold form-label">Image</label>
                        <input :class="{ 'is-invalid' : errors.photo }" v-model.trim="pizza.photo" type="text" class="form-control" name="photo" placeholder="https://www.marcobianchi.blog/wp-content/uploads/2023/01/pizza-2048x1365.jpg"> 
                        <!-- //* CONTROLLO LATO FRONTEND (viene effettuato prima di un controllo tramite backend) - minlength potrebbe non funzionare come previsto con v-model quindi bisogna fare una funzione apposita in js -->
                        <!-- //! DA DECOMMENTARE (LASCIATO COMMENTATO PER FAR VEDERE CHE FUNZIONA IL CONTROLLO DEGLI ERRORI LATO BACKEND)  -->
                        <!-- <input :class="{ 'is-invalid' : errors.photo }" v-model.trim="pizza.photo" required minlength="1" type="text" class="form-control" name="photo" placeholder="https://www.marcobianchi.blog/wp-content/uploads/2023/01/pizza-2048x1365.jpg">  -->
                        <div v-for="(error,index) in errors.photo" :key="index" class="text-danger mt-1">{{ error }}</div>
                    </div>
                    <div class="mb-3" style="width: 80vw">
                        <label for="description" class="fw-bold form-label">Description</label>
                        <textarea :class="{ 'is-invalid' : errors.description }" v-model.trim="pizza.description" type="text" name="description" class="form-control" placeholder="Description" cols="10" rows="10"></textarea> 
                        <div v-for="(error,index) in errors.description" :key="index" class="text-danger mt-1">{{ error }}</div>
                    </div>
                    <button type="submit" class="btn btn-danger-c" :btn-danger="sending" :disabled="sending">{{ sending ? 'Loading...' : 'Add' }}</button>
                </form>
            </div>
        </div>

        <Loader v-if="!loaded"/>

    </div>
</template>



<style scoped>
    .btn-danger-c{
        background-color: #ff001e !important;
        color: white;
    }

    a{
        color: inherit;

    }
</style>

