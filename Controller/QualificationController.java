package com.example.assess.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.assess.Entity.Qualification;
import com.example.assess.Service.QualificationService;
import com.example.assess.response.ResponseGenerator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/qualification")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAddress() {
        try {
            List<Qualification> qualificationList = qualificationService.getAllqualification();
            if (!qualificationList.isEmpty()) {
                return ResponseGenerator.successResponse("Data fetched successfully", qualificationList);
            } else {
                return ResponseGenerator.errorResponse("No addresses found");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getByqualificationId(@RequestParam int qualificationId) {
        try {
            Optional<Qualification> qualification = qualificationService.getByqualificationId(qualificationId);
            if (qualification.isPresent()) {
                return ResponseGenerator.successResponse("Qualification found successfully", qualification.get());
            } else {
                return ResponseGenerator.errorResponse("Qualification not found with ID: " + qualificationId);
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/create")
	public ResponseEntity<?> added(@Valid @RequestBody Qualification qualification) {
	    try {
	        Qualification added = qualificationService.saveQualification(qualification);
	        if (added != null) {
	            return ResponseGenerator.successResponse("Qualification is created successfully", qualification);
	        } else {
	            return ResponseGenerator.errorResponse("Qualification is not created");
	        }
	    } catch (Exception e) {
	        return ResponseGenerator.errorResponse("Qualification creation failed: " + e.getMessage());
	    }
	}
    @PutMapping("/update")
    public ResponseEntity<?> updateQualification(@RequestBody Qualification request) {
        try {
            Qualification updated = qualificationService.updateQualification(request);
            if (updated != null) {
                return ResponseGenerator.successResponse("Address updated successfully", updated);
            } else {
                return ResponseGenerator.errorResponse("Address update failed");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteQualification(@RequestParam int qualificationId) {
        try {
            boolean isDeleted = qualificationService.deleteQualificationById(qualificationId);
            if (isDeleted) {
                return ResponseGenerator.successResponse("Address deleted successfully", "Deleted ID: " + qualificationId);
            } else {
                return ResponseGenerator.errorResponse("Address with ID " + qualificationId + " not found");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }
}
