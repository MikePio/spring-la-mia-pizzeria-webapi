package org.java.app.db.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.db.api.dto.PizzaDTO;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  
  // * creazione di una nuova pizza in post
  // * su thunder client o postman inserendo l'url http://localhost:8080/api/v1.0/pizzeria-italia, scrivendo nel BODY il codice qui in basso e inviandolo in POST dovrebbe creare un nuovo oggetto(/pizza)
  // * es. codice da inserire nel Body di thunder client o postman
  // {
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccanta",
  //   "photo": "diavola.jpg",
  //   "price": 10.0
  // }
  // * es. risultato generato dal codice scritto qui in alto
  // {
  //   "id": 4,
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccanta",
  //   "photo": "diavola.jpg",
  //   "price": 10.0,
  //   "specialOffers": null,
  //   "ingredients": null,
  //   "formattedPrice": "10,00"
  // }
	@PostMapping
	public ResponseEntity<Pizza> createPizza(@RequestBody PizzaDTO pizzaDto) {
		
		Pizza pizza = new Pizza(pizzaDto);
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

  // * modifica di una pizza esistente con PUT
  // * su thunder client o postman inserendo l'url http://localhost:8080/api/v1.0/pizzeria-italia/ + l'id della pizza da modificare, scrivendo nel BODY il codice qui in basso e inviandolo in PUT dovrebbe creare un nuovo oggetto(/pizza)
  // * (es. url per la modifica della pizza con id 2:  http://localhost:8080/api/v1.0/pizzeria-italia/2)
  // * es. codice da inserire nel Body di thunder client o postman
  // {
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccanta",
  //   "photo": "diavola.jpg",
  //   "price": 10.0
  // }
  // * es. risultato generato dal codice scritto qui in alto
  // {
  //   "id": 2,
  //   "name": "test pizza creata",
  //   "description": "Pomodoro, mozzarella e salame piccanta",
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
  @PutMapping("{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable int id, @RequestBody PizzaDTO pizzaDto) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Pizza pizza = optPizza.get();
		pizza.fillFromDto(pizzaDto);
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

}
