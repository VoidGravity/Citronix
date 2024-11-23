package org.abdellah.citronix.DTO.request;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class VenteDTO {
    private Integer id;
    private LocalDate dateVente;
    private Double prixUnitaire;
    private String client;
    private Long recolteId;
    private Double revenu;
}