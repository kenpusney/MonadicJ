package net.kimleo.monadic.optional;

import net.kimleo.monadic.Monad;

import java.util.function.Function;

public interface Optional<T> extends Monad<T> {
    T value();

    Optional<T> or(Optional<T> other);

    static <T> Optional<T> of(T value) {
        return value == null ? new NullValue<>() : new Value<>(value);
    }

    static <T> Optional<T> empty() {
        return new NullValue<>();
    }

    class NullValue<T> implements Optional<T> {

        @Override
        public Optional<T> or(Optional<T> other) {
            return other;
        }

        @Override
        public T value() {
            return null;
        }

        @Override
        public <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func) {
            return (Mu) Optional.empty();
        }
    }

    class Value<T> implements Optional<T> {
        private final T value;

        public Value(T value) {
            this.value = value;
        }

        @Override
        public Optional<T> or(Optional<T> other) {
            return this;
        }

        @Override
        public T value() {
            return value;
        }

        @Override
        public <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func) {
            return func.apply(value);
        }
    }
}

