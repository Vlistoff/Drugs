package ru.vlistoff.drugstore.repository;

import ru.vlistoff.drugstore.model.Drug;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
}
