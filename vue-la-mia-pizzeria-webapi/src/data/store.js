import {reactive} from "vue";
import axios from "axios"

export const store = reactive({
  //* collegamento con le Api della repo laravel-api
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



  // methods
  toggleSidebar() {
    store.showSidebar = !store.showSidebar;
    // console.log(store.showSidebar);
  }
})
