package com.hrms.hrmslite.controller;

import com.hrms.hrmslite.dto.AttendanceDto;
import com.hrms.hrmslite.entity.Attendance;
import com.hrms.hrmslite.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<Attendance> markAttendance(@Valid @RequestBody AttendanceDto dto) {
        Attendance saved = attendanceService.markAttendance(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByEmployee(employeeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable String id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }
    @GetMapping("/summary")
    public ResponseEntity<Map<String, List<String>>> getAttendanceSummary() {
        List<Attendance> allAttendance = attendanceService.getAllAttendance();

        List<String> presentEmployees = allAttendance.stream()
                .filter(Attendance::isPresent)
                .map(Attendance::getEmployeeId)
                .toList();

        List<String> absentEmployees = allAttendance.stream()
                .filter(a -> !a.isPresent())
                .map(Attendance::getEmployeeId)
                .toList();

        Map<String, List<String>> summary = new HashMap<>();
        summary.put("present", presentEmployees);
        summary.put("absent", absentEmployees);

        return ResponseEntity.ok(summary);
    }
    @GetMapping("/summary/{employeeId}")
    public ResponseEntity<Map<String, Object>> getEmployeeAttendanceSummary(@PathVariable String employeeId) {
        List<Attendance> employeeAttendance = attendanceService.getAttendanceByEmployee(employeeId);

        long presentCount = employeeAttendance.stream().filter(Attendance::isPresent).count();
        long absentCount = employeeAttendance.size() - presentCount;

        Map<String, Object> summary = new HashMap<>();
        summary.put("employeeId", employeeId);
        summary.put("totalDays", employeeAttendance.size());
        summary.put("presentDays", presentCount);
        summary.put("absentDays", absentCount);
        summary.put("records", employeeAttendance);

        return ResponseEntity.ok(summary);
    }
}
