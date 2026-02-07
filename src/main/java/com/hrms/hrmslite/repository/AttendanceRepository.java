package com.hrms.hrmslite.repository;

import com.hrms.hrmslite.entity.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {

    List<Attendance> findByEmployeeId(String employeeId);

    List<Attendance> findByDate(LocalDate date);

    List<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date);
}
