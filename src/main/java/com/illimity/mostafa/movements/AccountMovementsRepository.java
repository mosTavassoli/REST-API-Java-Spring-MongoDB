package com.illimity.mostafa.movements;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMovementsRepository extends MongoRepository<AccountMovements, String> {

    @Query(value = "{customerId:?0}", sort="{date:-1}")
    List<AccountMovements> getTenLastMovementsOrderByDate(String customerId, Pageable pageable);
}
