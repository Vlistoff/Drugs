package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.Cities;
import ru.vlistoff.drugstore.repository.CitiesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesServiceImpl implements DatabaseService<Cities>{
    private final CitiesRepository repository;

    public CitiesServiceImpl(CitiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cities> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Cities> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public Cities create(Cities city)  {
        return repository.save(city);
    }

    @Override
    public Cities update(Cities city, Long id) {
        Optional<Cities> existingCitiesOptional = repository.findById(id);

        if (existingCitiesOptional.isPresent()) {
            Cities existingCities = existingCitiesOptional.get();
            existingCities.setCityName(city.getCityName());
            return repository.save(existingCities);
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
