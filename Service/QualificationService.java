package com.example.assess.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assess.Entity.Qualification;
import com.example.assess.Repository.QualificationRepository;

@Service
public class QualificationService {
	@Autowired
	private QualificationRepository qualificationRepository;
	
       public List<Qualification> getAllqualification(){
    	   return qualificationRepository.findAll();
    	   
       }

       public Optional<Qualification> getByqualificationId(int qualificationId) {
   		return qualificationRepository.findById(qualificationId);
   	   }
       public Qualification saveQualification(Qualification qualification) {
		  return qualificationRepository.save(qualification);
	   }

	   public Qualification updateQualification( Qualification updatedQualification) {
		   Optional<Qualification> existingQualification = qualificationRepository.findById(updatedQualification.getQualificationId());


		           
		   if(existingQualification.isPresent()){
		    
		    existingQualification.get().setCourseName(updatedQualification.getCourseName());
		    existingQualification.get().setCoursePercentage(updatedQualification.getCoursePercentage());

		    return qualificationRepository.saveAndFlush(existingQualification.get());
		   }
		   return null;
		}
	   
	  
	   public boolean deleteQualificationById(int qualificationId) {
		    if (qualificationRepository.existsById(qualificationId)) {
		    	qualificationRepository.deleteById(qualificationId);
		        return true;
		    }
		    return false;
		}





}
