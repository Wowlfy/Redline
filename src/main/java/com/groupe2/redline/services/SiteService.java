package com.groupe2.redline.services;

import com.groupe2.redline.entities.Site;
import com.groupe2.redline.repository.SiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
