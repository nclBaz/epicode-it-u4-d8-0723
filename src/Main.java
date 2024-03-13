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
			return rndm.nextInt(1, 101);
		};

		List<Integer> randomIntegers = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			randomIntegers.add(randomNumbersSupplier.get());
		}
		System.out.println(randomIntegers);
		Supplier<User> userSupplier = () -> new User("NOME", "COGNOME", randomNumbersSupplier.get());

		List<User> usersList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			usersList.add(userSupplier.get());
		}

		System.out.println(usersList);

		Supplier<List<User>> random100 = () -> {
			List<User> usersList2 = new ArrayList<>();
			for (int i = 0; i < 100; i++) {
				usersList2.add(userSupplier.get());
			}
			return usersList2;
		};

		System.out.println(random100.get());
		System.out.println(random100.get());
	}
}