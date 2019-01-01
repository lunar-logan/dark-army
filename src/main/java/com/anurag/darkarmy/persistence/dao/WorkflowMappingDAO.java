package com.anurag.darkarmy.persistence.dao;

import com.anurag.darkarmy.persistence.entity.WorkflowMapping;
import com.anurag.darkarmy.persistence.entity.WorkflowMappingID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkflowMappingDAO extends MongoRepository<WorkflowMapping, WorkflowMappingID> {
}
