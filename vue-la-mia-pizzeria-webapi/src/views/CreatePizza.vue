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

        }
    },

    components:{


    },

    methods:{
        sendForm() {
            this.sending = true;

            const newPizza = {
                name: store.name,
                price: store.price,
                photo: store.photo,
                description: store.description
            };

            axios.post(store.apiUrl, newPizza)
                .then(result => {
                    this.sending = false;
                    this.success = true;
                    this.resetForm();    
                    console.log('Pizza created: ', result);                
                })
                .catch(error => {
                    this.sending = false;
                    console.error('Error creating pizza: ', error);
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

        <div class="d-flex flex-column align-items-center justify-content-center">
            <!--//* @submit.prevent="sendForm()" nel form impedisce il ricaricamento della pagina quando viene inviato e, allo stesso tempo, chiama la funzione sendForm -->
            <form @submit.prevent="sendForm()">
                <div class="mb-3" style="width: 80vw">
                    <label for="name" class="fw-bold form-label">Name</label>
                    <input v-model.trim="store.name" type="text" name="name" class="form-control" placeholder="Margherita">
                    <!-- <input :class="{ 'is-invalid' : errors.name }" v-model.trim="store.name" type="text" name="name" class="form-control" placeholder="Margherita"> -->
                    <!-- <p v-for="(error,index) in errors.name" :key="index" class="text-danger">{{ error }}</p> -->
                </div>
                <div class="mb-3" style="width: 80vw">
                    <label for="price" class="fw-bold form-label">Price</label>
                    <input v-model.trim="store.price" type="number" class="form-control" name="price" placeholder="10.50€">
                    <!-- <input :class="{ 'is-invalid' : errors.price }" v-model.trim="store.price" type="number" class="form-control" name="price" placeholder="10.50€"> -->
                    <!-- <p v-for="(error,index) in errors.price" :key="index" class="text-danger">{{ error }}</p> -->
                </div>
                <div class="mb-3" style="width: 80vw">
                    <label for="photo" class="fw-bold form-label">Image</label>
                    <input v-model.trim="store.photo" type="text" class="form-control" name="photo" placeholder="https://www.marcobianchi.blog/wp-content/uploads/2023/01/pizza-2048x1365.jpg">
                    <!-- <input :class="{ 'is-invalid' : errors.photo }" v-model.trim="store.photo" type="text" class="form-control" name="photo" placeholder="name.png"> -->
                    <!-- <p v-for="(error,index) in errors.photo" :key="index" class="text-danger">{{ error }}</p> -->
                </div>
                <div class="mb-3" style="width: 80vw">
                    <label for="description" class="fw-bold form-label">Description</label>
                    <textarea v-model.trim="store.description" type="text" name="description" class="form-control" placeholder="Description" cols="10" rows="10"></textarea>
                    <!-- <textarea :class="{ 'is-invalid' : errors.description }" v-model.trim="store.description" type="text" name="description" class="form-control" placeholder="Description" cols="10" rows="10"></textarea> -->
                    <!-- <p v-for="(error,index) in errors.description" :key="index" class="text-danger">{{ error }}</p> -->
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
