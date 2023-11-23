<script>
export default {
  name: 'Header',

  data(){
    return{
      today: '',

    }
  },
  mounted() {
    const now = new Date();
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    this.today = now.toLocaleDateString('it-IT', options); 
  }

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
      <div>{{ today }}</div>
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
// @use '../scss/main.scss' as *;

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