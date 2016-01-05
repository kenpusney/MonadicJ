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

    public Either<String, Boolean> validate(Customer customer) {
        String name = customer.getNickName()
                .or(customer.getName()).value();
        if (name.length() > 18 || name.length() < 4) {
            return Either.left("Customer name length must between 4 and 18");
        } else if (!Character.isAlphabetic(name.charAt(0))) {
            return Either.left("Customer name must start with letters");
        } else if (!name.matches("^(\\w|\\d|\\s)+$")) {
            return Either.left("Customer name should not contain special characters");
        }
        return Either.right(true);
    }
}
