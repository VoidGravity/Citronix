package org.abdellah.citronix.model;




import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "ventes")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private LocalDate dateVente;

    @Positive
    private Double prixUnitaire;

    @NotBlank
    private String client;

    @ManyToOne
    private Recolte recolte;
}
