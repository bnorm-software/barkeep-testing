package com.bnorm.qt;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.quicktheories.quicktheories.api.Consumer5;
import org.quicktheories.quicktheories.api.QuadConsumer;
import org.quicktheories.quicktheories.api.TriConsumer;

public final class Consumers {
    private Consumers() {
    }

    private static AssertionError assertion(Exception exception) {
        return new AssertionError("Unexpected exception: " + exception.getMessage(), exception);
    }

    public static <A> Consumer<A> throwing(ThrowingConsumer<A> original) {
        return (a) -> {
            try {
                original.accept(a);
            } catch (Exception error) {
                throw assertion(error);
            }
        };
    }

    public static <A, B> BiConsumer<A, B> throwing(ThrowingConsumer2<A, B> original) {
        return (a, b) -> {
            try {
                original.accept(a, b);
            } catch (Exception error) {
                throw assertion(error);
            }
        };
    }

    public static <A, B, C> TriConsumer<A, B, C> throwing(ThrowingConsumer3<A, B, C> original) {
        return (a, b, c) -> {
            try {
                original.accept(a, b, c);
            } catch (Exception error) {
                throw assertion(error);
            }
        };
    }

    public static <A, B, C, D> QuadConsumer<A, B, C, D> throwing(ThrowingConsumer4<A, B, C, D> original) {
        return (a, b, c, d) -> {
            try {
                original.accept(a, b, c, d);
            } catch (Exception error) {
                throw assertion(error);
            }
        };
    }

    public static <A, B, C, D, E> Consumer5<A, B, C, D, E> throwing(ThrowingConsumer5<A, B, C, D, E> original) {
        return (a, b, c, d, e) -> {
            try {
                original.accept(a, b, c, d, e);
            } catch (Exception error) {
                throw assertion(error);
            }
        };
    }
}
