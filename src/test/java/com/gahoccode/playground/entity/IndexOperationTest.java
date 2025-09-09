package com.gahoccode.playground.entity;

import com.gahoccode.playground.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

public class IndexOperationTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(IndexOperationTest.class);

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void createIndex(){
        var indexOperation = this.elasticsearchOperations.indexOps(IndexCoordinates.of("albums"));
        Assertions.assertTrue(indexOperation.create());

        this.verify(indexOperation, 1, 1);
    }
    @Test
    public void createIndexWithSetting(){
        var indexOperation = this.elasticsearchOperations.indexOps(Review.class);
        Assertions.assertTrue(indexOperation.create());

        this.verify(indexOperation, 1, 1);
    }
    @Test
    public void createIndexWithSettingsAndMappings(){
        var indexOperation = this.elasticsearchOperations.indexOps(Customer.class);
        Assertions.assertTrue(indexOperation.createWithMapping());

        this.verify(indexOperation, 3, 0);
    }
    @Test
    public void createIndexWithFieldMappings(){
        var indexOperation = this.elasticsearchOperations.indexOps(Movie.class);
        Assertions.assertTrue(indexOperation.createWithMapping());

        this.verify(indexOperation, 1, 1);
    }

    private void verify(IndexOperations indexOperations, int expectedShards, int expectedReplicas){
        var settings = indexOperations.getSettings();
        log.info("Setting: {}", settings);
        log.info("Mapping: {}", indexOperations.getMapping());

        Assertions.assertEquals(String.valueOf(expectedShards), settings.get("index.number_of_shards"));
        Assertions.assertEquals(String.valueOf(expectedReplicas), settings.get("index.number_of_replicas"));

        Assertions.assertTrue(indexOperations.delete());
    }

}
