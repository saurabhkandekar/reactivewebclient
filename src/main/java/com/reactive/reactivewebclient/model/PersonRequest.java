package com.reactive.reactivewebclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest  {
    private String name;
    private String gender;

}
