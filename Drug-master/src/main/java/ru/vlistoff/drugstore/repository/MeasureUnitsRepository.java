package ru.vlistoff.drugstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlistoff.drugstore.model.MeasureUnits;

@Repository
public interface MeasureUnitsRepository extends JpaRepository<MeasureUnits, Long> {
}
