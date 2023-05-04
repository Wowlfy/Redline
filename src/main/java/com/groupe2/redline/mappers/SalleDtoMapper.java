package com.groupe2.redline.mappers;

import com.groupe2.redline.dto.salle.SalleDto;
import com.groupe2.redline.entities.Salle;
import com.groupe2.redline.entities.Site;
import com.groupe2.redline.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalleDtoMapper {

    @Autowired
    private SiteRepository siteRepository;
    public Salle salleFromDto(SalleDto salleDto) throws EntityNotFoundException {
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
}
