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
  deleteMessage: false,


  // methods
  toggleSidebar() {
    store.showSidebar = !store.showSidebar;
    // console.log(store.showSidebar);
  },
  // funzione per eliminare una pizza
  deletePizza(id, redirect) {
    store.loaded = false;
    //*rotta definita in PizzaApiController del progetto Spring
    axios.delete(store.apiUrl+ "/delete/" + id)
      .then(response => {
        console.log('Successfully deleted pizza:', response.data);
        // reindirizzo alla pagina home dove ci sono tutte le pizze
        redirect; //* this.$router è un'istanza fornita da Vue Router (Vue Router gestisce la navigazione all'interno di un'applicazione Vue) 
        
        store.setMessageAndReset();
      })
      .catch(error => {
        console.error('Error while deleting the pizza:', error);
      })
      .finally(() => {
        // codice che deve essere eseguito indipendentemente dal successo o dall'errore della richiesta
        store.loaded = true;
      });
  },
  setMessageAndReset() {
    store.deleteMessage = true;

    setTimeout(() => {
      store.deleteMessage = false;
    }, 4000); // 4 secondi
  }
})
