package ru.vlistoff.drugstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlistoff.drugstore.model.ProducingCountry;

@Repository
public interface ProducingCountryRepository extends JpaRepository<ProducingCountry, Long> {
}
