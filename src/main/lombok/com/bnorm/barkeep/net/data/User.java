package com.bnorm.barkeep.net.data;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class User {

    private final String username;
    private final String password;
}
