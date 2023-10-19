package org.java.app.db.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// * spring-la-mia-pizzeria-webapi DAY 1 - STEP 2 - creare il controller (PizzaApiController) nel pacchetto delle API (org.java.app.db.api.controller)
@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaApiController {

  // * spring-la-mia-pizzeria-webapi DAY 1 - STEP 3 - testare le API con `PostMan` o `Thunder Client` (estensione di Visual Studio Code) es. http://localhost:8080/api/pizza-test in GET
  @GetMapping
  public ResponseEntity<String> test() {

    return new ResponseEntity<String>("hello world", HttpStatus.OK);
  }




}
