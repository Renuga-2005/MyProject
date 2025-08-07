package com.example.assess.Controller;

import com.example.assess.Entity.Students;
import com.example.assess.Service.Studentservice;
import com.example.assess.dto.AllDTO;
//import com.example.assess.AllDTO;
import com.example.assess.dto.Studentfulldto;
import com.example.assess.response.ResponseGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private Studentservice studentService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStudent() {
        try {
            List<Students> students = studentService.getAllStudents();
            if (!students.isEmpty()) {
                return ResponseGenerator.successResponse("Data fetch success", students);
            }
            return ResponseGenerator.errorResponse("No students found");
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Error: " + e.getMessage());
        }
    }

    @GetMapping("/details/getById")
    public ResponseEntity<?> getDetails(@RequestParam int studentId) {
        try {
            Studentfulldto dto = studentService.getStudentWithAllDetails(studentId);
            if (dto != null) {
                return ResponseGenerator.successResponse("Student found", dto);
            }
            return ResponseGenerator.errorResponse("Student not found with ID: " + studentId);
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Error: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createStudent(@Valid @RequestBody AllDTO studentDTO) {
        try {
            Students createStudent = studentService.addStudent(studentDTO);
            return ResponseGenerator.successResponse("Student added successfully", createStudent);
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Error while adding: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody AllDTO requestDto) {
        try {
            Students updatedStudent = studentService.addStudent(requestDto);
            if (updatedStudent != null) {
                return ResponseGenerator.successResponse("Student updated successfully", updatedStudent);
            }
            return ResponseGenerator.errorResponse("Student update failed");
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStudent(@RequestParam int studentId) {
        try {
            boolean isDeleted = studentService.deleteStudent(studentId);
            if (isDeleted) {
                return ResponseGenerator.successResponse("Student deleted successfully", studentId);
            }
            return ResponseGenerator.errorResponse("Student not found");
        } catch (Exception e) {
            return ResponseGenerator.errorResponse("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getquery")
    public ResponseEntity<?> getqueryStudent(@RequestParam int studentId) {
        try {
            List<AllDTO> students = studentService.getquery(studentId);
            if (!students.isEmpty()) {
                return ResponseGenerator.successResponse("Data fetch success", students);
            }
            return ResponseGenerator.errorResponse("No data found");
        }  catch (Exception e) {
            return ResponseGenerator.errorResponse("Exception caught: " + e.getMessage());
        }
    }
}
