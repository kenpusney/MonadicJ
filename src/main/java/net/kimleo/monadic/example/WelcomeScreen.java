package net.kimleo.monadic.example;

import net.kimleo.monadic.either.Either;

public class WelcomeScreen {

    public Either<String, String> welcome(Customer customer) {
        return Either.right(customer.getNickName()
                        .or(customer.getName()).value())
                .where("Customer name length must between 4 and 18",
                        name -> name.length() < 19 && name.length() >= 4)
                .where("Customer name must start with letters",
                        name -> Character.isAlphabetic(name.charAt(0)))
                .where("Customer name should not contain special characters",
                        name -> name.matches("^(\\w|\\d|\\s)+$"))
                .bind(name -> Either.right(String.format("Hello %s", name)));
    }

}
