package com.example.controller;

import com.example.model.SpaceEntity;
import com.example.service.SpaceEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spaces")
public class spaceController {

    SpaceEntityService spaceEntityService;

    public spaceController(SpaceEntityService spaceEntityService) {
        this.spaceEntityService = spaceEntityService;
    }


    @PostMapping
    public ResponseEntity<SpaceEntity> createSpace(@RequestBody SpaceEntity spaceEntity) {
        return new ResponseEntity<>(spaceEntityService.createSpace(spaceEntity), HttpStatus.CREATED);
    }
}
