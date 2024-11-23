package org.abdellah.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.FermeRequestDTO;
import org.abdellah.citronix.DTO.response.FermeResponseDTO;
import org.abdellah.citronix.service.FermeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fermes")
@RequiredArgsConstructor
public class FermeController {
    private final FermeService fermeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FermeResponseDTO createFerme(@Valid @RequestBody FermeRequestDTO request) {
        return fermeService.createFerme(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FermeResponseDTO updateFerme(
            @PathVariable Long id,
            @Valid @RequestBody FermeRequestDTO request) {
        return fermeService.updateFerme(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFerme(@PathVariable Long id) {
        fermeService.deleteFerme(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FermeResponseDTO getFermeById(@PathVariable Long id) {
        return fermeService.getFermeById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FermeResponseDTO> getAllFermes() {
        return fermeService.getAllFermes();
    }
}