package org.abdellah.citronix.controller;


import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.model.Arbre;
import org.abdellah.citronix.service.ArbreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arbres")
@RequiredArgsConstructor
public class ArbreController {
    private final ArbreService arbreService;

    //read
    @GetMapping("/get")
    public ResponseEntity<List<Arbre>> getAllArbres(){
        return ResponseEntity.ok(arbreService.getAllArbres());
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity<Arbre> updateArbre(@PathVariable Long id, @RequestBody Arbre arbre){
        arbre.setId(id);
        return ResponseEntity.ok(arbreService.updateArbre(arbre));
    }
    //create
    @PostMapping("/save")
    public ResponseEntity<Arbre> saveArbre(@RequestBody Arbre arbre){
        return ResponseEntity.ok(arbreService.updateArbre(arbre));
    }
    //delate
    @DeleteMapping("/{id}")
    public ResponseEntity<Arbre> delateArbre(@PathVariable Long id){

        return ResponseEntity.noContent().build();
    }


}