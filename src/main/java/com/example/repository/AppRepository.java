package com.example.repository;

import com.example.model.AppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRepository extends JpaRepository<AppEntity, Integer>{
    List<AppEntity> findAllBySpaceId(int spaceId);
}
