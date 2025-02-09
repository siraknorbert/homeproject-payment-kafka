package hu.siraknorbert.homeproject.repository;

import hu.siraknorbert.homeproject.model.MoneyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MoneyTransactionRepository extends JpaRepository<MoneyTransaction, UUID> {
}
