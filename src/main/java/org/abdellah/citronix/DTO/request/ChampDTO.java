package org.abdellah.citronix.DTO.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChampDTO {
    private Long id;
    private String nom;
    private Double superficie;
    private Long fermeId;
    private List<ArbreDTO> arbres;
}
