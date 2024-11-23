package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.response.DashboardViewModel;
import org.abdellah.citronix.DTO.response.FermeViewModel;
import org.abdellah.citronix.DTO.response.StatistiquesChampViewModel;

import java.util.List;


public interface FermeService {
    // Use ViewModels directly instead of DTOs/Response objects
    List<FermeViewModel> getAllFermes();
    FermeViewModel getFermeById(Long id);
    FermeViewModel createFerme(FermeViewModel fermeVM);
    FermeViewModel updateFerme(Long id, FermeViewModel fermeVM);
    void deleteFerme(Long id);
    DashboardViewModel getDashboard(Long fermeId);
    StatistiquesChampViewModel getStatistics(Long fermeId);
}