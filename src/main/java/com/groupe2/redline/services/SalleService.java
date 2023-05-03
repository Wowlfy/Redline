package com.groupe2.redline.services;

import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.repository.SalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SalleService {
    @Autowired
    private SalleRepository salleRepository;


    public List<Salle> getAllSalles() {
        return this.salleRepository.findAll(Sort.by(Sort.Direction.ASC, "libelle"));
    }
    public Salle addSalle(Salle salle){
        return salleRepository.save(salle);
    }
}
