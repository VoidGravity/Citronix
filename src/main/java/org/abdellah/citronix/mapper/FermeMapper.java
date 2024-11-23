package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.request.FermeRequestDTO;
import org.abdellah.citronix.DTO.response.FermeResponseDTO;
import org.abdellah.citronix.model.Ferme;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    @Mapping(target = "id", ignore = true)
    Ferme toEntity(FermeRequestDTO dto);

    FermeResponseDTO toDTO(Ferme ferme);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(FermeRequestDTO dto, @MappingTarget Ferme entity);
}