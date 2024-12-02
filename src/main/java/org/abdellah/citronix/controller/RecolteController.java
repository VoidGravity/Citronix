package org.abdellah.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.RecolteRequestDTO;
import org.abdellah.citronix.DTO.response.RecolteResponseDTO;
import org.abdellah.citronix.model.Saison;
import org.abdellah.citronix.service.RecolteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recoltes")
@RequiredArgsConstructor
public class RecolteController {
    private final RecolteService recolteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecolteResponseDTO createRecolte(@Valid @RequestBody RecolteRequestDTO request) {
        return recolteService.createRecolte(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecolteResponseDTO updateRecolte(
            @PathVariable Long id,
            @Valid @RequestBody RecolteRequestDTO request) {
        return recolteService.updateRecolte(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecolte(@PathVariable Long id) {
        recolteService.deleteRecolte(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RecolteResponseDTO getRecolteById(@PathVariable Long id) {
        return recolteService.getRecolteById(id);
    }

//    @GetMapping("/champ/{champId}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<RecolteResponseDTO> getRecoltesByChamp(@PathVariable Long champId) {
//        return recolteService.getRecoltesByChampId(champId);
//    }

//    @GetMapping("/saison/{saison}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<RecolteResponseDTO> getRecolteBySaison(@PathVariable Saison saison) {
//        return recolteService.getRecolteBySaison(saison);
//    }
//
//    @GetMapping("/{id}/quantite-totale")
//    @ResponseStatus(HttpStatus.OK)
//    public double getQuantiteTotale(@PathVariable Long id) {
//        return recolteService.calculateQuantiteTotale(id);
//    }
}