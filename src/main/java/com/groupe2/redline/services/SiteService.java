package com.groupe2.redline.services;

import com.groupe2.redline.entities.Site;
import com.groupe2.redline.exceptions.SiteDejaActifException;
import com.groupe2.redline.exceptions.SiteDejaInactifException;
import com.groupe2.redline.repository.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<Site> getAllSites() {
        List<Site> sites = this.siteRepository.findAll(Sort.by(Sort.Direction.ASC, "libelle"));
        return sites;
    }

    public Site addSite(Site site) {
        //TODO Gestion des erreurs
        Site savedSite = this.siteRepository.save(site);
        return savedSite;
    }

    public Optional<Site> findById(Long id) {
        return siteRepository.findById(id);
    }


    public Site editSite(Long id, Site site) throws EntityNotFoundException {
        Optional<Site> editingSite = siteRepository.findById(id);
        if (editingSite.isEmpty()) {
            throw new EntityNotFoundException("Le site avec l'ID " + id + " n'existe pas.");
        }

        Site editedSite = editingSite.get();

        if (site.getLibelle() != null) {
            editedSite.setLibelle(site.getLibelle());

        }
        if (site.getDescription() != null) {
            editedSite.setDescription(site.getDescription());

        }

        if (site.getAdresse() != null) {
            editedSite.setAdresse(site.getAdresse());
        }

        Site savedSite = siteRepository.save(editedSite);
        return savedSite;
    }

    public void activer(Long idSite) throws SiteDejaActifException {
        Optional<Site> siteOptional = findById(idSite);

        if (siteOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Site site = siteOptional.get();

        if (site.isActif()) {
            throw new SiteDejaActifException();
        }

        site.setActif(true);
        siteRepository.saveAndFlush(site);
    }

    public void desactiver(Long idSite) throws SiteDejaInactifException {
        Optional<Site> siteOptional = findById(idSite);

        if (siteOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Site site = siteOptional.get();

        if (!site.isActif()) {
            throw new SiteDejaInactifException();
        }

        site.setActif(false);
        siteRepository.saveAndFlush(site);
    }
}
