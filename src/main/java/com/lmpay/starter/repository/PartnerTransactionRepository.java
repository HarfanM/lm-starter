package com.lmpay.starter.repository;

import com.lmpay.starter.model.PartnerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerTransactionRepository extends JpaRepository<PartnerTransaction, Integer> {
    @Query(value = "SELECT generate_transaction_no()", nativeQuery = true)
    String generateTransactionNo();
    PartnerTransaction findByTransactionNo(String transactionNo);
}
