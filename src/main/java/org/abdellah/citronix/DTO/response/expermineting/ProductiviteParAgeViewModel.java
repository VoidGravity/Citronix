package org.abdellah.citronix.DTO.response.expermineting;




import lombok.Builder;
import lombok.Data;




@Data
@Builder
public class ProductiviteParAgeViewModel {
    private String categorieAge;
    private int nombreArbres;
    private double productionMoyenne;
}
