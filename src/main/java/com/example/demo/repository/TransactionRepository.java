package com.example.demo.repository;


import com.example.demo.entity.Transactions;
import com.example.demo.model.ProductTransactionCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {
    Optional<Transactions> findByRequestCodeAndOfferOfferCode(String requestCode,String offerCode);

    Optional<Transactions> findByCurrentUserId(Long userId);

    Optional<Transactions> findByCode(String code);

    @Query("SELECT new com.example.demo.model.ProductTransactionCount(pt.id, COUNT(t)) " +
            "FROM Transactions t " +
            "JOIN t.products pt " +
            "GROUP BY pt.id " +
            "HAVING COUNT(t) > 0 " +
            "ORDER BY pt.id")
    List<ProductTransactionCount> findProductTransactionCounts();
}
