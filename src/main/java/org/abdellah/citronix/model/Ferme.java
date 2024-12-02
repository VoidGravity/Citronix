package org.abdellah.citronix.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "ferme")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String localisation ;
    @Positive
    private double superficie;
    @PastOrPresent
    @NotNull
    private LocalDate dateCreation;
    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Champ> champs;
}
