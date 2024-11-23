package org.abdellah.citronix.DTO.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VenteSimpleViewModel {
    private Integer id;
    private LocalDate dateVente;
    private String client;
    private Double revenu;
}