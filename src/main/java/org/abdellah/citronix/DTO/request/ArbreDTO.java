package org.abdellah.citronix.DTO.request;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ArbreDTO {
    private Long id;
    private LocalDate datePlantation;
    private Long champId;
    private Integer age;
    private Double productiviteParSaison;
}
