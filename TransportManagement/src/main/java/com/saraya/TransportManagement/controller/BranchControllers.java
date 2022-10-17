package com.saraya.TransportManagement.controller;

import com.saraya.TransportManagement.exception.ResourceNotFoundException;
import com.saraya.TransportManagement.models.Branch;
import com.saraya.TransportManagement.models.User;
import com.saraya.TransportManagement.repository.BranchRepository;
import com.saraya.TransportManagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/api")
    public class BranchControllers {

        private final UserRepository repo;

        private final BranchRepository branchRepository;


        public BranchControllers(UserRepository repo, BranchRepository branchRepository) {
            this.repo = repo;
            this.branchRepository = branchRepository;
        }



        @GetMapping({ "/branch/{branchId}" })
        public ResponseEntity<Branch> getBranchById(@PathVariable(value = "branchId") Long branchId) {
            Branch branch = branchRepository.findById(branchId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Branch with id = " + branchId));

            return new ResponseEntity<>(branch, HttpStatus.OK);
        }


        @GetMapping("/branch/{userId}/branch")
        public ResponseEntity<Branch> getBranchByUserId(@PathVariable(value = "userId") Long userId) {

            User user= repo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Branch with id = " + userId));

            Branch branch = branchRepository.findByManagerId(userId);
            return new ResponseEntity<>(branch, HttpStatus.OK);
        }

   /* @GetMapping("/branch")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }*/


        @PostMapping("/manager/{userId}/branch")
        public ResponseEntity<Branch> createBranch(@PathVariable(value = "userId") Long userId,

                                                   @RequestBody Branch branchRequest) {

            User manager = repo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found manager with id = " + userId));


            branchRequest.setManager(manager);
            Branch branch = branchRepository.save(branchRequest);

            return new ResponseEntity<>(branch, HttpStatus.CREATED);
        }

        @PutMapping("/branch/{branchId}")
        public ResponseEntity<Branch> updateBranch(@PathVariable("branchId") long branchId,  @PathVariable(value = "userId") Long userId,
                                                             @RequestBody Branch branchRequest) {
           Branch branch = branchRepository.findById(branchId)
                    .orElseThrow(() -> new ResourceNotFoundException("Id " + branchId + " not found"));

           branch.setName(branchRequest.getName());
            branch.setCountry(branchRequest.getCountry());
            branch.setCity(branchRequest.getCity());


            return new ResponseEntity<>(branchRepository.save(branch), HttpStatus.OK);
        }

        @DeleteMapping("/branch/{branchId}")
        public ResponseEntity<HttpStatus> deleteBranch(@PathVariable("branchId") long branchId) {
            branchRepository.deleteById(branchId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }


