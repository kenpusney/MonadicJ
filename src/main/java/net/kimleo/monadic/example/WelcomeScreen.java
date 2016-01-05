package net.kimleo.monadic.example;


import net.kimleo.monadic.optional.Optional;

public class WelcomeScreen {

    public String welcome(Customer customer) {
        return nameOf(customer).value();
    }

    private Optional<String> nameOf(Customer customer) {
        return customer.getNickName()
                .or(customer.getName())
                .map(name -> String.format("Hello %s", name));
    }

}
