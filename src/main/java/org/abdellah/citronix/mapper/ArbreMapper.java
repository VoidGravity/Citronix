package org.abdellah.citronix.mapper;

import org.abdellah.citronix.model.Arbre;
import org.abdellah.citronix.model.Champ;
import org.abdellah.citronix.DTO.request.ArbreRequestDTO;
import org.abdellah.citronix.DTO.response.ArbreResponseDTO;
import org.abdellah.citronix.util.ArbreUtil;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {ArbreUtil.class})  // Added imports
public interface ArbreMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "champ", source = "champId", qualifiedByName = "champIdToChamp")
    Arbre toEntity(ArbreRequestDTO dto);

    @Named("champIdToChamp")
    default Champ champIdToChamp(Long champId) {
        return champId != null ? Champ.builder().id(champId).build() : null;
    }

    @Mapping(target = "age", expression = "java(org.abdellah.citronix.util.ArbreUtil.calculateAge(arbre.getDatePlantation()))")  // Use fully qualified name
    @Mapping(target = "productiviteParSaison", expression = "java(arbre.getStatus().getProductionParSaison())")
    @Mapping(target = "champId", expression = "java(arbre.getChamp() != null ? arbre.getChamp().getId() : null)")
    ArbreResponseDTO toDTO(Arbre arbre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "champ", ignore = true)
    void updateEntityFromDTO(ArbreRequestDTO dto, @MappingTarget Arbre entity);
}