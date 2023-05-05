package com.groupe2.redline.mappers;

import com.groupe2.redline.dto.SalleDto;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Site;
import com.groupe2.redline.repositories.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalleMapper {

    private final SiteRepository siteRepository;

    public SalleMapper(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Salle createSalleFromDto(SalleDto salleDto) throws EntityNotFoundException {
        Salle nouvelleSalle = new Salle();
        nouvelleSalle.setLibelle(salleDto.getLibelle());
        nouvelleSalle.setDescription(salleDto.getDescription());
        nouvelleSalle.setNbPlaces(salleDto.getNbPlaces());
        Optional<Site> siteOptional = siteRepository.findById(salleDto.getSiteId());
        if (siteOptional.isEmpty()){
            throw new EntityNotFoundException();
        }
        nouvelleSalle.setSite(siteOptional.get());
        return nouvelleSalle;
    }

    public Salle editSalleFromDto(Salle salle, SalleDto salleDto) {
        if(salleDto.getLibelle() != null) {
            salle.setLibelle(salleDto.getLibelle());
        }
        if(salleDto.getDescription() != null) {
            salle.setDescription(salleDto.getDescription());
        }
        if(salleDto.getNbPlaces() != null) {
            salle.setNbPlaces(salleDto.getNbPlaces());
        }
        return salle;
    }
}
