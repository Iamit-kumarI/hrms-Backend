package com.hrms.hrmslite.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "attendance")
public class Attendance {

    @Id
    private String id;

    private String employeeId;   // reference to Employee

    private LocalDate date;      // attendance date

    private boolean present;     // true = present, false = absent
}
