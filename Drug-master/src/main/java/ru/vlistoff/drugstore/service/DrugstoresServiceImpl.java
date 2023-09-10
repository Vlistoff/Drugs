package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.Cities;
import ru.vlistoff.drugstore.model.Drugstores;
import ru.vlistoff.drugstore.model.Streets;
import ru.vlistoff.drugstore.repository.CitiesRepository;
import ru.vlistoff.drugstore.repository.DrugstoresRepository;
import ru.vlistoff.drugstore.repository.StreetsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DrugstoresServiceImpl implements DatabaseService<Drugstores>{
    private final DrugstoresRepository repository;
    private final CitiesRepository repositoryCities;
    private final StreetsRepository repositoryStreets;

    public DrugstoresServiceImpl(DrugstoresRepository repository, CitiesRepository repositoryCities,
                                 StreetsRepository repositoryStreets) {
        this.repository = repository;
        this.repositoryCities = repositoryCities;
        this.repositoryStreets = repositoryStreets;
    }


    @Override
    public Optional<Drugstores> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Drugstores> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public Drugstores create(Drugstores drugstores) {
        return repository.save(drugstores);
    }

    public Drugstores create(Drugstores drugstores, Long cityId, Long streetId) {
        Cities city = repositoryCities.findById(cityId).orElse(null);
        Streets street = repositoryStreets.findById(streetId).orElse(null);
        drugstores.setCities(city);
        drugstores.setStreets(street);
        return repository.save(drugstores);
    }

    @Override
    public Drugstores update(Drugstores drugstores, Long id) {
        Optional<Drugstores> existingDrugstoresOptional = repository.findById(id);

        if (existingDrugstoresOptional.isPresent()) {
            Drugstores existingDrugstores = existingDrugstoresOptional.get();
            existingDrugstores.setBuilding(drugstores.getBuilding());
            existingDrugstores.setCities(drugstores.getCities());
            existingDrugstores.setEmail(drugstores.getEmail());
            existingDrugstores.setStreets(drugstores.getStreets());
            existingDrugstores.setFullName(drugstores.getFullName());
            existingDrugstores.setShortName(drugstores.getShortName());
            existingDrugstores.setPhoneNumber(drugstores.getPhoneNumber());
            return repository.save(existingDrugstores);
        } else {
            return null;
        }
    }

    public Drugstores update(Drugstores drugstores, Long id, Long cityId, Long streetId) {
        Optional<Drugstores> existingDrugstoresOptional = repository.findById(id);

        if (existingDrugstoresOptional.isPresent()) {
            Drugstores existingDrugstores = existingDrugstoresOptional.get();
            Cities city = repositoryCities.findById(cityId).orElse(null);
            Streets street = repositoryStreets.findById(streetId).orElse(null);
            existingDrugstores.setCities(city);
            existingDrugstores.setStreets(street);
            existingDrugstores.setBuilding(drugstores.getBuilding());
            existingDrugstores.setEmail(drugstores.getEmail());
            existingDrugstores.setFullName(drugstores.getFullName());
            existingDrugstores.setShortName(drugstores.getShortName());
            existingDrugstores.setPhoneNumber(drugstores.getPhoneNumber());
            return repository.save(existingDrugstores);
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
