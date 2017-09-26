package com.example.repository;

import com.example.model.SpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository <SpaceEntity, Integer>{
}
