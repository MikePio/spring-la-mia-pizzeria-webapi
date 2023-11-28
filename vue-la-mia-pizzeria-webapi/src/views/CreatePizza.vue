<script>
import axios from 'axios';
import { store } from '../data/store';

export default {
    name:'CreatePizza',

    data(){
        return{
            //* dati già salvati nello store:
            // name: '',
            // price: '',
            // photo: '',
            // description: '',
            store,
            sending: false,
            success: false,
            errors: {},

        }
    },

    components:{


    },

    methods:{
        sendForm() {
            this.sending = true;
            this.errors = {};
            this.success = false;

            const newPizza = {
                name: store.name,
                price: store.price,
                photo: store.photo,
                description: store.description
            };

            axios.post(store.apiUrl, newPizza)
                .then(result => {
                    this.resetForm();    
                    this.success = result.newPizza.success;
                    if(!result.newPizza.success){
                        this.errors = result.newPizza.errors;
                        console.log("p", this.success, this.errors);
                    }else{
                        this.errors = {};
                    }
                    this.sending = false;
                    console.log('Pizza created: ', result); 
                    console.log("pp", this.success, this.errors);
                })
                .catch(error => {
                    this.sending = false;
                    console.log("d", this.success, this.errors);
                    if (error.response && error.response.data && error.response.data.errors) {
                        // se ci sono errori nella risposta, assegna gli errori alla variabile errors
                        this.errors = error.response.data.errors;
                        console.log("dd", this.success, this.errors);

                    } else {
                        console.log("ddd", this.success, this.errors);
                        console.error('Error creating pizza: ', error);
                    }
                });
        },
        resetForm() {
            store.name = '';
            store.price = '';
            store.photo = '';
            store.description = '';
            this.success = true;
        },
            

    },
}
</script>

<template>
    <div class="container-inner d-flex flex-column align-items-center justify-content-start" style=" max-width: 90%;">
    <!-- oppure con mx-auto -->
    <!-- <div class="container-inner mx-auto"> -->

        <h2 class="mb-4 text-center">Add A New Pizza</h2>

        <div v-if="success" class="alert alert-success mt-4" role="alert">
            <h5 class="text-success p-0 m-0">Pizza Added! Check the menu</h5>
        </div>

        <div v-if="errors" class="alert alert-danger p-0 mt-4" role="alert">
            <div v-for="(error,index) in errors" :key="index" class="text-danger mx-4 my-2">- {{ error }}</div> 
        </div>

        <div class="d-flex flex-column align-items-center justify-content-center">
            <!--//* @submit.prevent="sendForm()" nel form impedisce il ricaricamento della pagina quando viene inviato e, allo stesso tempo, chiama la funzione sendForm -->
            <form @submit.prevent="sendForm()">
                <div class="mb-3" style="width: 80vw">
                    <label for="name" class="fw-bold form-label">Name</label>
                    <input :class="{ 'is-invalid' : errors.name }" v-model.trim="store.name" type="text" name="name" class="form-control" placeholder="Margherita"> 
                    <div class="text-danger mt-1">{{errors.name}}</div>
                </div>
                <div class="mb-3" style="width: 80vw">
                    <label for="price" class="fw-bold form-label">Price</label>
                    <input :class="{ 'is-invalid' : errors.price }" v-model.trim="store.price" type="text" class="form-control" name="price" placeholder="10.50"> 
                    <div class="text-danger mt-1">{{errors.price}}</div>
                </div>
                <div class="mb-3" style="width: 80vw">
                    <label for="photo" class="fw-bold form-label">Image</label>
                    <input :class="{ 'is-invalid' : errors.photo }" v-model.trim="store.photo" type="text" class="form-control" name="photo" placeholder="https://www.marcobianchi.blog/wp-content/uploads/2023/01/pizza-2048x1365.jpg"> 
                    <div class="text-danger mt-1">{{errors.photo}}</div>
                </div>
                <div class="mb-3" style="width: 80vw">
                    <label for="description" class="fw-bold form-label">Description</label>
                    <textarea :class="{ 'is-invalid' : errors.description }" v-model.trim="store.description" type="text" name="description" class="form-control" placeholder="Description" cols="10" rows="10"></textarea> 
                    <div class="text-danger mt-1">{{errors.description}}</div>
                </div>
                <button type="submit" class="btn btn-danger-c" :btn-danger="sending" :disabled="sending">{{ sending ? 'Loading...' : 'Add' }}</button>
            </form>
        </div>

    </div>
</template>



<style scoped>
    .btn-danger-c{
        background-color: #ff001e !important;
        color: white;
    }
</style>
