package org.abdellah.citronix.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ArbreViewModel {
    private Long id;
    private LocalDate datePlantation;
    private String champNom;
    private Integer age;
    private String categorie; // "Jeune", "Mature", "Vieux", "Non productif"
    private Double productiviteParSaison;
    private boolean estProductif;
}