package com.hrms.hrmslite.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users") // MongoDB collection name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id; // MongoDB uses String/ObjectId for IDs

    private String username; // unique constraint handled in code/service if needed

    private String password;

    private Role role; // Enum stored as string by default
}
