package net.kimleo.monadic.optional;

import java.util.function.Function;

public interface Optional<T> {
    T value();

    <U> Optional<U> map(Function<T, U> func);

    Optional<T> or(Optional<T> other);

    static <T> Optional<T> of(T value) {
        return value == null ? new NullValue() : new Value<>(value);
    }
}

class NullValue<T> implements Optional<T> {
    @Override
    public <U> Optional<U> map(Function<T, U> func) {
        return new NullValue<>();
    }

    @Override
    public Optional<T> or(Optional<T> other) {
        return other;
    }

    @Override
    public T value() {
        return null;
    }
}

class Value<T> implements Optional<T> {
    private final T value;

    public Value(T value) {
        this.value = value;
    }

    @Override
    public <U> Optional<U> map(Function<T, U> func) {
        return Optional.of(func.apply(value));
    }

    @Override
    public Optional<T> or(Optional<T> other) {
        return this;
    }

    @Override
    public T value() {
        return value;
    }
}
