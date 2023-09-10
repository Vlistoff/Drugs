package ru.vlistoff.drugstore.repository;

import ru.vlistoff.drugstore.model.Cities;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Long> {
}
