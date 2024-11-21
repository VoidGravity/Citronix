package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.ArbreDTO;
import org.abdellah.citronix.model.Arbre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    ArbreDTO toDto(Arbre arbre);
    Arbre toEntity(ArbreDTO dto);
    List<ArbreDTO> toDtoList(List<Arbre> arbres);
}