package com.kp.fuelApp.Controller;

import com.kp.fuelApp.Entity.Fuel;
import com.kp.fuelApp.Service.FuelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@SpringBootTest
class FuelControllerTest {

    public FuelService fuelService;
@Autowired
    public FuelControllerTest(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @Test
    public void addRaport(){
        Fuel fuel= Fuel.builder()
                .gasPricePerLiter(5.99)
                .gasQty(28.05)
                .gasType("PB95")
                .date(LocalDate.now())
                .build();
        BigDecimal gasCost=new BigDecimal(fuel.getGasPricePerLiter()*fuel.getGasQty()).setScale(2, RoundingMode.HALF_UP);
        fuel.setRecipeSum(Double.parseDouble(gasCost.toString()));
        fuelService.addFuelReport(fuel);
    }

}