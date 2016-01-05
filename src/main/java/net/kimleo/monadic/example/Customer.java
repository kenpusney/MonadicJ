package net.kimleo.monadic.example;

import net.kimleo.monadic.optional.Optional;

import static net.kimleo.monadic.optional.Optional.of;

public class Customer {
    private final Optional<String> name;
    private final Optional<String> nickName;

    public Customer(String name, String nickName) {
        this.name = of(name);
        this.nickName = of(nickName);
    }

    public Optional<String> getNickName() {
        return nickName;
    }

    public Optional<String> getName() {
        return name;
    }
}
