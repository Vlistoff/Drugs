package ru.vlistoff.drugstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlistoff.drugstore.model.Drugstores;

@Repository
public interface DrugstoresRepository extends JpaRepository<Drugstores, Long> {
}
