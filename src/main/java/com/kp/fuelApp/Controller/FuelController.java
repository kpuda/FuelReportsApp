package com.kp.fuelApp.Controller;

import com.kp.fuelApp.Entity.Fuel;
import com.kp.fuelApp.Entity.FuelFetchDetails;
import com.kp.fuelApp.Repository.FuelRepository;
import com.kp.fuelApp.Service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController(value = "/")
public class FuelController {

    private FuelRepository fuelRepository;
    private FuelService fuelService;

    @Autowired
    public FuelController(FuelRepository fuelRepository, FuelService fuelService) {
        this.fuelRepository = fuelRepository;
        this.fuelService = fuelService;
    }

    //POSTMAPPING
    @PostMapping("/addFuelReport")
    public void addFuelReport(@RequestBody Fuel fuel){
        BigDecimal gasCost=new BigDecimal(fuel.getGasPricePerLiter()*fuel.getGasQty()).setScale(2, RoundingMode.HALF_UP);
        fuel.setRecipeSum(Double.parseDouble(gasCost.toString()));
        fuelService.addFuelReport(fuel);
    }

    @DeleteMapping("/deleteReport/{id}")
    public String deleteFuelReport(@PathVariable("id") Long id){
        return fuelService.deleteReportById(id);
    }

    //GETMAPPING
    @GetMapping("/all")
    public FuelFetchDetails showAll(){
        FuelFetchDetails details = new FuelFetchDetails();
        details.setFuelList(fuelRepository.findAll());
        details.setGasPerLiterMean(
                new BigDecimal(
                        (details.getFuelList().stream()
                                .mapToDouble(fuel ->fuel.getGasPricePerLiter()).sum())/details.getFuelList().size()).setScale(2, RoundingMode.HALF_UP)
        );
        details.setRecipeSum(
                new BigDecimal(
                        (details.getFuelList().stream()
                                .mapToDouble(fuel ->fuel.getRecipeSum()).sum()))
        );
        details.setRecipeMean(
                new BigDecimal(
                        (details.getFuelList().stream()
                                .mapToDouble(fuel -> fuel.getRecipeSum()).sum())/details.getFuelList().size())
        );
        details.toString();
        return details;
    }

    @GetMapping("/byType/{type}")
    public FuelFetchDetails showByFuelType(@PathVariable("type") String type){
        FuelFetchDetails detailsByType=new FuelFetchDetails();
        detailsByType.setFuelList(fuelRepository.findByFuelType(type));
        detailsByType.setGasPerLiterMean(
                new BigDecimal(
                        (detailsByType.getFuelList().stream()
                                .mapToDouble(fuel->fuel.getGasPricePerLiter()).sum())/detailsByType.getFuelList().size())
        );
        detailsByType.setRecipeSum(
                new BigDecimal(
                        detailsByType.getFuelList().stream()
                                .mapToDouble(fuel->fuel.getRecipeSum()).sum()
                )
        );
        detailsByType.setRecipeMean(
                new BigDecimal(
                        (detailsByType.getFuelList().stream().mapToDouble(fuel->fuel.getRecipeSum()).sum())/detailsByType.getFuelList().size()
                )
        );
        return detailsByType;
    }

    @GetMapping("/byDate/")
    public FuelFetchDetails showByDates(@RequestParam String startDate,@RequestParam  String endDate) {
        FuelFetchDetails fetchByDate=new FuelFetchDetails();
        fetchByDate.setFuelList(fuelRepository.findByDate(startDate,endDate));
        fetchByDate.setGasPerLiterMean(
                new BigDecimal(
                        (fetchByDate.getFuelList().stream()
                                .mapToDouble(fuel ->fuel.getGasPricePerLiter()).sum())/fetchByDate.getFuelList().size()).setScale(2, RoundingMode.HALF_UP)
        );
        fetchByDate.setRecipeSum(
                new BigDecimal(
                        (fetchByDate.getFuelList().stream()
                                .mapToDouble(fuel ->fuel.getRecipeSum()).sum()))
        );
        fetchByDate.setRecipeMean(
                new BigDecimal(
                        (fetchByDate.getFuelList().stream()
                                .mapToDouble(fuel -> fuel.getRecipeSum()).sum())/fetchByDate.getFuelList().size())
        );
        fetchByDate.toString();
        return fetchByDate;
    }
}
