package com.graduation.bird.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PredictResult {

    private int no;

    private int prediction;

    private String specie;

}
