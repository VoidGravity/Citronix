package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.request.VenteRequestDTO;
import org.abdellah.citronix.DTO.response.expermineting.VenteResponseDTO;
import org.abdellah.citronix.model.Vente;
import org.abdellah.citronix.model.Recolte;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VenteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recolte", source = "recolteId", qualifiedByName = "recolteIdToRecolte")
    Vente toEntity(VenteRequestDTO dto);

    @Named("recolteIdToRecolte")
    default Recolte recolteIdToRecolte(Long recolteId) {
        return Recolte.builder().id(recolteId).build();
    }

    VenteResponseDTO toDTO(Vente vente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "recolte", ignore = true)
    void updateEntityFromDTO(VenteRequestDTO dto, @MappingTarget Vente entity);
}