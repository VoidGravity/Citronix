package org.abdellah.citronix.service;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.model.Arbre;
import org.abdellah.citronix.repository.ArbeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArbreService {
    private final ArbeRepository arbeRepository;


    public Arbre saveArbre(Arbre arbre){
        return arbeRepository.save(arbre);
    }
    public Arbre updateArbre(Arbre arbre){
        return arbeRepository.save(arbre);
    }
    public void delateArbre(Long id){
        arbeRepository.deleteById(id);
    }
    public List<Arbre> getAllArbres(){
       return arbeRepository.findAll();
    }
}
