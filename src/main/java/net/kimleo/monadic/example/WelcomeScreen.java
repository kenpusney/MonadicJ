package net.kimleo.monadic.example;

import net.kimleo.monadic.either.Either;
import net.kimleo.monadic.optional.Optional;
import net.kimleo.monadic.validate.Validate;

public class WelcomeScreen {

    public String welcome(Customer customer) {
        return Validate.validate(nameOf(customer).value())
                .where(name -> name.length() < 19 && name.length() >= 4)
                .where(name -> Character.isAlphabetic(name.charAt(0)))
                .where(name -> name.matches("^(\\w|\\d|\\s)+$"))
                .bind(name -> Optional.of(String.format("Hello %s", name))).value();
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
