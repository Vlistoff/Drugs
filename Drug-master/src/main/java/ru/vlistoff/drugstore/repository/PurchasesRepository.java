package ru.vlistoff.drugstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlistoff.drugstore.model.Purchases;

@Repository
public interface PurchasesRepository extends JpaRepository<Purchases, Long> {
}
