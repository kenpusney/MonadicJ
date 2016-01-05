package net.kimleo.monadic.validate;

import net.kimleo.monadic.Monad;
import net.kimleo.monadic.either.Either;
import net.kimleo.monadic.optional.Optional;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Validate<T> extends Monad<T> {
    @Override
    <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func);

    static <T> Validate<T> validate(T t) {
        return new Validator<>(t);
    }

    Validate<T> where(Predicate<T> pred);

    class Validator<T> implements Validate<T> {
        private final T value;

        public Validator(T value) {
            this.value = value;
        }

        @Override
        public <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func) {
            return func.apply(value);
        }

        @Override
        public Validate<T> where(Predicate<T> pred) {
            if (value != null  && pred.test(value)) {
                return this;
            } else {
                return new Invalid<>();
            }
        }
    }
    class Invalid<T> implements Validate<T> {
        @Override
        public <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func) {
            return (Mu) Optional.empty();
        }

        @Override
        public Validate<T> where(Predicate<T> pred) {
            return new Invalid();
        }
    }
}
