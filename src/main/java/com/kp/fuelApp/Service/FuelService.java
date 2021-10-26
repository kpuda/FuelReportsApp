package com.kp.fuelApp.Service;

import com.kp.fuelApp.Entity.Fuel;
import com.kp.fuelApp.Repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class FuelService {

    private FuelRepository fuelRepository;

    @Autowired
    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    public void addFuelReport(Fuel fuel){
        BigDecimal gasCost=new BigDecimal(fuel.getGasPricePerLiter()*fuel.getGasQty()).setScale(2, RoundingMode.HALF_UP);
        fuel.setRecipeSum(Double.parseDouble(gasCost.toString()));
        fuelRepository.save(fuel);
    }

    public String deleteReportById(Long id){
    if (fuelRepository.findById(id).isPresent()){
    fuelRepository.deleteById(id);
    return "Deleted report successfully";
    } else
    return "Something went wrong. Report not deleted";
    }
}
