package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.FermeDTO;
import org.abdellah.citronix.model.Ferme;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    FermeDTO toDto(Ferme ferme);
    Ferme toEntity(FermeDTO dto);
    List<FermeDTO> toDtoList(List<Ferme> fermes);
}