package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.request.RecolteRequestDTO;
import org.abdellah.citronix.DTO.response.RecolteResponseDTO;
import org.abdellah.citronix.model.Recolte;
import org.abdellah.citronix.model.Arbre;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RecolteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "arbre", source = "arbreId", qualifiedByName = "arbreIdToArbre")
    Recolte toEntity(RecolteRequestDTO dto);

    @Named("arbreIdToArbre")
    default Arbre arbreIdToArbre(Long arbreId) {
        return Arbre.builder().id(arbreId).build();
    }

    RecolteResponseDTO toDTO(Recolte recolte);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "arbre", ignore = true)
    void updateEntityFromDTO(RecolteRequestDTO dto, @MappingTarget Recolte entity);
}