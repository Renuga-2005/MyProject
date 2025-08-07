package com.example.assess.Service;



import com.example.assess.Entity.Students;
import com.example.assess.Repository.AddressRepo;
import com.example.assess.Repository.QualificationRepository;
import com.example.assess.Repository.Studentrepository;
import com.example.assess.dto.AllDTO;
//import com.example.assess.AllDTO;
import com.example.assess.dto.Studentfulldto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Studentservice {

    @Autowired
    private Studentrepository studentRepository;

    @Autowired
    private AddressRepo addressRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    public Studentfulldto getStudentWithAllDetails(int id) {
        Optional<Students> studentdto = studentRepository.findById(id);
        if (studentdto.isPresent()) {
            Students student = studentdto.get();
            Studentfulldto dto = new Studentfulldto();
            dto.setStudentId(student.getStudentId());
            dto.setStudentName(student.getName());
            dto.setGender(student.getGender());
            dto.setPno(student.getPno());
            dto.setEmail(student.getEmail());
            dto.setAddress(addressRepository.findByStudentId(id));
            dto.setQualification(qualificationRepository.findByStudentId(id));
            return dto;
        }
        return null;
    }

    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Students> getByStudentId(int id) {
        return studentRepository.findById(id);
    }

    public Students addStudent(AllDTO dto) {
        Students student = new Students();
        student.setStudentId(dto.studentId());
        student.setName(dto.name());
        student.setGender(dto.gender());
        student.setPno(dto.pno());
        student.setEmail(dto.email());
        return studentRepository.save(student);
    }

    public boolean deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Students updateStudent(AllDTO dto) {
        Optional<Students> existingStudent = studentRepository.findById(dto.studentId());
        if (existingStudent.isPresent()) {
            Students student = existingStudent.get();
            student.setName(dto.name());
            student.setGender(dto.gender());
            student.setPno(dto.pno());
            student.setEmail(dto.email());
            return studentRepository.saveAndFlush(student);
        }
        return null;
    }

    public List<AllDTO> getquery(int id) {
        return studentRepository.getStudentDetailsById(id);
    }
}
