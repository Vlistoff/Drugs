package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.MeasureUnits;
import ru.vlistoff.drugstore.repository.MeasureUnitsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasureUnitsServiceImpl implements DatabaseService<MeasureUnits>{
    private final MeasureUnitsRepository repository;

    public MeasureUnitsServiceImpl(MeasureUnitsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<MeasureUnits> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MeasureUnits> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public MeasureUnits create(MeasureUnits measureUnits) {
        System.out.println(measureUnits.toString());
        return repository.save(measureUnits);
    }

    @Override
    public MeasureUnits update(MeasureUnits measureUnits, Long id) {
        Optional<MeasureUnits> existingMeasureUnitsOptional = repository.findById(id);

        if (existingMeasureUnitsOptional.isPresent()){
            MeasureUnits existingMeasureUnits = existingMeasureUnitsOptional.get();
            existingMeasureUnits.setMeasureUnitName(measureUnits.getMeasureUnitName());
            return repository.save(existingMeasureUnits);
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
