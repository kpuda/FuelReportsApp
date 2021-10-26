package com.kp.fuelApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuelFetchDetails {

    private List<Fuel> fuelList;
    private BigDecimal recipeSum;
    private BigDecimal gasPerLiterMean;
    private BigDecimal recipeMean;

}
