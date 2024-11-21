package org.abdellah.citronix.DTO;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class RecolteDetailDTO {
    private Integer id;
    private Double quantite;
    private Long arbreId;
    private Long recolteId;
}
