package com.groupe2.redline.controllers;

import com.groupe2.redline.entities.Site;
import com.groupe2.redline.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/site")
public class SiteController {

    private SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping(value ="/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Site>> getSites() {
        List<Site> sites = this.siteService.getAllSites();
        return ResponseEntity.ok(sites);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Site> addSite(@RequestBody Site site) {
        Site saveSite = siteService.addSite(site);
        return new ResponseEntity<>(saveSite, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Site> updateSite(@PathVariable Long id, @RequestBody Site site) throws ClassNotFoundException {
        return new ResponseEntity<>(siteService.editSite(id, site), HttpStatus.OK);
    }
}
