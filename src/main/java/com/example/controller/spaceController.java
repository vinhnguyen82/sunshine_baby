package com.example.controller;

import com.example.model.SpaceEntity;
import com.example.service.SpaceEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<SpaceEntity> getSpaces() {
        return spaceEntityService.getSpaces();
    }

    @GetMapping("/{id}")
    public SpaceEntity getSpace(@PathVariable int id) {
        return spaceEntityService.getSpace(id);
    }

    @PatchMapping("/{id}")
    public SpaceEntity updateSpace(@RequestBody SpaceEntity spaceEntity, @PathVariable int id) {
        return spaceEntityService.updateSpace(spaceEntity, id);
    }
}
