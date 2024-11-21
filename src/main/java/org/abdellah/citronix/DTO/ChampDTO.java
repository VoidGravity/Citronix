package org.abdellah.citronix.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChampDTO {
    private Long id;
    private String nom;
    private Double superficie;
    private Long fermeId;
    private List<ArbreDTO> arbres;
}