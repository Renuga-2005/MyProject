package com.example.assess.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assess.Entity.Address;
import com.example.assess.Repository.AddressRepo;

@Service
public class AddressService {
	@Autowired
	private AddressRepo AddressRepository;
	
       public List<Address> getAlladdress(){
    	   return AddressRepository.findAll();
    	   
       }

       public Optional<Address> getByaddressId(int addressId) {
   		return AddressRepository.findById(addressId);
   	   }
       public Address saveAddress(Address address) {
		  return AddressRepository.save(address);
	   }

	   public Address updateAddress( Address updatedaddress) {
		   Optional<Address> existingAddress = AddressRepository.findById(updatedaddress.getAddressId());


		           
		   if(existingAddress.isPresent()){
		    existingAddress.get().setAddressId(updatedaddress.getAddressId());
		    existingAddress.get().setDoorNo(updatedaddress.getDoorNo());
		    existingAddress.get().setCity(updatedaddress.getCity());
		    existingAddress.get().setPinCode(updatedaddress.getPinCode());

		    return AddressRepository.saveAndFlush(existingAddress.get());
		   }
		   return null;
		}
	   
	  
	   public boolean deleteAddressById(int addressId) {
		    if (AddressRepository.existsById(addressId)) {
		    	AddressRepository.deleteById(addressId);
		        return true;
		    }
		    return false;
		}





}
