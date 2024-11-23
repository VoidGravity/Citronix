package org.abdellah.citronix.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "recoltes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRecolte;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Saison saison;


    @NotNull
    @Positive
    private double quantiteTotale;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL)
    private List<RecolteDetail> recolteDetails;

    @OneToMany(mappedBy = "recolte")
    private List<Vente> ventes;

}