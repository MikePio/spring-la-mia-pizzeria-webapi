package org.java.app.db.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.java.app.db.api.dto.PizzaDTO;
import org.java.app.db.api.response.ErrorResponse;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

// * spring-la-mia-pizzeria-webapi DAY 1 - STEP 2 - creare il controller (PizzaApiController) nel pacchetto delle API (org.java.app.db.api.controller)
@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizzeria-italia")
public class PizzaApiController {

  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private SpecialOfferService specialOfferService;
  
  // * soluzione + semplice per ottenere tutte le pizze SENZA LA SEARCH BAR // es. http://localhost:8080/api/v1.0/pizzeria-italia
  // @GetMapping
  // public ResponseEntity<List<Pizza>> getAllPizzas() {

  //   List<Pizza> pizzas = null;

  //   pizzas = pizzaService.findAll();

  //   return new ResponseEntity<>(pizzas, HttpStatus.OK);
  // }

  // * spring-la-mia-pizzeria-webapi DAY 1 - STEP 3 - compilare il controller e stampare hello world testare le API con `PostMan` o `Thunder Client` (estensione di Visual Studio Code) es. http://localhost:8080/api/pizza-test in GET
  // * spring-la-mia-pizzeria-webapi DAY 1 - STEP 4.3 - compilare il controller e stampare il json tramite le API con `PostMan` o `Thunder Client` (estensione di Visual Studio Code) es. http://localhost:8080/api/pizza-test in GET
  // * metodo per la ricerca di una pizza per nome esempio da cercare nelle api per mostrare la pizza margherita e ciò a cui è associato: http://localhost:8080/api/v1.0/pizzeria-italia?q=margherita
  @GetMapping
  public ResponseEntity<List<Pizza>> getAllPizzas(@RequestParam(required = false, name = "q") String query) {

    List<Pizza> pizzas = null;

    if(query == null || query.isEmpty()){
      // * ricerca di tutte le pizze
      pizzas = pizzaService.findAll();
    }else{
      // * ricerca di una pizza per nome
      pizzas = pizzaService.findByName(query); //* es. da cercare nelle api per mostrare la pizza margherita e ciò a cui è associato: http://localhost:8080/api/v1.0/pizzeria-italia?q=margherita
    }

    return new ResponseEntity<>(pizzas, HttpStatus.OK);
  }

  // * metodo per la ricerca di una pizza per id es. da cercare nelle api per mostrare la pizza con id = 2 http://localhost:8080/api/v1.0/pizzeria-italia/2
  @GetMapping("/pizzas/{id}")
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
  
  // * creazione di una nuova pizza in post
  // * su thunder client o postman inserendo l'url http://localhost:8080/api/v1.0/pizzeria-italia, scrivendo nel BODY il codice qui in basso e inviandolo in POST dovrebbe creare un nuovo oggetto(/pizza)
  // * es. codice da inserire nel Body di thunder client o postman
  // {
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccante",
  //   "photo": "diavola.jpg",
  //   "price": 10.0
  // }
  // * es. risultato generato dal codice scritto qui in alto
  // {
  //   "id": 4,
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccante",
  //   "photo": "diavola.jpg",
  //   "price": 10.0,
  //   "specialOffers": null,
  //   "ingredients": null,
  //   "formattedPrice": "10,00"
  // }

  //* CREATE SENZA la gestione dei messaggi di errore  */
	// @PostMapping
	// public ResponseEntity<Pizza> createPizza(@RequestBody PizzaDTO pizzaDto) {
		
  //   Pizza pizza = new Pizza(pizzaDto);

	// 	pizza = pizzaService.save(pizza);
		
	// 	return new ResponseEntity<>(pizza, HttpStatus.OK);
	// }
  
  //* CREATE CON la gestione dei messaggi di errore  */
  @PostMapping                             // @RequestBody indica l'oggetto da inviare in post
  public ResponseEntity<?> createPizza(@Valid @RequestBody PizzaDTO pizzaDto, BindingResult bindingResult, Model model) {
    
    // * verifica se ci sono errori, crea un HashMap e poi inserisce come chiave il campo e come valore il messaggio di errore
    if (bindingResult.hasErrors()) {
      Map<String, List<String>> errors = new HashMap<>();
      bindingResult.getFieldErrors().forEach(error -> {
        String field = error.getField();
        String message = error.getDefaultMessage();
        errors.computeIfAbsent(field, key -> new ArrayList<>()).add(message);
      });
      
      ErrorResponse errorResponse = new ErrorResponse(errors);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // * creazione oggetto 
    Pizza pizza = new Pizza(pizzaDto);
    
    //* verifico se il nome è unico e salvo l'oggetto nel db oppure stampo il messaggio di errore
    try {
      pizzaService.save(pizza);
      return new ResponseEntity<>(pizza, HttpStatus.OK);

    } catch (DataIntegrityViolationException e) {
      // CONSTRAIN VALIDATION (unique) cioè verifica se è univoco
      System.out.println("Error: " + e.getClass().getSimpleName());
  
      // creazione di una mappa per gli errori
      Map<String, List<String>> errors = new HashMap<>();
      // creazione di una lista per gli errori relativi al campo "name"
      List<String> nameErrors = new ArrayList<>();
      // aggiunta del messaggio di errore alla lista "nameErrors"
      nameErrors.add("The name must be unique");
      // inserimento della lista degli errori relativi al campo "name" nella mappa "errors"
      errors.put("name", nameErrors);
      
      // creazione dell'oggetto ErrorResponse con la mappa degli errori e restituzione come risposta
      ErrorResponse errorResponse = new ErrorResponse(errors);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
  }


  // * modifica di una pizza esistente con PUT
  // * su thunder client o postman inserendo l'url http://localhost:8080/api/v1.0/pizzeria-italia/ + edit/ + l'id della pizza da modificare, scrivendo nel BODY il codice qui in basso e inviandolo in PUT dovrebbe modificare l'oggetto/la pizza selezionato/a
  // * (es. url per la modifica della pizza con id 2:  http://localhost:8080/api/v1.0/pizzeria-italia/edit/2)
  // * es. codice da inserire nel Body di thunder client o postman
  // {
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccante",
  //   "photo": "diavola.jpg",
  //   "price": 10.0
  // }
  // * es. risultato generato dal codice scritto qui in alto
  // {
  //   "id": 2,
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccante",
  //   "photo": "cotto.jpg",
  //   "price": 10.0,
  //   "specialOffers": [
  //     {
  //       "id": 2,
  //       "title": "Offerta Speciale 2",
  //       "startDate": "2023-10-20",
  //       "endDate": "2024-03-27",
  //       "htmlStartDate": "2023-10-20",
  //       "htmlEndDate": "2024-03-27",
  //       "formattedStartDate": "20/10/2023",
  //       "formattedEndDate": "27/03/2024"
  //     }
  //   ],
  //   "ingredients": [
  //     {
  //       "id": 1,
  //       "name": "pomodoro",
  //       "nameWithInitialCapital": "Pomodoro"
  //     },
  //     {
  //       "id": 2,
  //       "name": "mozzarella",
  //       "nameWithInitialCapital": "Mozzarella"
  //     },
  //     {
  //       "id": 3,
  //       "name": "prosciutto cotto",
  //       "nameWithInitialCapital": "Prosciutto cotto"
  //     }
  //   ],
  //   "formattedPrice": "10,00"
  // }
  @PutMapping("/edit/{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable int id, @RequestBody PizzaDTO pizzaDto) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		pizza.fillFromDto(pizzaDto);
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

  // * cancellazione di una pizza esistente 
  // * su thunder client o postman inserendo l'url http://localhost:8080/api/v1.0/pizzeria-italia/ + delete/ + l'id della pizza da ELIMINARE e inviandolo in DELETE dovrebbe ELIMINARE l'oggetto/la pizza selezionato/a
  // * (es. url per l'eliminazione della pizza con id 2:  http://localhost:8080/api/v1.0/pizzeria-italia/delete/2)
  @DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deletePizza(@PathVariable int id) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
      return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

		Pizza pizza = optPizza.get();

    // eliminazione degli ingredienti associati ad una pizza - dichiaro la lista di offerte speciali associate a una pizza ottenuta con il metodo findByPizza in modo da poterla ciclare per eliminare le offerte speciali associate alla pizza specificata
		List<SpecialOffer> specialOffers = specialOfferService.findByPizza(pizza);
    // eliminazione degli ingredienti associati ad una pizza - ciclo la lista per ottenere ogni offerta speciale associata alla pizza (perché possono esserci più offerte speciali associate ad una pizza e non una sola)
    for (SpecialOffer specialOffer : specialOffers) {
      // eliminazione degli ingredienti associati ad una pizza - elimina nella tabella special_offer gli ingredienti associati/collegati alla pizza
      specialOfferService.delete(specialOffer);
    }
		
		pizzaService.deletePizza(pizza);
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
