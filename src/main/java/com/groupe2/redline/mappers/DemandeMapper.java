package com.groupe2.redline.mappers;

import com.groupe2.redline.dto.DemandeDto;
import com.groupe2.redline.entities.Demande;
import org.springframework.stereotype.Component;

@Component
public class DemandeMapper {

    public Demande createDemandeFromDto(DemandeDto demandeDto) {

        Demande newDemande = new Demande();
        newDemande.setNomDemandeur(demandeDto.getNomDemander());
        newDemande.setEmail(demandeDto.getEmail());
        newDemande.setDuree(demandeDto.getDuree());
        newDemande.setPropositionDate1(demandeDto.getPropositionDate1());
        newDemande.setPropositionDate2(demandeDto.getPropositionDate2());
        newDemande.setPropositionDate3(demandeDto.getPropositionDate3());
        newDemande.setDescription(demandeDto.getDescription());

        return newDemande;
    }
}
