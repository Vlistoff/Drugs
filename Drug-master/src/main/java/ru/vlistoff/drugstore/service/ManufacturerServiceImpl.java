package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.Manufacturer;
import ru.vlistoff.drugstore.model.ProducingCountry;
import ru.vlistoff.drugstore.repository.ManufacturerRepository;
import ru.vlistoff.drugstore.repository.ProducingCountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl  implements DatabaseService<Manufacturer>{
    private final ManufacturerRepository repository;
    private final ProducingCountryRepository repositoryp;

    public ManufacturerServiceImpl(ManufacturerRepository repository, ProducingCountryRepository repositoryp) {
        this.repository = repository;
        this.repositoryp = repositoryp;
    }

    @Override
    public Optional<Manufacturer> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return repository.save(manufacturer);
    }

    public Manufacturer create(Manufacturer manufacturer, Long producingCountryId) {
        ProducingCountry producingCountry = repositoryp.findById(producingCountryId).orElse(null);
        manufacturer.setProducingCountry(producingCountry);
        return repository.save(manufacturer);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer, Long id) {
        Optional<Manufacturer> existingManufacturerOptional = repository.findById(id);

        if (existingManufacturerOptional.isPresent()){
            Manufacturer existingManufacturer = existingManufacturerOptional.get();
            existingManufacturer.setManufacturerName(manufacturer.getManufacturerName());
            existingManufacturer.setProducingCountry(manufacturer.getProducingCountry());
            return repository.save(existingManufacturer);
        } else {
            return null;
        }
    }

    public Manufacturer update(Manufacturer manufacturer, Long id, Long producingCountryId) {
        Optional<Manufacturer> existingManufacturerOptional = repository.findById(id);

        if (existingManufacturerOptional.isPresent()){
            Manufacturer existingManufacturer = existingManufacturerOptional.get();
            ProducingCountry producingCountry = repositoryp.findById(producingCountryId).orElse(null);
            existingManufacturer.setProducingCountry(producingCountry);
            existingManufacturer.setManufacturerName(manufacturer.getManufacturerName());
            return repository.save(existingManufacturer);
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
