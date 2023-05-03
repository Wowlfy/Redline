package com.groupe2.redline.controllers;

import com.groupe2.redline.entities.Site;
import com.groupe2.redline.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/site")
public class SiteController {

    private SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Site>> getSites() {
        List<Site> sites = this.siteService.getAllSites();
        return ResponseEntity.ok(sites);
    }
}
