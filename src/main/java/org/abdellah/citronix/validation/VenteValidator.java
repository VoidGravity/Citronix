package org.abdellah.citronix.validation;


import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.VenteRequestDTO;
import org.abdellah.citronix.exception.BusinessException;
import org.abdellah.citronix.exception.ResourceNotFoundException;
import org.abdellah.citronix.repository.RecolteRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class VenteValidator {
    private final RecolteRepository recolteRepository;

    public void validate(VenteRequestDTO request) {
        validateRecolteExists(request.recolteId());
        validatePrixUnitaire(request.prixUnitaire());
        validateClient(request.client());
        validateDateVente(request.dateVente());
    }

    private void validateRecolteExists(Long recolteId) {
        if (!recolteRepository.existsById(recolteId)) {
            throw new ResourceNotFoundException("Recolte", recolteId);
        }
    }

    private void validatePrixUnitaire(Double prixUnitaire) {
        if (prixUnitaire <= 0) {
            throw new BusinessException("Le prix unitaire doit être supérieur à 0");
        }
    }

    private void validateClient(String client) {
        if (client == null || client.trim().isEmpty()) {
            throw new BusinessException("Le client est obligatoire");
        }
    }

    private void validateDateVente(LocalDate dateVente) {
        if (dateVente == null) {
            throw new BusinessException("La date de vente est obligatoire");
        }
        if (dateVente.isAfter(LocalDate.now())) {
            throw new BusinessException("La date de vente ne peut pas être dans le futur");
        }
    }
}
