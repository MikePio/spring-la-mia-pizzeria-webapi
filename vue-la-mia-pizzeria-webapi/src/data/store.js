import {reactive} from "vue";
import axios from "axios"

export const store = reactive({
  //* collegamento con le Api del progetto Spring
  apiUrl: 'http://localhost:8080/api/v1.0/pizzeria-italia',

  // sidebar
  showSidebar: false,
  // data
  pizzas: [],
  search: '',
  name: '',
  price: '',
  photo: '',
  description: '',
  // loader
  loaded: false,
  searched: false,


  // methods
  toggleSidebar() {
    store.showSidebar = !store.showSidebar;
    // console.log(store.showSidebar);
  }
})
