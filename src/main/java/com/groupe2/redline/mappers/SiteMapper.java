package com.groupe2.redline.mappers;

import com.groupe2.redline.dto.SiteDto;
import com.groupe2.redline.entities.Site;
import org.springframework.stereotype.Component;

@Component
public class SiteMapper {

    public Site createSiteFromDTO(SiteDto siteDto) {

        Site newSite = new Site();
        newSite.setLibelle(siteDto.getLibelle());
        newSite.setAdresse(siteDto.getAdresse());
        newSite.setDescription(siteDto.getDescription());
        return newSite;
    }

    public Site editSiteFromDTO(Site site, SiteDto siteDTO) {
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
