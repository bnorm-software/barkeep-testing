package com.bnorm.barkeep.net.data;

import org.quicktheories.quicktheories.core.Source;

import static org.quicktheories.quicktheories.generators.SourceDSL.strings;

public final class Sources {

    private Sources() {
    }

    public static Source<Bar> bars(Source<String> strings) {
        return Source.of(strings.combine(strings, Bar::create));
    }

    public static Source<Book> books(Source<String> strings) {
        return Source.of(strings.combine(strings, Book::create));
    }

    public static Source<String> types() {
        return strings().allPossible().ofLengthBetween(0, 20);
    }

    public static Source<String> titles() {
        return strings().allPossible().ofLengthBetween(0, 100);
    }

    public static Source<String> descriptions() {
        return strings().allPossible().ofLengthBetween(0, 1000);
    }


    // ===== Users ===== //

    public static Source<User> users(Source<String> strings) {
        return Source.of(strings.combine(strings, User::create));
    }

    public static Source<String> usernames() {
        return strings().allPossible().ofLengthBetween(0, 20);
    }

    public static Source<String> passwords() {
        return strings().allPossible().ofLengthBetween(0, 20);
    }
}
