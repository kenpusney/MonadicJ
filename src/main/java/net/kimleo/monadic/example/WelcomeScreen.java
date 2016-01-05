package net.kimleo.monadic.example;

import net.kimleo.monadic.either.Either;
import net.kimleo.monadic.optional.Optional;

public class WelcomeScreen {

    public String welcome(Customer customer) {
        if (validate(customer).right()) {
            return hello(customer).value();
        }
        return null;
    }

    private Optional<String> hello(Customer customer) {
        return nameOf(customer)
                .bind(name -> Optional.of(String.format("Hello %s", name)));
    }

    private Optional<String> nameOf(Customer customer) {
        return customer.getNickName()
                .or(customer.getName());
    }

    public Either<String, Boolean> validate(Customer customer) {
        String name = nameOf(customer).value();
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
