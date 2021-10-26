package com.kp.fuelApp.Repository;

import com.kp.fuelApp.Entity.Fuel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class FuelRepositoryTest {

    @Autowired
    public FuelRepository fuelRepository;

    @Test
    public void addFuelRecipe(){
        Fuel fuel= Fuel.builder()
                .gasPricePerLiter(5.95)
                .gasQty(25.11)
                .gasType("PB95")
                .date(LocalDate.now())
                .build();
        BigDecimal gasCost=new BigDecimal(fuel.getGasPricePerLiter()*fuel.getGasQty()).setScale(2, RoundingMode.HALF_UP);
        fuel.setRecipeSum(Double.parseDouble(gasCost.toString()));
        fuelRepository.save(fuel);
    }

    @Test
    public void findAll(){
        List<Fuel> fuels=fuelRepository.findAll();
        Double fuelSumUp=fuels.stream().mapToDouble(fuel ->fuel.getRecipeSum()).sum();
        BigDecimal fuelSum=new BigDecimal(fuelSumUp).setScale(2,RoundingMode.HALF_UP);
        System.out.println(fuelSum);
    }

    @Test
    public void selectByFuelType(){
        List<Fuel> fuels=fuelRepository.findByFuelType("ON");
        Double fuelSumUp=fuels.stream().mapToDouble(fuel ->fuel.getRecipeSum()).sum();
        BigDecimal fuelSum=new BigDecimal(fuelSumUp).setScale(2,RoundingMode.HALF_UP);
        System.out.println(fuelSum);
    }

    @Test
    public void selectByDate(){
        List<Fuel> fuels=fuelRepository.findByDate("2021-01-21","2021-11-10");
        Double fuelSumUp=fuels.stream().mapToDouble(fuel ->fuel.getRecipeSum()).sum();
        BigDecimal fuelSum=new BigDecimal(fuelSumUp).setScale(2,RoundingMode.HALF_UP);
        System.out.println(fuelSum);
    }
}