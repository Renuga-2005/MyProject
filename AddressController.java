package com.example.assess;
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

import com.example.assess.response.ResponseGenerator;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAddress() {
        try {
            List<Address> addressList = addressService.getAlladdress();
            if (!addressList.isEmpty()) {
                return ResponseGenerator.successResponse("Data fetched successfully", addressList);
            } else {
                return ResponseGenerator.errorResponse("No addresses found");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getByaddressId(@RequestParam int addressId) {
        try {
            Optional<Address> address = addressService.getByaddressId(addressId);
            if (address.isPresent()) {
                return ResponseGenerator.successResponse("Address found successfully", address.get());
            } else {
                return ResponseGenerator.errorResponse("Address not found with ID: " + addressId);
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/create")
	public ResponseEntity<?> added(@Valid @RequestBody Address address) {
	    try {
	        Address added = addressService.saveAddress(address);
	        if (added != null) {
	            return ResponseGenerator.successResponse("Address is created successfully", added);
	        } else {
	            return ResponseGenerator.errorResponse("Address is not created");
	        }
	    } catch (Exception e) {
	        return ResponseGenerator.errorResponse("Address creation failed: " + e.getMessage());
	    }
	}
    @PutMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody Address request) {
        try {
            Address updated = addressService.updateAddress(request);
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
    public ResponseEntity<?> deleteAddress(@RequestParam int addressId) {
        try {
            boolean isDeleted = addressService.deleteAddressById(addressId);
            if (isDeleted) {
                return ResponseGenerator.successResponse("Address deleted successfully", "Deleted ID: " + addressId);
            } else {
                return ResponseGenerator.errorResponse("Address with ID " + addressId + " not found");
            }
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("An error occurred: " + e.getMessage());
        }
    }
}
