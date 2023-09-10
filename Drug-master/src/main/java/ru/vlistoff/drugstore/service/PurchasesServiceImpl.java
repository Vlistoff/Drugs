package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.Drug;
import ru.vlistoff.drugstore.model.Purchases;
import ru.vlistoff.drugstore.model.Requests;
import ru.vlistoff.drugstore.repository.DrugRepository;
import ru.vlistoff.drugstore.repository.PurchasesRepository;
import ru.vlistoff.drugstore.repository.RequestsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PurchasesServiceImpl implements DatabaseService<Purchases> {
    private final PurchasesRepository repository;
    private final RequestsRepository repositoryRequests;
    private final DrugRepository repositoryDrug;

    public PurchasesServiceImpl(PurchasesRepository repository, RequestsRepository repositoryRequests, DrugRepository repositoryDrug) {
        this.repository = repository;
        this.repositoryRequests = repositoryRequests;
        this.repositoryDrug = repositoryDrug;
    }

    @Override
    public Optional<Purchases> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Purchases> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public Purchases create(Purchases purchases) {
        return repository.save(purchases);
    }

    public Purchases create(Purchases purchases, Long requestId, Long drugId) {
        Requests request = repositoryRequests.findById(requestId).orElse(null);
        Drug drug = repositoryDrug.findById(drugId).orElse(null);
        purchases.setDrug(drug);
        purchases.setRequests(request);
        return repository.save(purchases);
    }

    @Override
    public Purchases update(Purchases purchases, Long id) {
        Optional<Purchases> existingPurchasesOptional = repository.findById(id);

        if (existingPurchasesOptional.isPresent()) {
            Purchases existingPurchases = existingPurchasesOptional.get();
            existingPurchases.setDrug(purchases.getDrug());
            existingPurchases.setRequests(purchases.getRequests());
            existingPurchases.setExpirationDate(purchases.getExpirationDate());
            existingPurchases.setReleaseDate(purchases.getReleaseDate());
            existingPurchases.setQuantityReleased(purchases.getQuantityReleased());
            existingPurchases.setQuantityRequested(purchases.getQuantityRequested());
            return repository.save(existingPurchases);
        } else {
            return null;
        }
    }

    public Purchases update(Purchases purchases, Long id, Long requestId, Long drugId) {
        Optional<Purchases> existingPurchasesOptional = repository.findById(id);

        if (existingPurchasesOptional.isPresent()) {
            Purchases existingPurchases = existingPurchasesOptional.get();
            Requests request = repositoryRequests.findById(requestId).orElse(null);
            Drug drug = repositoryDrug.findById(drugId).orElse(null);
            existingPurchases.setDrug(drug);
            existingPurchases.setRequests(request);
            existingPurchases.setExpirationDate(purchases.getExpirationDate());
            existingPurchases.setReleaseDate(purchases.getReleaseDate());
            existingPurchases.setQuantityReleased(purchases.getQuantityReleased());
            existingPurchases.setQuantityRequested(purchases.getQuantityRequested());
            return repository.save(existingPurchases);
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
