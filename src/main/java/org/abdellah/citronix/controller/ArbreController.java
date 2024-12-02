package org.abdellah.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.ArbreRequestDTO;
import org.abdellah.citronix.DTO.response.ArbreResponseDTO;
import org.abdellah.citronix.service.ArbreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/arbres")
@RequiredArgsConstructor
public class ArbreController {
    private final ArbreService arbreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArbreResponseDTO createArbre(@Valid @RequestBody ArbreRequestDTO request) {
        return arbreService.createArbre(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArbreResponseDTO updateArbre(
            @PathVariable Long id,
            @Valid @RequestBody ArbreRequestDTO request) {
        return arbreService.updateArbre(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArbre(@PathVariable Long id) {
        arbreService.deleteArbre(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArbreResponseDTO getArbreById(@PathVariable Long id) {
        return arbreService.getArbreById(id);
    }

    @GetMapping("/champ/{champId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ArbreResponseDTO> getArbresByChamp(@PathVariable Long champId) {
        return arbreService.getArbresByChampId(champId);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ArbreResponseDTO> getAllArbres() {

        return arbreService.getAllArbres();
    }

    @GetMapping("/{id}/productivite")
    @ResponseStatus(HttpStatus.OK)
    public double getProductivite(@PathVariable Long id) {
        return arbreService.calculateProductivite(id);
    }

    @GetMapping("/{id}/age")
    @ResponseStatus(HttpStatus.OK)
    public int getAge(@PathVariable Long id) {
        return arbreService.calculateAge(id);
    }

    @GetMapping("/champ/{champId}/count")
    @ResponseStatus(HttpStatus.OK)
    public int getArbreCountByChamp(@PathVariable Long champId) {
        return arbreService.countArbresByChamp(champId);
    }
}