package net.kimleo.monadic.either;

import net.kimleo.monadic.Monad;

import java.util.function.Function;

public interface Either<E, T> extends Monad<T> {
    @Override
    <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func);

    static <E, T> Either<E, T> left(E e) {
        return new Left<>(e);
    }

    static <E, T> Either<E, T> right(T t) {
        return new Right<>(t);
    }

    E left();

    T right();

    boolean valid();

    class Left<E, T> implements Either<E, T> {
        private final E extra;

        public Left(E extra) {
            this.extra = extra;
        }

        @Override
        public <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func) {
            return (Mu) Either.<E, U>left(extra);
        }

        @Override
        public E left() {
            return extra;
        }

        @Override
        public T right() {
            return null;
        }

        @Override
        public boolean valid() {
            return false;
        }
    }

    class Right<E, T> implements Either<E, T> {
        private T value;

        public Right(T value) {
            this.value = value;
        }

        @Override
        public <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func) {
            return func.apply(value);
        }

        @Override
        public E left() {
            return null;
        }

        @Override
        public T right() {
            return value;
        }

        @Override
        public boolean valid() {
            return true;
        }
    }
}
