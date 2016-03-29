package com.bnorm.barkeep.net.data;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class Book {

    private long id;
    private String type;
    private final String title;
    private final String description;
}
