package com.hrms.hrmslite.service;

import com.hrms.hrmslite.dto.AttendanceDto;
import com.hrms.hrmslite.entity.Attendance;
import com.hrms.hrmslite.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Attendance markAttendance(AttendanceDto dto) {
        Attendance attendance = Attendance.builder()
                .employeeId(dto.getEmployeeId())
                .date(dto.getDate())
                .present(dto.getPresent())
                .build();
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendanceByEmployee(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public void deleteAttendance(String id) {
        attendanceRepository.deleteById(id);
    }
}
