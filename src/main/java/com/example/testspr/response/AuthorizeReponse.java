package com.example.testspr.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeReponse {
    private Integer errorcode;
    private String errorinfo;
}
