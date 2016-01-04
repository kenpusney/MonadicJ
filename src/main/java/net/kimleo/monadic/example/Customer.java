package net.kimleo.monadic.example;

public class Customer {
    final String name;
    final String nickName;

    public Customer(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getName() {
        return name;
    }


}
