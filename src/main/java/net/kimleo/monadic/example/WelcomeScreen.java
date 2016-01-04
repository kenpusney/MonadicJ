package net.kimleo.monadic.example;

public class WelcomeScreen {

    public String welcome(Customer customer) {
        if (customer.getNickName() != null) {
            return hello(customer.getNickName());
        } else if (customer.getName() != null) {
            return hello(customer.getName());
        }
        return hello("Anonymous");
    }

    private String hello(String name) {
        return String.format("Hello %s", name);
    }
}
