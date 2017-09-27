package com.example.controller;

import com.example.model.SpaceEntity;
import com.example.service.SpaceEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceController {

    SpaceEntityService spaceEntityService;

    public SpaceController(SpaceEntityService spaceEntityService) {
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
    public ResponseEntity<SpaceEntity> getSpace(@PathVariable int id) {
        SpaceEntity spaceEntity = spaceEntityService.getSpace(id);

        if (null == spaceEntity) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spaceEntity, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SpaceEntity> updateSpace(@RequestBody SpaceEntity spaceEntity, @PathVariable int id) {
        SpaceEntity oldSpaceEntity = spaceEntityService.getSpace(id);

        if (null == oldSpaceEntity) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spaceEntityService.updateSpace(spaceEntity, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeSpace(@PathVariable int id) {
        SpaceEntity spaceEntity = spaceEntityService.getSpace(id);

        if (null == spaceEntity) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        spaceEntityService.removeSpace(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
