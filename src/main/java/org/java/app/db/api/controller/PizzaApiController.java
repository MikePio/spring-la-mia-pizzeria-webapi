package org.java.app.db.api.controller;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
  @GetMapping
  public ResponseEntity<List<Pizza>> getAllPizzas() {

    List<Pizza> pizzas = pizzaService.findAll();

    return new ResponseEntity<>(pizzas, HttpStatus.OK);
  }




}
