package ru.vlistoff.drugstore.service;

import ru.vlistoff.drugstore.model.Drug;
import ru.vlistoff.drugstore.model.Manufacturer;
import ru.vlistoff.drugstore.model.MeasureUnits;
import ru.vlistoff.drugstore.repository.DrugRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.repository.ManufacturerRepository;
import ru.vlistoff.drugstore.repository.MeasureUnitsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DrugServiceImpl implements DatabaseService<Drug> {
    private final DrugRepository repository;
    private final ManufacturerRepository repositoryManufacturer;
    private final MeasureUnitsRepository repositoryMeasureUnits;

    public DrugServiceImpl(DrugRepository repository, ManufacturerRepository repositoryManufacturer, MeasureUnitsRepository repositoryMeasureUnits) {
        this.repository = repository;
        this.repositoryManufacturer = repositoryManufacturer;
        this.repositoryMeasureUnits = repositoryMeasureUnits;
    }

    @Override
    public Optional<Drug> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Drug> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public Drug create(Drug drug) {
        return repository.save(drug);
    }

    public Drug create(Drug drug, Long manufacturerId, Long measureUnitId) {
        Manufacturer manufacturer = repositoryManufacturer.findById(manufacturerId).orElse(null);
        MeasureUnits measureUnits = repositoryMeasureUnits.findById(measureUnitId).orElse(null);
        drug.setManufacturer(manufacturer);
        drug.setMeasureUnits(measureUnits);
        return repository.save(drug);
    }

    @Override
    public Drug update(Drug drug, Long id) {
        Optional<Drug> existingModelOptional = repository.findById(id);

        if (existingModelOptional.isPresent()) {
            Drug existingDrug = existingModelOptional.get();
            existingDrug.setManufacturer(drug.getManufacturer());
            existingDrug.setTradeName(drug.getTradeName());
            existingDrug.setInternationalName(drug.getInternationalName());
            existingDrug.setMeasureUnits(drug.getMeasureUnits());
            existingDrug.setDose(drug.getDose());
            existingDrug.setForm(drug.getForm());
            return repository.save(existingDrug);
        } else {
            return null;
        }
    }

    public Drug update(Drug drug, Long id, Long manufacturerId, Long measureUnitId) {
        Optional<Drug> existingModelOptional = repository.findById(id);

        if (existingModelOptional.isPresent()) {
            Drug existingDrug = existingModelOptional.get();
            Manufacturer manufacturer = repositoryManufacturer.findById(manufacturerId).orElse(null);
            MeasureUnits measureUnits = repositoryMeasureUnits.findById(measureUnitId).orElse(null);
            existingDrug.setManufacturer(manufacturer);
            existingDrug.setMeasureUnits(measureUnits);
            existingDrug.setTradeName(drug.getTradeName());
            existingDrug.setInternationalName(drug.getInternationalName());
            existingDrug.setDose(drug.getDose());
            existingDrug.setForm(drug.getForm());
            return repository.save(existingDrug);
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
