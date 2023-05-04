package com.groupe2.redline.services;

import com.groupe2.redline.entities.Site;
import com.groupe2.redline.repository.SiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public Site editSite(Long id, Site site) throws ClassNotFoundException {
        Optional<Site> editingSite = siteRepository.findById(id);
        if (!editingSite.isPresent()) {
            throw new ClassNotFoundException("Le site avec l'ID " + id + " n'existe pas.");
        }

        Site editedSite = editingSite.get();
        editedSite.setLibelle(site.getLibelle());
        editedSite.setDescription(site.getDescription());
        editedSite.setAdresse(site.getAdresse());

        Site savedSite = siteRepository.save(editedSite);
        return savedSite;
    }
}
