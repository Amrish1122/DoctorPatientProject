package com.gaur.Assignment.controller;

import com.gaur.Assignment.ExceptionHandler.DataNotFoundException;
import com.gaur.Assignment.entity.Doctor;
import com.gaur.Assignment.entity.Patient;
import com.gaur.Assignment.service.DoctorService;
import com.gaur.Assignment.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {


    @Autowired
    private PatientServiceImpl patientService;
    @Autowired
    private  DoctorService doctorService;


    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(@RequestBody Patient request){
        Patient patient = patientService.createPatient(request);
        boolean isRegistered = true;
        if (patient==null){
            patientService.createPatient(request);
            isRegistered=false;
        }
      ResponseEntity<?> doctorList =  data(request);
        //data(request).getBody();
        return ResponseEntity.ok(doctorList.getBody());
     //   return new ResponseEntity<SuggestedDoctorDto>(PatientMapper.suggestedDoctorDto(request,doctorList,isRegistered));


    }
    public ResponseEntity< ?> data (Patient request){
        List<String> customList = new ArrayList<>();
        String city = request.getCity();
        List<Doctor> doctors = doctorService.suggestDoctorBySymptoms(request.getSymptom())
                .stream().
                filter(doctor ->
                        doctor.getCity() != null
                                && doctor.getCity()
                                .toLowerCase()
                                .equals(request.getCity().toLowerCase()))
                .toList();
        customList=doctors.stream().map(Doctor::getCity).collect(Collectors.toList());
                //.filter(doctor -> doctor.getCity().toLowerCase().equalsIgnoreCase(city)).toList();
        boolean exists=customList.stream().anyMatch(c->c.equalsIgnoreCase(city));
        if (exists){
            if (doctors.isEmpty()){
               return new ResponseEntity<>(
                       "there isn't any doctor present at your location for your symptom",
                       HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>( doctors,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(
                    "We are still waiting to expand to your location",HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/all_patients")
    public ResponseEntity<List<Patient>> findAll(){
        return new ResponseEntity<>(patientService.findAllPatient(),HttpStatus.OK) ;
    }

    @GetMapping("/sug_doc")
    public ResponseEntity<?> findByPatientId(@RequestParam Long id){
        Patient patient = patientService.findById(id);
        if (patient!=null){
            ResponseEntity<?> doctors = data(patient);
            return ResponseEntity.ok(doctors.getBody());
        }else
            throw new DataNotFoundException("Patient id is not valid");
    }

    @GetMapping("/details")
    public ResponseEntity<Patient> findPatientDetailsById(@RequestParam Long id){
        Patient patient = patientService.findById(id);
        if (patient!=null){
            return ResponseEntity.ok(patient);
        }else {
            throw new DataNotFoundException("Patient id is not valid");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updatePatient(@RequestBody Patient req,@RequestParam Long id){
        Patient patient = patientService.updatePatientById(req,id);
        if (patient!=null){
            return ResponseEntity.ok("patient update successfully");
        }
        throw new DataNotFoundException("Patient id is not valid");
    }


    @DeleteMapping("/remove")
    public ResponseEntity<String> removePatientById(@RequestParam Long id){
        Boolean isRemoved = patientService.deletePatientById(id);
        if (isRemoved){
            return ResponseEntity.ok("patient removed successfully");
        }
        return new ResponseEntity<>("Patient not found ", HttpStatus.NOT_FOUND);
    }
}
