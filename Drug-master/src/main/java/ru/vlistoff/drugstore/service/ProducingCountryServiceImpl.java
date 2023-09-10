package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.ProducingCountry;
import ru.vlistoff.drugstore.repository.ProducingCountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProducingCountryServiceImpl implements DatabaseService<ProducingCountry> {
    private final ProducingCountryRepository repository;

    public ProducingCountryServiceImpl(ProducingCountryRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<ProducingCountry> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProducingCountry> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public ProducingCountry create(ProducingCountry producingCountry) {
        return repository.save(producingCountry);
    }

    @Override
    public ProducingCountry update(ProducingCountry producingCountry, Long id) {
        Optional<ProducingCountry> existingProducingCountryOptional = repository.findById(id);

        if (existingProducingCountryOptional.isPresent()) {
            ProducingCountry existingProducingCountry = existingProducingCountryOptional.get();
            existingProducingCountry.setProducingCountryName(producingCountry.getProducingCountryName());
            return repository.save(existingProducingCountry);
        } else {
            return  null;
        }
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }
}
