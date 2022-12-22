package com.example.dimaproject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ArgsNeuronPojo {
    private String date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;

}
