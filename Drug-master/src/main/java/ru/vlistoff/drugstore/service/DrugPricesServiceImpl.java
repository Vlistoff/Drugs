package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.Drug;
import ru.vlistoff.drugstore.model.DrugPrices;
import ru.vlistoff.drugstore.repository.DrugPricesRepository;
import ru.vlistoff.drugstore.repository.DrugRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DrugPricesServiceImpl  implements DatabaseService<DrugPrices>{
    private final DrugPricesRepository repository;
    private final DrugRepository repositoryd;

    public DrugPricesServiceImpl(DrugPricesRepository repository, DrugRepository repositoryd) {
        this.repository = repository;
        this.repositoryd = repositoryd;
    }

    @Override
    public Optional<DrugPrices> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<DrugPrices> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public DrugPrices create(DrugPrices drugPrice){
        return repository.save(drugPrice);
    }

    public DrugPrices create(DrugPrices drugPrice, Long drugId){
        Drug drug = repositoryd.findById(drugId).orElse(null);
        drugPrice.setDrug(drug);
        return repository.save(drugPrice);
    }

    @Override
    public DrugPrices update(DrugPrices drugPrice, Long id) {
        Optional<DrugPrices> existingDrugPriceOptional = repository.findById(id);

        if (existingDrugPriceOptional.isPresent()) {
            DrugPrices existingDrugPrice = existingDrugPriceOptional.get();
            existingDrugPrice.setPrice(drugPrice.getPrice());
            existingDrugPrice.setDate(drugPrice.getDate());
            existingDrugPrice.setDrug(drugPrice.getDrug());
            return repository.save(existingDrugPrice);
        } else {
            return null;
        }
    }

    public DrugPrices update(DrugPrices drugPrice, Long id, Long drugId) {
        Optional<DrugPrices> existingDrugPriceOptional = repository.findById(id);

        if (existingDrugPriceOptional.isPresent()) {
            DrugPrices existingDrugPrice = existingDrugPriceOptional.get();
            Drug drug = repositoryd.findById(drugId).orElse(null);
            existingDrugPrice.setDrug(drug);
            existingDrugPrice.setPrice(drugPrice.getPrice());
            existingDrugPrice.setDate(drugPrice.getDate());
            return repository.save(existingDrugPrice);
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
