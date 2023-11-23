<script>
export default {
  name: 'Header',

  data(){
    return{
      message: ''


    }
  },
  methods: {
    setMessage() {
      const now = new Date();
      const dayOfWeek = now.getDay();
      const hours = now.getHours();
      const minutes = now.getMinutes();
      // const dayOfWeek = 2;
      // const hours = 19;
      // const hours = 23;
      // const minutes = 29;
      // const minutes = 30;

      console.log(now);
      console.log(dayOfWeek);
      console.log(hours);
      console.log(minutes);

      if ( //* "Pizzeria aperta fino alle 23:30" dal martedì alla domenica dalle ore 19:30 alle ore 23:29
        // ((dayOfWeek != 1) && (hours >= 0 && hours <= 18)) ||
        // ((dayOfWeek != 1) && ((hours == 19 && minutes >= 30) || (hours == 23 && minutes <= 29))) 
        // oppure
        ((dayOfWeek >= 2 || dayOfWeek <= 6 || dayOfWeek === 0) && (hours >= 20 && hours <= 22)) ||
        ((dayOfWeek >= 2 || dayOfWeek <= 6 || dayOfWeek === 0) && ((hours == 19 && minutes >= 30) || (hours == 23 && minutes <= 29))) 
      ) {
        this.message = `Pizzeria aperta fino alle 23:30`;
      } else if ( //* "La pizzeria riapre alle 19:30" dal martedì alla domenica, dalle ore 00:00 alle ore 19:29 e dalle 23:31 a 23:59
        // ((dayOfWeek != 1) && (hours >= 0 && hours <= 18)) ||
        // ((dayOfWeek != 1) && (((hours == 19 && minutes <= 29) && (dayOfWeek != 1 && dayOfWeek <= 1)) || ((hours == 23 && minutes >= 30) && (dayOfWeek != 1 && dayOfWeek >= 2 ) ))) 
        // oppure
        ((dayOfWeek >= 2 || dayOfWeek <= 6 || dayOfWeek === 0) && (hours >= 0 && hours <= 18)) ||
        ((dayOfWeek >= 2 || dayOfWeek <= 6 || dayOfWeek === 0) && (((hours == 19 && minutes <= 29) && (dayOfWeek != 1 && dayOfWeek <= 1)) || ((hours == 23 && minutes >= 30) && (dayOfWeek != 1 && dayOfWeek >= 2 ) ))) 
      ) {
        this.message = "La pizzeria riapre alle 19:30";
        //! in realtà la soluzione migliore sarebbe impostare per prima la condizione per questo messaggio (ho fatto questa soluzione alternativa solo per esercitarmi con la logica)
      } else { //* "La pizzeria riapre martedì alle 19:30" dalle 23:30 della domenica fino a 19:29 del martedì
        this.message = "La pizzeria riapre martedì alle 19:30";
        // this.message = "Error";
      }
    }
  },
  mounted() {
    this.setMessage();
    // aggiorna il messaggio ogni minuto
    setInterval(() => {
      this.setMessage();
    }, 60000);
  },

}
</script>

<template>
<header class="bg-dark" style="max-height: 165px;">
  
  <nav class="px-2 px-sm-5 d-flex justify-content-between" style="background-color: rgba(0, 0, 0, 0.2); height: 60px; max-height: 60px;">
    <!-- //* compare la sidebar da 576px in giù  -->
    <div class="side-toggle-btn my-auto me-auto">
      <button class="btn-dark-c" @click.prevent="toggleSidebar()"><i class="fa fa-bars"></i></button>
    </div>

    <!-- //* compaiono i link sopra i 576px  -->
    <ul class="nav-list">
      <li>
        <router-link :to="{name: 'home'}">Home</router-link>
      </li>
      <li>
        <!-- <router-link :to="{name: 'new-pizza'}">Add a New Pizza</router-link> -->
        <a href="#">Add a New Pizza</a>
      </li>
      <li>
        <!-- <router-link :to="{name: 'contacts'}">Contacts</router-link> -->
        <a href="#">Contacts</a>
      </li>
    </ul>
    <div class="d-flex justify-content-between align-items-center text-white">
      <div>{{ message }}</div>
    </div>
  </nav>

  <div class="d-flex align-items-center justify-content-center text-white" style="height: 90px; max-height: 90px;">
    <div class="text-center p-4">
      <h1>Pizzeria Italia</h1>
    </div>
  </div>

</header>
</template>

<style lang="scss" scoped>
@use '../scss/main.scss' as *;

header{
    nav{
      .side-toggle-btn{
        display: none;
      }
      ul{
        list-style: none;
        height: 100%;
        padding: 0;
        display: flex;
        align-items: center;
        li{
          margin: 0 15px 0 0;
          a{
            text-decoration: none;
            color: white;
            transition: all .3s;
            &:hover{
              color: white;
              text-shadow: 0 0 10px white;
            }
            &.active{
              text-shadow: 0 0 10px white;
              // color: darken($color: white, $amount: 30%);
            }
          }
        }
      }
    }
  }

.btn-dark-c{
  // background-color: #212529 !important;
  background-color: #1A1E21 !important;
  display: inline-block;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: 0.25rem;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  color: #fff;
  border: 1px solid transparent;
  text-decoration: none;
  transition: background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
}

// da 0px fino a 576px
@media screen and (max-width: 576px) {
    header{
      nav{
        .side-toggle-btn{
          display: block;
          }
        ul{
          display: none;
        }
      }
    }

}

</style>