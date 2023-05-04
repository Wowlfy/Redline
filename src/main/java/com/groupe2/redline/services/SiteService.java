package com.groupe2.redline.services;

import com.groupe2.redline.dto.SiteDto;
import com.groupe2.redline.mappers.SiteMapper;
import com.groupe2.redline.entities.Site;
import com.groupe2.redline.exceptions.SiteDejaActifException;
import com.groupe2.redline.exceptions.SiteDejaInactifException;
import com.groupe2.redline.repositories.SiteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SiteMapper siteMapper;

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

    public Site editSite(Long id, SiteDto siteDTO) throws EntityNotFoundException {
        Optional<Site> editingSite = siteRepository.findById(id);
        if (editingSite.isEmpty()) {
            throw new EntityNotFoundException("Le site avec l'ID " + id + " n'existe pas.");
        }

        Site updatedSite = siteMapper.editSiteFromDTO(editingSite.get(), siteDTO);
        Site savedSite = siteRepository.save(updatedSite);

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
