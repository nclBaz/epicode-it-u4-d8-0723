import entities.User;
import functional_interfaces.StringModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
	public static void main(String[] args) {
/*		StringModifier starWrapper = str -> "*" + str + "*";
		StringModifier dotsWrapper = str -> "." + str + ".";
		StringModifier inverter = str -> {
			String[] splitted = str.split("");
			String inverted = "";
			for(int i= splitted.length -1; i >=0; i-- ) {
				inverted += splitted[i];
			}
			return inverted;
		};
		System.out.println(starWrapper.modify("CIAO"));
		System.out.println(dotsWrapper.modify("CIAO"));
		System.out.println(inverter.modify("CIAO"));*/

		// **************************** PREDICATES *****************************
		User aldo = new User("Aldo", "Baglio", 20);
		Predicate<Integer> isMoreThanZero = num -> num > 0;
		Predicate<Integer> isLessThanHundred = num -> num < 100;
		Predicate<User> isAgeLessThanFifty = user -> user.getAge() < 50;
		System.out.println(isMoreThanZero.test(100));
		System.out.println(isMoreThanZero.and(isLessThanHundred).test(400));
		System.out.println(isMoreThanZero.negate().test(100));
		System.out.println(isMoreThanZero.test(aldo.getAge()));
		System.out.println(isAgeLessThanFifty.test(aldo));

		// **************************** SUPPLIER *****************************
		Supplier<Integer> randomNumbersSupplier = () -> {
			Random rndm = new Random();
			return rndm.nextInt(1, 1001);
		};


		Supplier<User> userSupplier = () -> new User("NOME", "COGNOME", randomNumbersSupplier.get());

		List<User> usersList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			usersList.add(userSupplier.get());
		}

		// System.out.println(usersList);

		Supplier<List<User>> random100 = () -> {
			List<User> usersList2 = new ArrayList<>();
			for (int i = 0; i < 100; i++) {
				usersList2.add(userSupplier.get());
			}
			return usersList2;
		};

		usersList.forEach(user -> System.out.println(user));
		// usersList.forEach(System.out::println); // Identica alla riga precedente però un pelo più compatta

		usersList.stream().forEach(user -> System.out.println(user));

		// ************************************************** STREAMS - FILTER ***********************************
		List<Integer> randomIntegers = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			randomIntegers.add(randomNumbersSupplier.get());
		}
		System.out.println(randomIntegers);
		System.out.println("Elenco numeri > 0 e < 100");

		randomIntegers.stream().filter(num -> num > 0 && num < 100).forEach(num -> System.out.println(num));
		// la stessa operazione di sopra può essere anche effettuata con un Predicate se definito in precedenza
		randomIntegers.stream().filter(isMoreThanZero.and(isLessThanHundred)).forEach(num -> System.out.println(num));
	}
}