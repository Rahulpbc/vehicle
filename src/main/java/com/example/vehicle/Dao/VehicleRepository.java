package com.example.vehicle.Dao;

import com.example.vehicle.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
    Vehicle findVehicleById(Integer id);
    List<Vehicle> findVehicleByMake(String Make);
    List<Vehicle> findVehicleByModel(String Model);
    List<Vehicle> findVehicleByYear(Integer Year);
    List<Vehicle> findVehicleByModelAndMake(String Model,String Make);
    List<Vehicle> findVehicleByModelAndYear(String Model,Integer Year);
    List<Vehicle> findVehicleByMakeAndYear(String Make,Integer Year);
    List<Vehicle> findVehicleByMakeAndYearAndModel(String Make,Integer Year,String Model);
}
