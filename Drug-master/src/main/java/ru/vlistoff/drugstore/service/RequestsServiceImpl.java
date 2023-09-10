package ru.vlistoff.drugstore.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.vlistoff.drugstore.model.Drugstores;
import ru.vlistoff.drugstore.model.Requests;
import ru.vlistoff.drugstore.repository.RequestsRepository;
import ru.vlistoff.drugstore.repository.DrugstoresRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RequestsServiceImpl implements DatabaseService<Requests> {
    private final RequestsRepository repository;
    private final DrugstoresRepository repositoryDrugstores;

    public RequestsServiceImpl(RequestsRepository repository, DrugstoresRepository repositoryDrugstores) {
        this.repository = repository;
        this.repositoryDrugstores = repositoryDrugstores;
    }

    @Override
    public Optional<Requests> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Requests> getAll() {
        return repository.findAll(Sort.by("id"));
    }

    @Override
    public Requests create(Requests requests) {
        return repository.save(requests);
    }

    public Requests create(Requests requests, Long drugstoreId) {
        Drugstores drugstore = repositoryDrugstores.findById(drugstoreId).orElse(null);
        requests.setDrugstores(drugstore);
        return repository.save(requests);
    }

    @Override
    public Requests update(Requests requests, Long id) {
        Optional<Requests> existingRequestsOptional = repository.findById(id);

        if (existingRequestsOptional.isPresent()) {
            Requests existingRequests = existingRequestsOptional.get();
            existingRequests.setDateRequest(requests.getDateRequest());
            existingRequests.setRequestsNumber(requests.getRequestsNumber());
            existingRequests.setDateCompletionRequest(requests.getDateCompletionRequest());
            existingRequests.setDrugstores(requests.getDrugstores());
            return repository.save(existingRequests);
        } else {
            return null;
        }
    }

    public Requests update(Requests requests, Long id, Long drugstoreId) {
        Optional<Requests> existingRequestsOptional = repository.findById(id);

        if (existingRequestsOptional.isPresent()) {
            Requests existingRequests = existingRequestsOptional.get();
            Drugstores drugstore = repositoryDrugstores.findById(drugstoreId).orElse(null);
            existingRequests.setDrugstores(drugstore);
            existingRequests.setDateRequest(requests.getDateRequest());
            existingRequests.setRequestsNumber(requests.getRequestsNumber());
            existingRequests.setDateCompletionRequest(requests.getDateCompletionRequest());
            return repository.save(existingRequests);
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
