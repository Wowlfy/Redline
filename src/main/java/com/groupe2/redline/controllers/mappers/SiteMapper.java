package com.groupe2.redline.controllers.mappers;

import com.groupe2.redline.controllers.dto.SiteDTO;
import com.groupe2.redline.entities.Site;
import org.springframework.stereotype.Component;

@Component
public class SiteMapper {

    public Site editSitefromDTO(Site site, SiteDTO siteDTO) {
        if(siteDTO.getLibelle() != null) {
            site.setLibelle(siteDTO.getLibelle());
        }
        if(siteDTO.getDescription() != null) {
            site.setDescription(siteDTO.getDescription());
        }
        if(siteDTO.getAdresse() != null) {
            site.setAdresse(siteDTO.getAdresse());
        }
        return site;
    }
}
