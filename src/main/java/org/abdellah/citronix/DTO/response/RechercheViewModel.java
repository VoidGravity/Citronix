package org.abdellah.citronix.DTO.response;





import lombok.Builder;
import lombok.Data;
import java.util.*;






@Data
@Builder
public class RechercheViewModel {
    private Long id;
    private String type; // "Ferme", "Champ", "Arbre", etc.
    private String nom;
    private String description;
    private Map<String, String> attributsSupplementaires;
}