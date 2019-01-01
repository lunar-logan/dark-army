package com.anurag.darkarmy.persistence.dao;

import com.anurag.darkarmy.persistence.entity.Workflow;
import com.anurag.darkarmy.persistence.entity.WorkflowID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkflowDAO extends MongoRepository<Workflow, WorkflowID> {
}
