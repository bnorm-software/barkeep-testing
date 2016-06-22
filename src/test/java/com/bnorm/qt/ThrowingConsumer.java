package com.bnorm.qt;

public interface ThrowingConsumer<A> {
    void accept(A a) throws Exception;
}
