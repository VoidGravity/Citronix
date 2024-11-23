package org.abdellah.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.VenteRequestDTO;
import org.abdellah.citronix.DTO.response.VenteResponseDTO;
import org.abdellah.citronix.service.VenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventes")
@RequiredArgsConstructor
public class VenteController {
    private final VenteService venteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VenteResponseDTO createVente(@Valid @RequestBody VenteRequestDTO request) {
        return venteService.createVente(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VenteResponseDTO getVenteById(@PathVariable Long id) {
        return venteService.getVenteById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VenteResponseDTO> getAllVentes() {
        return venteService.getAllVentes();
    }

    @GetMapping("/recolte/{recolteId}")
    @ResponseStatus(HttpStatus.OK)
    public List<VenteResponseDTO> getVentesByRecolte(@PathVariable Long recolteId) {
        return venteService.getVentesByRecolte(recolteId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
    }
}