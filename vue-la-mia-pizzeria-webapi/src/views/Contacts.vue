<script>
import axios from 'axios';
import { store } from '../data/store';

export default {
    name:'Contacts',

    data(){
        return{
            name: '',
            price: '',
            photo: '',
            description: '',

        }
    },

    components:{
        
    
    },

    methods:{

        

    },
}
</script>

<template>
    <div class="container-inner d-flex flex-column align-items-center justify-content-start" style=" max-width: 90%;">
    <!-- oppure con mx-auto -->
    <!-- <div class="container-inner mx-auto"> -->

        <h2 class="mb-4 text-center">Contacts</h2>

    </div>

</template>



<style>

</style>
