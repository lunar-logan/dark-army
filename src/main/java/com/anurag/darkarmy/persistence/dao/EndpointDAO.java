package com.anurag.darkarmy.persistence.dao;

import com.anurag.darkarmy.persistence.entity.Endpoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EndpointDAO extends MongoRepository<Endpoint, String> {
}
