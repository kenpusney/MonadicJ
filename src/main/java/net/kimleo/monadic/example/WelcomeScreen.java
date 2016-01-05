package net.kimleo.monadic.example;


import net.kimleo.monadic.optional.Optional;

import java.util.regex.Matcher;

public class WelcomeScreen {

    public String welcome(Customer customer) {
        return hello(customer).value();
    }

    private Optional<String> hello(Customer customer) {
        return nameOf(customer)
                .map(name -> String.format("Hello %s", name));
    }

    private Optional<String> nameOf(Customer customer) {
        return customer.getNickName()
                .or(customer.getName());
    }

    public boolean validate(Customer customer) throws ValidationException {
        String name = nameOf(customer).value();
        if (name.length() > 18 || name.length() < 4) {
            throw new ValidationException("Customer name length must between 4 and 18");
        } else if (name.matches("^\\w")) {
            throw new ValidationException("Customer name must start with letters");
        } else if (!name.matches("^(\\w|\\d|\\s)+$")) {
            throw new ValidationException("Customer name should not contain special characters");
        }
        return true;
    }
}
