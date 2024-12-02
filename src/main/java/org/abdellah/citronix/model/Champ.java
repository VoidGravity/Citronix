package org.abdellah.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "champ")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String nom;
    @Positive
    @DecimalMin(value = "0.1", message = "La superficie minimale est de 0.1 hectare")
    private Double superficie;


    @ManyToOne
    @JoinColumn(name = "ferme_id")
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Arbre> arbres;

}
