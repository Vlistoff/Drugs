package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.Cities;
import ru.vlistoff.drugstore.model.Streets;
import ru.vlistoff.drugstore.repository.CitiesRepository;
import ru.vlistoff.drugstore.repository.StreetsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StreetsServiceImpl implements DatabaseService<Streets>{

    private final StreetsRepository repository;
    private final CitiesRepository repositoryc;

    public StreetsServiceImpl(StreetsRepository repository, CitiesRepository repositoryc) {
        this.repository = repository;
        this.repositoryc = repositoryc;
    }

    @Override
    public Optional<Streets> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Streets> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public Streets create(Streets streets) {
        return repository.save(streets);
    }

    public Streets create(Streets streets, Long cityId) {
        Cities city = repositoryc.findById(cityId).orElse(null);
        streets.setCities(city);
        return repository.save(streets);
    }

    @Override
    public Streets update(Streets streets, Long id) {
        Optional<Streets> existingStreetsOptional = repository.findById(id);

        if (existingStreetsOptional.isPresent()){
            Streets existingStreets = existingStreetsOptional.get();
            existingStreets.setCities(streets.getCities());
            existingStreets.setStreetName(streets.getStreetName());
            return repository.save(existingStreets);
        } else {
            return null;
        }
    }

    public Streets update(Streets streets, Long id, Long cityId) {
        Optional<Streets> existingStreetsOptional = repository.findById(id);

        if (existingStreetsOptional.isPresent()){
            Streets existingStreets = existingStreetsOptional.get();
            Cities city = repositoryc.findById(cityId).orElse(null);
            existingStreets.setCities(city);
            existingStreets.setStreetName(streets.getStreetName());
            return repository.save(existingStreets);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }
}
