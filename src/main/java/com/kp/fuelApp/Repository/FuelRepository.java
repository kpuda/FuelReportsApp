package com.kp.fuelApp.Repository;

import com.kp.fuelApp.Entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Long> {

    @Query(
        value = "SELECT * FROM fuel WHERE gas_type=?",
            nativeQuery = true
    )
    List<Fuel> findByFuelType(String gasType);

    @Query(
            value = "SELECT * FROM fuelapp.fuel where date between ? and ?",
            nativeQuery = true
    )
    List<Fuel> findByDate(String startDate,String endDate);

}
