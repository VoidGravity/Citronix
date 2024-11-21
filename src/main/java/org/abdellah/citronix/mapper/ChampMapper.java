package org.abdellah.citronix.mapper;

import org.abdellah.citronix.DTO.ChampDTO;
import org.abdellah.citronix.model.Champ;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChampMapper {
    ChampDTO toDto(Champ champ);
    Champ toEntity(ChampDTO dto);
    List<ChampDTO> toDtoList(List<Champ> champs);
}