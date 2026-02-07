package com.hrms.hrmslite.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {

    @NotBlank
    private String employeeId;

    @NotNull
    private LocalDate date;

    @NotNull
    private Boolean present;  // true = present, false = absent
}
