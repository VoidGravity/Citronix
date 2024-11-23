package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.request.ChampRequestDTO;
import org.abdellah.citronix.DTO.response.ChampResponseDTO;
import org.abdellah.citronix.model.Champ;
import org.abdellah.citronix.model.Ferme;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ferme", source = "fermeId", qualifiedByName = "fermeIdToFerme")
    Champ toEntity(ChampRequestDTO dto);

    @Named("fermeIdToFerme")
    default Ferme fermeIdToFerme(Long fermeId) {
        return Ferme.builder().id(fermeId).build();
    }

    ChampResponseDTO toDTO(Champ champ);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ferme", ignore = true)
    void updateEntityFromDTO(ChampRequestDTO dto, @MappingTarget Champ entity);
}