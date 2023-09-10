package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.Requests;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.RequestsServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestsController {
    private final RequestsServiceImpl service;

    public RequestsController(RequestsServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Requests>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requests> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{drugstoreId}")
    public ResponseEntity<Requests> create(@RequestBody Requests requests,@PathVariable Long drugstoreId) {
        return ResponseEntity.ok(service.create(requests, drugstoreId));
    }

    @PutMapping("/{id}/{drugstoreId}")
    public ResponseEntity<Requests> update(@PathVariable Long id, @RequestBody Requests requests,
                                           @PathVariable Long drugstoreId) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(requests, id, drugstoreId));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            service.delete(id);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));
            return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body("Успешно!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
