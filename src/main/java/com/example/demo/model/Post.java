package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Post {
    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String text;

    @CreatedBy
    private String username;

    @NotNull
    private LocalDate createdAt = LocalDate.now();
}
