package ru.vlistoff.drugstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlistoff.drugstore.model.DrugPrices;

@Repository
public interface DrugPricesRepository extends JpaRepository<DrugPrices, Long> {
}
