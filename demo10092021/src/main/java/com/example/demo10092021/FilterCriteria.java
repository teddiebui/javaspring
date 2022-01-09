package com.example.demo10092021;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriteria {
    private String identity;
    private String field;
    private String operator;
    private Object value; // arrays, list, object

}
