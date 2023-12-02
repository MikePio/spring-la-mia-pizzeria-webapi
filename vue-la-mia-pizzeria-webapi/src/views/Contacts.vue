<script>
import axios from 'axios';
import { store } from '../data/store';

export default {
    name:'Contacts',

    data(){
        return{

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

        <h2 class="mb-5 text-center">Contacts</h2>

        <div class="contact-card">
            <div class="contact-info">
                <h3 class="contact-name">Pizzeria Italia</h3>
                <p class="contact-email">pizzeriaitalia@example.com</p>
                <p class="contact-phone">+1234567890</p>
            </div>
            <div class="d-flex justify-content-center align-items-center mt-3">
                <!-- Instagram -->
                <a class="bg-dark btn btn-outline-light btn-floating m-1 rounded-circle"
                    href="https://www.instagram.com/michelepiopilla/" role="button"><i style="padding: 4px 0px;"
                        class="fab fa-instagram"></i></a>
                <!-- Linkedin -->
                <a class="bg-dark btn btn-outline-light btn-floating m-1 rounded-circle"
                    href="https://www.linkedin.com/in/michele-pilla/" role="button"><i style="padding: 4px 0px;"
                        class="fab fa-linkedin-in"></i></a>
                <!-- Github -->
                <a class="bg-dark btn btn-outline-light btn-floating m-1 rounded-circle" href="https://github.com/MikePio"
                    role="button"><i style="padding: 4px 0px;" class="fab fa-github"></i></a>
            </div>
        </div>

    </div>

</template>



<style lang="scss" scoped>
.contacts-heading {
    text-align: center;
    font-size: 2rem;
    margin-bottom: 20px;
}

.contact-card {
    background-color: #f5f5f5;
    border-radius: 8px;
    padding: 20px;
    margin-bottom: 20px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.contact-name {
    font-size: 1.5rem;
    margin-bottom: 5px;
}

.contact-email,
.contact-phone {
    font-size: 1.2rem;
    color: #777;
    margin-bottom: 5px;
}
</style>
