package org.abdellah.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;



@Entity
@Table(name = "arbres")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate datePlantation;

    @ManyToOne
    @JoinColumn(name = "champ_id")
    private Champ champ;
    
    @Enumerated(EnumType.STRING)
    private ArbreStatus status;

    @OneToMany(mappedBy = "arbre")
    private List<RecolteDetail> recolteDetails;
}