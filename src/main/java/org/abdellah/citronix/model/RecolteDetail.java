package org.abdellah.citronix.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "harvest_details")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecolteDetail
{

    @Id @GeneratedValue
    private Integer id;
    private Double quantite;

    @ManyToOne
    private Arbre arbre;

    @ManyToOne
    private Recolte recolte;
}
