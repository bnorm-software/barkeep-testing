package com.bnorm.qt;

public interface ThrowingConsumer2<A, B> {
    void accept(A a, B b) throws Exception;
}
