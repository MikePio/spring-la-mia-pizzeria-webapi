package org.java.app.db.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// * spring-la-mia-pizzeria-webapi DAY 1 - STEP 2 - creare il controller (PizzaApiController) nel pacchetto delle API (org.java.app.db.api.controller)
@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizzeria-italia")
public class PizzaApiController {

  @Autowired
  private PizzaService pizzaService;

  // * spring-la-mia-pizzeria-webapi DAY 1 - STEP 3 - compilare il controller e stamapre hello world testare le API con `PostMan` o `Thunder Client` (estensione di Visual Studio Code) es. http://localhost:8080/api/pizza-test in GET
  // * spring-la-mia-pizzeria-webapi DAY 1 - STEP 4.3 - compilare il controller e stampare il json tramite le API con `PostMan` o `Thunder Client` (estensione di Visual Studio Code) es. http://localhost:8080/api/pizza-test in GET
  // * metodo per la ricerca di una pizza per nome esempio da cercare nelle api per mostrare la pizza margherita e ciò a cui è associato: http://localhost:8080/api/v1.0/pizzeria-italia?q=margherita
  @GetMapping
  public ResponseEntity<List<Pizza>> getAllPizzas(@RequestParam(required = false, name = "q") String query) {

    List<Pizza> pizzas = null;

    if(query == null){
      // * ricerca di tutte le pizze
      pizzas = pizzaService.findAll();
    }else{
      // * ricerca di una pizza per nome
      pizzas = pizzaService.findByName(query); // es. da cercare nelle api per mostrare la pizza margherita e ciò a cui è associato: http://localhost:8080/api/v1.0/pizzeria-italia?q=margherita
    }

    return new ResponseEntity<>(pizzas, HttpStatus.OK);
  }

  // * metodo per la ricerca di una pizza per id es. da cercare nelle api per mostrare la pizza con id = 2 http://localhost:8080/api/v1.0/pizzeria-italia/2
  @GetMapping("{id}")
  public ResponseEntity<Pizza> getPizzaById(@PathVariable int id) {
  
    // * Step 1 - Trova la pizza con l'ID specificato
    Optional<Pizza> optPizza = pizzaService.findById(id); // http://localhost:8080/api/v1.0/pizzeria-italia/2
  
    // * Step 2 - Se la pizza non esiste, restituisci un errore 404
    if (optPizza.isEmpty()) {

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  
    // * Step 3 - Se la pizza esiste, restituiscila con un codice di stato 200
    return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
  }
  




}
