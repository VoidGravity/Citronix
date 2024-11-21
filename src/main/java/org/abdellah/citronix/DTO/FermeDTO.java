package org.abdellah.citronix.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class FermeDTO {
    private Long id;
    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;
    private Set<ChampDTO> champs;
}