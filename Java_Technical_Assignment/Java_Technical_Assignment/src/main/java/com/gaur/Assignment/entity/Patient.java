package com.gaur.Assignment.entity;

import com.gaur.Assignment.enums.Symptom;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 3,max = 40,message = "name must contain atleast 40 characters")
    private String name;

    @NotBlank
   @Size(min = 3,max = 40)
    private String city;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{10}",message = "Invalid contact number please check it again")
    private String number;

  private Symptom symptom;
}
