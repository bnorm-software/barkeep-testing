package com.bnorm.barkeep.net.data;

import lombok.Data;

@Data
public class Book {

    private String id;
    private String type;
    private final String title;
    private final String description;
    private String createStamp;
    private String modifyStamp;
}
