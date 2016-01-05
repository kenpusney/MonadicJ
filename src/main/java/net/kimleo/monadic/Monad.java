package net.kimleo.monadic;

import java.util.function.Function;

public interface Monad<T> {
    <U, Mu extends Monad<U>> Mu bind(Function<T, Mu> func);
}
