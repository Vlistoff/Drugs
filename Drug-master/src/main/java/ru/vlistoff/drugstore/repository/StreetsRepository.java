package ru.vlistoff.drugstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlistoff.drugstore.model.Streets;

@Repository
public interface StreetsRepository extends JpaRepository<Streets, Long> {
}
