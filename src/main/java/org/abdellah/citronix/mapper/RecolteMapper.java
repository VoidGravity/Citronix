package org.abdellah.citronix.mapper;

import org.abdellah.citronix.model.Recolte;
import org.abdellah.citronix.model.Champ;
import org.abdellah.citronix.DTO.request.RecolteRequestDTO;
import org.abdellah.citronix.DTO.response.RecolteResponseDTO;
import org.mapstruct.*;
@Mapper(componentModel = "spring")
public interface RecolteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantiteTotale", ignore = true)
    Recolte toEntity(RecolteRequestDTO dto);

    RecolteResponseDTO toDTO(Recolte recolte);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantiteTotale", ignore = true)
    void updateEntityFromDTO(RecolteRequestDTO dto, @MappingTarget Recolte entity);
}