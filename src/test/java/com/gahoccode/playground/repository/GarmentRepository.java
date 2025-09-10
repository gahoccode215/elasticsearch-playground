package com.gahoccode.playground.repository;

import com.gahoccode.playground.entity.Garment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarmentRepository extends ElasticsearchRepository<Garment, String> {
}
