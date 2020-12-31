package com.kaplan.ecommerce.customer.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The field 'numberID' cannot be null")
    @Size(min=8, max=8, message = "The size of 'numberId' must be 8")
    @Column(name = "number_id", unique = true, length = 8, nullable = false)
    private String numberID;

    @NotEmpty(message = "The field 'firstName' cannot be null")
    @Column(name = "first_name", nullable = false);
    private String firstName;

    @NotEmpty(message = "The field 'firstName' cannot be null")
    @Column(name = "last_name", nullable = false);
    private String lastName;

    @NotEmpty(message = "The field 'email' cannot be null")
    @Email(message = "Invalid mail address")
    @Column(unique = true, nullable = false);
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "The field 'location' cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Location location;

    @NotEmpty
    private String state;
}
