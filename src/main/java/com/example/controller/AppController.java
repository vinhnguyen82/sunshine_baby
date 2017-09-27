package com.example.controller;

import com.example.model.AppEntity;
import com.example.service.AppEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces/{spaceId}/apps")
public class AppController {

    AppEntityService appEntityService;

    public AppController(AppEntityService appEntityService) {
        this.appEntityService = appEntityService;
    }

    @PostMapping
    public ResponseEntity<AppEntity> createApp(@PathVariable int spaceId, @RequestBody AppEntity appEntity) {

        AppEntity newAppEntity = appEntityService.createApp(appEntity, spaceId);

        return new ResponseEntity<>(newAppEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppEntity>> getAllApps(@PathVariable int spaceId) {

        List<AppEntity> appEntities = appEntityService.findAllApps(spaceId);

        return new ResponseEntity<>(appEntities, HttpStatus.OK);
    }
}
