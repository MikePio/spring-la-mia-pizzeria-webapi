package org.java.app;

import java.time.LocalDate;

import org.java.app.db.auth.pojo.Role;
import org.java.app.db.auth.pojo.User;
import org.java.app.db.auth.service.RoleService;
import org.java.app.db.auth.service.UserService;
import org.java.app.db.pojo.Ingredient;
import org.java.app.db.pojo.Pizza;
import org.java.app.db.pojo.SpecialOffer;
import org.java.app.db.serv.IngredientService;
import org.java.app.db.serv.PizzaService;
import org.java.app.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// * STEP 6 vengono creati gli oggetti con i propri dati che verranno inseriti come righe della tabella nel database
// * implementare CommandLineRunner
// * aggiungere @Autowired private PizzaService pizzaService;
// * creare nel metodo run degli oggetti
@SpringBootApplication(scanBasePackages = "org.java.app")
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{

	@Autowired
	private PizzaService pizzaService;

	// importazione del file service nel file Application
	@Autowired
	private SpecialOfferService specialOfferService;
	
	// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.3/3.5 - INSERIRE DATI NEL DB --> importazione del file service nel file Application
	@Autowired
	private IngredientService ingredientService;
	
	// * STEP 6.2 - AUTHENTICATION - Importo il file RoleService nel file application
	@Autowired
	private RoleService roleService;

	// * STEP 6.3 - AUTHENTICATION - Importo il file UserService nel file application
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.4/3.5 - INSERIRE DATI NEL DB --> CREARE gli elementi da inserire nel db
		Ingredient pomodoro = new Ingredient("pomodoro");
		Ingredient mozzarella = new Ingredient("mozzarella");
		Ingredient prosciuttoCotto = new Ingredient("prosciutto cotto");
		Ingredient salamePiccante = new Ingredient("salame piccante");
		Ingredient funghi = new Ingredient("funghi");
		Ingredient salsiccia = new Ingredient("salsiccia");
		Ingredient carciofi = new Ingredient("carciofi");
		Ingredient olive = new Ingredient("olive");
		Ingredient pomodorini = new Ingredient("pomodorini");
		Ingredient gorgonzola = new Ingredient("gorgonzola");
		Ingredient fontina = new Ingredient("fontina");
		Ingredient provola = new Ingredient("provola");
		Ingredient acciughe = new Ingredient("acciughe");

		// * RELAZIONE MANY-TO-MANY / N-N - STEP 3.5/3.5 - INSERIRE DATI NEL DB --> SALVARE gli elementi da inserire nel db
		ingredientService.save(pomodoro);
		ingredientService.save(mozzarella);
		ingredientService.save(prosciuttoCotto);
		ingredientService.save(salamePiccante);
		ingredientService.save(funghi);
		ingredientService.save(salsiccia);
		ingredientService.save(carciofi);
		ingredientService.save(olive);
		ingredientService.save(pomodorini);
		ingredientService.save(gorgonzola);
		ingredientService.save(fontina);
		ingredientService.save(provola);
		ingredientService.save(olive);
		ingredientService.save(acciughe);
		
    // * RELAZIONE MANY-TO-MANY / N-N - STEP 4.5/4.5 - COLLEGARE GLI ID DELLE PIZZE CON GLI ID DEGLI INGREDIENTI NELLA TABELLA db_pizzeria_relationships DEL DB --> aggiungere gli ingredienti(ogetti) creati alle pizze(oggetti)
		Pizza margherita = new Pizza("Margherita", "Pomodoro e mozzarella", "margherita.jpg", 5.00f, pomodoro, mozzarella );
		Pizza cotto = new Pizza("Prosciutto Cotto", "Pomodoro, mozzarella e prosciutto cotto", "cotto.jpg", 6.50f, pomodoro, mozzarella, prosciuttoCotto);
		Pizza diavola = new Pizza("Diavola", "Pomodoro, mozzarella e salame piccante", "diavola.jpg", 7.00f, pomodoro, mozzarella, salamePiccante);
		Pizza funghiPizza = new Pizza("Funghi", "Pomodoro, mozzarella e funghi", "funghi.jfif", 6.00f, pomodoro, mozzarella, funghi);
		Pizza salsicciaPizza = new Pizza("Salsiccia", "Pomodoro, mozzarella e salsiccia", "salsiccia.jfif", 6.50f, pomodoro, mozzarella, salsiccia);
		Pizza capricciosa = new Pizza("Capricciosa", "Pomodoro, mozzarella, prosciutto cotto, funghi, carciofi, olive", "capricciosa.jfif", 8.00f, pomodoro, mozzarella, prosciuttoCotto, funghi, carciofi, olive);
		Pizza quattroStagioni = new Pizza("Quattro Stagioni", "Pomodoro, mozzarella, prosciutto cotto, funghi, carciofi, pomodorini", "quattro-stagioni.webp", 8.50f, pomodoro, mozzarella, prosciuttoCotto, funghi, carciofi, pomodorini);
		Pizza quattroFormaggi = new Pizza("Quattro Formaggi", "Pomodoro, mozzarella, gorgonzola, fontina, provola", "quattro-formaggi.jpg", 9.00f, pomodoro, mozzarella, gorgonzola, fontina, provola);
		Pizza napoli = new Pizza("Napoli", "Pomodoro, mozzarella, acciughe", "napoli.jfif", 7.50f, pomodoro, mozzarella, acciughe);
		
		pizzaService.save(margherita);
		pizzaService.save(cotto);
		pizzaService.save(diavola);
		pizzaService.save(funghiPizza);
		pizzaService.save(salsicciaPizza);
		pizzaService.save(capricciosa);
		pizzaService.save(quattroStagioni);
		pizzaService.save(quattroFormaggi);
		pizzaService.save(napoli);
		
		System.out.println("\n\nDati inseriti nella tabella del database\n\n");
	
		// oggetti SpecialOffer da inserire nel db //! TODO : SE C'è UN ERRORE BISOGNA AGGIORNARE LE DATE CON DATE FUTURE E NON INSERIRE DATE DEL PASSATO
		SpecialOffer specialOffer1 = new SpecialOffer(0, "Offerta Speciale 1", LocalDate.now(), LocalDate.parse("2030-10-23"), margherita);
		SpecialOffer specialOffer2 = new SpecialOffer(0, "Offerta Speciale 2", LocalDate.now(), LocalDate.parse("2030-03-27"), cotto);
		SpecialOffer specialOffer3 = new SpecialOffer(0, "Offerta Speciale 3", LocalDate.now(), LocalDate.parse("2030-08-15"), diavola);
		SpecialOffer specialOffer4 = new SpecialOffer(0, "Offerta Speciale 4", LocalDate.now(), LocalDate.parse("2030-10-26"), margherita);

		specialOfferService.save(specialOffer1);
		specialOfferService.save(specialOffer2);
		specialOfferService.save(specialOffer3);
		specialOfferService.save(specialOffer4);

		// * STEP 6.4 - AUTHENTICATION - crea due oggetti Role, uno chiamato "ADMIN" e l'altro "USER" e succesivamente vengono salvati nel db nel file application
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		
		roleService.save(admin);
		roleService.save(user);
		
		// * STEP 6.5 - AUTHENTICATION - utilizza un algoritmo di crittografia bcrypt con il metodo BCryptPasswordEncoder().encode per cifrare le password pwsAdmin, pwsUser
		final String pwsAdmin = new BCryptPasswordEncoder().encode("123");
		final String pwsUser = new BCryptPasswordEncoder().encode("123");
		
		// * STEP 6.6 - AUTHENTICATION - crea due oggetti User, uno chiamato "admin1" e l'altro "user1" e succesivamente vengono salvati nel db  nel file application
		User admin1 = new User("admin1", pwsAdmin, admin, user);
		User user1 = new User("user1", pwsUser, user);
		
		userService.save(admin1);
		userService.save(user1);
	}

}
