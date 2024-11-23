package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.request.ArbreRequestDTO;
import org.abdellah.citronix.DTO.response.expermineting.ArbreResponseDTO;
import org.abdellah.citronix.model.Arbre;
import org.abdellah.citronix.model.Champ;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ArbreMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "champ", source = "champId", qualifiedByName = "champIdToChamp")
    Arbre toEntity(ArbreRequestDTO dto);

    @Named("champIdToChamp")
    default Champ champIdToChamp(Long champId) {
        return Champ.builder().id(champId).build();
    }

    @Mapping(target = "age", expression = "java(arbre.calculateAge())")
    @Mapping(target = "productiviteParSaison",
            expression = "java(arbre.getStatus().getProductionParSaison())")
    ArbreResponseDTO toDTO(Arbre arbre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "champ", ignore = true)
    void updateEntityFromDTO(ArbreRequestDTO dto, @MappingTarget Arbre entity);
}