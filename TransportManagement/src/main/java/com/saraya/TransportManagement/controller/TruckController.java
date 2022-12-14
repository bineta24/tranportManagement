package com.saraya.TransportManagement.controller;

import com.saraya.TransportManagement.exception.ResourceNotFoundException;
import com.saraya.TransportManagement.models.Branch;
import com.saraya.TransportManagement.models.Truck;

import com.saraya.TransportManagement.models.User;
import com.saraya.TransportManagement.repository.BranchRepository;
import com.saraya.TransportManagement.repository.TruckRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TruckController {

    private final TruckRepository repo;

    private final BranchRepository branchRepository;

    public TruckController(TruckRepository repo, BranchRepository branchRepository) {
        this.repo = repo;
        this.branchRepository = branchRepository;
    }



    @GetMapping("/trucks")
    public List<Truck> getAllTrucks(){
        return repo.findAll();
    }

    @GetMapping({"/truck/{truckId}"})
    public ResponseEntity<Truck> getTruckById(@PathVariable(value = "truckId") Long truckId) {
        Truck truck = repo.findById(truckId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Truck with id = " + truckId));

        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    @GetMapping("/truck/{branchId}/truck")
    public ResponseEntity<List<Truck>> getAllTruckByBranchId(@PathVariable(value = "branchId") Long branchId) {
        if (!branchRepository.existsById(branchId)) {
            throw new ResourceNotFoundException("Not found Branch with id = " + branchId);
        }

        List<Truck> branch = repo.findByBranchBranchId(branchId);
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }




    @PostMapping("/branch/{branchId}/truck")
    public ResponseEntity<Truck> createTruck(@PathVariable(value = "branchId") Long branchId,

                                             @RequestBody Truck truckRequest) {

      Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found manager with id = " + branchId));


        truckRequest.setBranch(branch);
        Truck truck = repo.save(truckRequest);

        return new ResponseEntity<>(truck, HttpStatus.CREATED);
    }

    @PutMapping("/truck/{truckId}")
    public ResponseEntity<Truck> updateTruck(@PathVariable("truckId") long truckId ,
                                             @RequestBody Truck truckRequest) {
        Truck truck = repo.findById(truckId)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + truckId + " not found"));

        truck.setModel(truckRequest.getModel());
        truck.setNo(truckRequest.getModel());
        truck.setInsurance(truckRequest.getModel());
        truck.setCapacity(truckRequest.getCapacity());




        return new ResponseEntity<>(repo.save(truck), HttpStatus.OK);
    }

    @DeleteMapping("/truck/{truckId}")
    public ResponseEntity<HttpStatus> deleteTruck(@PathVariable("truckId") long truckId) {
        repo.deleteById(truckId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}