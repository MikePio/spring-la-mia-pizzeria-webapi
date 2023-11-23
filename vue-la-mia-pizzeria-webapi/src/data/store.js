import {reactive} from "vue";
import axios from "axios"

export const store = reactive({
  //* collegamento con le Api della repo laravel-api
  apiUrl: 'http://api/v1.0/pizzeria-italia/',


})
