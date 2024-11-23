package org.abdellah.citronix.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecolteServiceImpl implements RecolteService {
    private final RecolteRepository recolteRepository;
    private final RecolteMapper recolteMapper;

    @Override
    public RecolteResponseDTO createRecolte(RecolteRequestDTO dto) {
        // Verify one harvest per season
        if (recolteRepository.existsByChampIdAndSaison(dto.champId(), dto.saison())) {
            throw new BusinessException("Une seule récolte par saison est autorisée pour un champ");
        }

        Recolte recolte = recolteMapper.toEntity(dto);
        return recolteMapper.toDTO(recolteRepository.save(recolte));
    }

    // Other implemented methods...
}