package com.bnorm.qt;

public interface ThrowingConsumer3<A, B, C> {
    void accept(A a, B b, C c) throws Exception;
}
