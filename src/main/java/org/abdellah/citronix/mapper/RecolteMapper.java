package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.RecolteDTO;
import org.abdellah.citronix.model.Recolte;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecolteMapper {
    RecolteDTO toDto(Recolte recolte);
    Recolte toEntity(RecolteDTO dto);
    List<RecolteDTO> toDtoList(List<Recolte> recoltes);
}