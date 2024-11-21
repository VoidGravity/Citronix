package org.abdellah.citronix.DTO;

import lombok.Builder;
import lombok.Data;
import org.abdellah.citronix.DTO.RecolteDetailDTO;
import org.abdellah.citronix.DTO.VenteDTO;
import org.abdellah.citronix.model.Saison;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class RecolteDTO {
    private Long id;
    private LocalDate dateRecolte;
    private Saison season;
    private double quantiteTotale;
    private List<RecolteDetailDTO> recolteDetails;
    private List<VenteDTO> ventes;
}