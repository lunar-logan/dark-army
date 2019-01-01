package com.anurag.darkarmy.persistence.dao;

import com.anurag.darkarmy.persistence.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskDAO extends MongoRepository<Task, String> {
}
