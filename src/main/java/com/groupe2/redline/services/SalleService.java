package com.groupe2.redline.services;

import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.repository.SalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SalleService {

    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public List<Salle> getAllSalles() {
        List<Salle> salles = this.salleRepository.findAll(Sort.by(Sort.Direction.ASC, "libelle"));
        return salles;
    }
    public String addSalle(Salle salle){
        return null;
    }
}
