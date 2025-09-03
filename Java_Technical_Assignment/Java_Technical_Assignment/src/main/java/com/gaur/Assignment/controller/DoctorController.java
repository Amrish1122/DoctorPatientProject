package com.gaur.Assignment.controller;

import com.gaur.Assignment.ExceptionHandler.DataNotFoundException;
import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.service.impl.DoctorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
@Tag(name = "Doctor Controller",description = "has rest endPoints of doctors")
public class DoctorController {

    @Autowired
    private DoctorServiceImpl doctorService;

    @Operation(operationId = "registerDoctor",
    summary = "adding a new doctor",
    description = "this restend point is used to create a new doctor")
    @ApiResponse(responseCode = "201",
    content = @Content(
                       mediaType = "application/json",
                       schema = @Schema(implementation = Doctor.class)))
    @PostMapping("/register")
    public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor){
        Doctor doctor1 = doctorService.createDoctor(doctor);
        if (doctor!=null){
            return new ResponseEntity<>("Doctor Registered Successfully", HttpStatus.CREATED);
        }else return new ResponseEntity<>("Doctor not registered",HttpStatus.BAD_REQUEST);
    }


    @Operation(operationId = "getDoctors",
            summary = "Getting all doctors",
            description = "this restend point is used fetch all doctor")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Doctor.class)))
    @GetMapping("/all_doctors")
    public ResponseEntity<List<Doctor>> findAll(){
        return new ResponseEntity<List<Doctor>>(doctorService.findAllDoctors(),HttpStatus.OK);
    }

    @Operation(operationId = "findDoctorByContactNumber",
            summary = "fetching doctor by contact number ",
            description = "this restend point is used to fetch by doctor contact number ")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Doctor.class)))
    @GetMapping("/find")
    public ResponseEntity<Doctor> findDoctorByPhoneNo(@RequestParam String phone){
        Doctor doctor = doctorService.findByDoctorNumber(phone);
        if (doctor!=null){
            return new ResponseEntity<>(doctor,HttpStatus.OK);
        }else throw new  DataNotFoundException("given id is not linked with any doctor");
    }

    @Operation(operationId = "findDoctorById",
            summary = "fetch a doctor by its id",
            description = "this restend point is used to fetch  doctor by id")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Doctor.class)))
    @GetMapping("/details")
    public ResponseEntity<Doctor>findDoctorById(@RequestParam Long id){
        Doctor doctor = doctorService.findDoctorById(id);
        if (doctor!=null){
            return new ResponseEntity<>(doctor,HttpStatus.OK);
        }else throw new  DataNotFoundException("given id is not linked with any doctor");
    }


    @Operation(operationId = "removeDoctor",
            summary = "remove doctor",
            description = "this restend point is used to remove doctor by id")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Doctor.class)))
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeDoctorById(@RequestParam Long id){
        boolean isRemoved = doctorService.deleteById(id);
        if (isRemoved){
            return new ResponseEntity<>("Doctor delete successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Doctor not found",HttpStatus.NOT_FOUND);
        }
    }

    @Operation(operationId = "updateDoctor",
            summary = "update existing doctor",
            description = "this restend point is used to update existing doctor")
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Doctor.class)))
    @PutMapping("/update")
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor,@RequestParam Long id){
        Doctor doctor1 = doctorService.updateDoctorById(doctor,id);
        if (doctor1!=null){
            return new ResponseEntity<>("Doctor update successfully ",HttpStatus.OK);
        }
        throw new DataNotFoundException("Doctor with  id : "+id+" not found");
    }
}
