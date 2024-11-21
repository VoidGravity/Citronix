package org.abdellah.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "champ")
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    private String nom;
    @Positive
    @Min(1000)
    private Double superficie;


    @ManyToOne
    @JoinColumn(name = "ferme_id")
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL)
    private List<Arbre> arbres;

}
