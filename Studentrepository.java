package com.example.assess;

//import com.example.assess.AllDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Studentrepository extends JpaRepository<Students, Integer> {

    @Query("""
        SELECT s.studentId AS studentId,
               s.name AS name,
               s.pno AS pno,
               s.email AS email,
               s.gender AS gender,
               a.doorNo AS doorNo,
               a.streetName AS streetName,
               a.city AS city,
               a.pinCode AS pincode,
               q.courseName AS courseName,
               q.coursePercentage AS coursePercentage
        FROM Students s
        JOIN Address a ON a.studentId = s.studentId
        JOIN Qualification q ON q.studentId = s.studentId
        WHERE s.studentId = :id
    """)
    List<AllDTO> getStudentDetailsById(@Param("id") int id);
}
