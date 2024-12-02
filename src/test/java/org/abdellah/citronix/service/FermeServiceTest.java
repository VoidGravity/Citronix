package org.abdellah.citronix.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.abdellah.citronix.model.Ferme;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.abdellah.citronix.DTO.request.FermeRequestDTO;
import org.abdellah.citronix.DTO.response.FermeResponseDTO;
import org.abdellah.citronix.exception.ResourceNotFoundException;
import org.abdellah.citronix.mapper.FermeMapper;
import org.abdellah.citronix.repository.FermeRepository;
import org.abdellah.citronix.service.impl.FermeServiceImpl;

@ExtendWith(MockitoExtension.class)
class FermeServiceTest {

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private FermeServiceImpl fermeService;

    @Test
    void createFerme_Success() {
        FermeRequestDTO request = FermeRequestDTO.builder()
                .nom("Ma Ferme")
                .localisation("Marrakech")
                .superficie(1.5)
                .dateCreation(LocalDate.now())
                .build();

        Ferme ferme = Ferme.builder()
                .id(1L)
                .nom("Ma Ferme")
                .localisation("Marrakech")
                .superficie(1.5)
                .dateCreation(LocalDate.now())
                .build();

        FermeResponseDTO expected = FermeResponseDTO.builder()
                .id(1L)
                .nom("Ma Ferme")
                .localisation("Marrakech")
                .superficie(1.5)
                .dateCreation(LocalDate.now())
                .build();

        when(fermeMapper.toEntity(request)).thenReturn(ferme);
        when(fermeRepository.save(any(Ferme.class))).thenReturn(ferme);
        when(fermeMapper.toDTO(ferme)).thenReturn(expected);

        FermeResponseDTO result = fermeService.createFerme(request);

        assertThat(result.nom()).isEqualTo("Ma Ferme");
        assertThat(result.superficie()).isEqualTo(1.5);
        verify(fermeRepository).save(any(Ferme.class));
    }

    @Test
    void getFermeById_Success() {
        Long fermeId = 1L;
        Ferme ferme = Ferme.builder()
                .id(fermeId)
                .nom("Ma Ferme")
                .localisation("Marrakech")
                .superficie(1.5)
                .build();

        FermeResponseDTO expected = FermeResponseDTO.builder()
                .id(fermeId)
                .nom("Ma Ferme")
                .localisation("Marrakech")
                .superficie(1.5)
                .build();

        when(fermeRepository.findById(fermeId)).thenReturn(Optional.of(ferme));
        when(fermeMapper.toDTO(ferme)).thenReturn(expected);

        FermeResponseDTO result = fermeService.getFermeById(fermeId);

        assertThat(result.id()).isEqualTo(fermeId);
        assertThat(result.nom()).isEqualTo("Ma Ferme");
    }

    @Test
    void getFermeById_WhenNotFound_ThrowsException() {
        Long fermeId = 1L;
        when(fermeRepository.findById(fermeId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> fermeService.getFermeById(fermeId));
    }

    @Test
    void getAllFermes_Success() {
        List<Ferme> fermes = List.of(
                Ferme.builder()
                        .id(1L)
                        .nom("Ferme 1")
                        .localisation("Marrakech")
                        .superficie(1.5)
                        .build(),
                Ferme.builder()
                        .id(2L)
                        .nom("Ferme 2")
                        .localisation("Agadir")
                        .superficie(2.0)
                        .build()
        );

        List<FermeResponseDTO> expected = List.of(
                FermeResponseDTO.builder()
                        .id(1L)
                        .nom("Ferme 1")
                        .localisation("Marrakech")
                        .superficie(1.5)
                        .build(),
                FermeResponseDTO.builder()
                        .id(2L)
                        .nom("Ferme 2")
                        .localisation("Agadir")
                        .superficie(2.0)
                        .build()
        );

        when(fermeRepository.findAll()).thenReturn(fermes);
        when(fermeMapper.toDTO(any(Ferme.class)))
                .thenReturn(expected.get(0), expected.get(1));

        List<FermeResponseDTO> result = fermeService.getAllFermes();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).nom()).isEqualTo("Ferme 1");
    }

    @Test
    void updateFerme_Success() {
        Long fermeId = 1L;
        FermeRequestDTO request = FermeRequestDTO.builder()
                .nom("Ferme Updated")
                .localisation("Marrakech")
                .superficie(2.0)
                .dateCreation(LocalDate.now())
                .build();

        Ferme existingFerme = Ferme.builder()
                .id(fermeId)
                .nom("Old Name")
                .localisation("Old Location")
                .superficie(1.5)
                .build();

        Ferme updatedFerme = Ferme.builder()
                .id(fermeId)
                .nom("Ferme Updated")
                .localisation("Marrakech")
                .superficie(2.0)
                .build();

        when(fermeRepository.findById(fermeId)).thenReturn(Optional.of(existingFerme));
        when(fermeRepository.save(any(Ferme.class))).thenReturn(updatedFerme);
        when(fermeMapper.toDTO(updatedFerme)).thenReturn(
                FermeResponseDTO.builder()
                        .id(fermeId)
                        .nom("Ferme Updated")
                        .localisation("Marrakech")
                        .superficie(2.0)
                        .build()
        );

        FermeResponseDTO result = fermeService.updateFerme(fermeId, request);

        assertThat(result.nom()).isEqualTo("Ferme Updated");
        assertThat(result.superficie()).isEqualTo(2.0);
    }
}