package com.groupe2.redline.services;

import com.groupe2.redline.dto.SiteDto;
import com.groupe2.redline.entities.Site;
import com.groupe2.redline.exceptions.SiteDejaActifException;
import com.groupe2.redline.exceptions.SiteDejaInactifException;
import com.groupe2.redline.mappers.SiteMapper;
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
        return this.siteRepository.findAll(Sort.by(Sort.Direction.ASC, "libelle"));
    }

    public Site addSite(SiteDto siteDto) {
        return this.siteRepository.save(siteMapper.createSiteFromDTO(siteDto));
    }

    public Site editSite(Long id, SiteDto siteDTO) throws EntityNotFoundException {
        Site site = findSiteOrThrow(id);

        return siteRepository.save(siteMapper.editSiteFromDTO(site, siteDTO));
    }

    public Site activer(Long idSite) throws SiteDejaActifException {
        Site site = findSiteOrThrow(idSite);

        if (site.isActif()) throw new SiteDejaActifException("Le site spécifié est déjà actif.");

        site.setActif(true);
        return siteRepository.saveAndFlush(site);
    }

    public Site desactiver(Long idSite) throws SiteDejaInactifException {
        Site site = findSiteOrThrow(idSite);

        if (!site.isActif()) throw new SiteDejaInactifException("Le site spécifié est déjà inactif.");

        site.setActif(false);
        return siteRepository.saveAndFlush(site);
    }

    /**
     * Récupérer un site par son id, ou lancer une erreur.
     *
     * @param id L'id su site recherché
     * @return La Site
     * @throws EntityNotFoundException Si l'id ne correspond à aucun site
     */
    private Site findSiteOrThrow(Long id) throws EntityNotFoundException {
        Optional<Site> siteOptional = siteRepository.findById(id);
        if (siteOptional.isEmpty()) throw new EntityNotFoundException("Le site spécifiée n'existe pas.");
        return siteOptional.get();
    }
}
