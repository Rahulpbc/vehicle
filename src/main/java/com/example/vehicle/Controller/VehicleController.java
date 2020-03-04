package com.example.vehicle.Controller;

import com.example.vehicle.Dao.VehicleRepository;
import com.example.vehicle.Model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//The controller part of the MVC framework

@RestController
public class VehicleController {

    //Instantiating the vehicle object (The object is created automatically)
    @Autowired
    private VehicleRepository vehicle;

    //The following method is the implementation of the 'CREATE vehicles' method
    @RequestMapping(value="/createVehicle",method = RequestMethod.POST,produces ={"text/plain"})
    public String createVehicle(@RequestBody Vehicle newVehicle) {
        String respo = "";
        Integer id = newVehicle.getId();
        Integer year = newVehicle.getYear();
        String make = newVehicle.getMake();
        String model = newVehicle.getModel();
        if (vehicle.findVehicleById(id).getId() == id) {
            respo = "Vehicle already exists";
        } else {
            vehicle.save(newVehicle);
            respo = "new vehicle inserted";
        }
        return respo;
    }

    //Implementation of the 'DELETE vehicles/{id}' method
    @RequestMapping(value="/deleteVehicle",method = RequestMethod.POST,produces ={"text/plain"})
    public String deleteVehicle(@RequestBody Vehicle deleteVehicle) {
        String respo="";
        Integer id=deleteVehicle.getId();
        if (vehicle.findVehicleById(id).getId() == id) {
            vehicle.delete(deleteVehicle);
            respo = "Deleted entry";
        } else {
            respo = "new such vehicle exits";
        }
        return respo;
    }

    //Implementation of the 'GET vehicles/{id}' methods (filtering is implemented, data can be filtered on id and/or make and/or model and/or year
    @RequestMapping(value="/getVehicle",method = RequestMethod.GET,produces ={"application/json"})
    public List<Vehicle>getVehicle(@RequestParam(required = false) String make, @RequestParam(required = false) String model, @RequestParam(required = false) String id, @RequestParam(required = false) String year) {

        //filtering by id
        if(id != null)
        {
            Vehicle singleVehicle=vehicle.findVehicleById(Integer.valueOf(id));
            List<Vehicle> v=new ArrayList<>();
            v.add(singleVehicle);
            return v;
        }

        //The following conditional statements handle the filtering of the search on the various vehicle properties
        //filtering by make, model and year
        if((model != null) && (year != null) && (make != null))
        {
            return vehicle.findVehicleByMakeAndYearAndModel(make,Integer.valueOf(year),model);
        }

        //filtering by make and model
        if((make != null) && (model != null))
        {
            return vehicle.findVehicleByModelAndMake(model,make);
        }

        //filtering by make and year
        if((make != null) && (year != null))
        {
            return vehicle.findVehicleByMakeAndYear(make,Integer.valueOf(year));
        }

        //filtering by model and year
        if((model != null) && (year != null))
        {
            return vehicle.findVehicleByModelAndYear(model,Integer.valueOf(year));
        }

        //filtering by make
        if(make != null)
        {
            return vehicle.findVehicleByMake(make);
        }

        //filtering by model
        if(model != null)
        {
            return vehicle.findVehicleByModel(model);
        }

        //filtering by year
        if(year != null)
        {
            return vehicle.findVehicleByYear(Integer.valueOf(year));
        }

        return null;

    }

    //Implementation of the 'GET vehicles' method
    @RequestMapping(value="/getVehicles",method = RequestMethod.GET,produces ={"application/json"})
    public List<Vehicle> getVehicle() {
        List<Vehicle> allvehicle=vehicle.findAll();
        return allvehicle;
    }

    //Implementation of the 'UPDATE vehicles' method
    @RequestMapping(value="/updateVehicle",method = RequestMethod.POST,produces ={"text/plain"})
    public String updateVehicle(@RequestBody Vehicle updateVehicle) {
        String respo = "";

        Vehicle existingVehicle=vehicle.findVehicleById(updateVehicle.getId());

        if (existingVehicle.getId() == updateVehicle.getId()) {

            if(updateVehicle.getYear()!=null)
            {
                existingVehicle.setYear(updateVehicle.getYear());
            }
            if(updateVehicle.getMake()!=null)
            {
                existingVehicle.setMake(updateVehicle.getMake());
            }
            if(updateVehicle.getModel()!=null)
            {
                existingVehicle.setModel(updateVehicle.getModel());
            }
            vehicle.save(existingVehicle);
            respo = "Updated Sucessfully";
        } else {
            respo = "no such vehicle exists";
        }
        return respo;
    }


}
