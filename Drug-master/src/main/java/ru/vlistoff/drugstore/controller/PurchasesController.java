package ru.vlistoff.drugstore.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.Purchases;
import ru.vlistoff.drugstore.service.PurchasesServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    private final PurchasesServiceImpl service;

    public PurchasesController(PurchasesServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Purchases>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchases> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{requestId}/{drugId}")
    public ResponseEntity<Purchases> create(@RequestBody Purchases purchases,@PathVariable Long requestId,
                                            @PathVariable Long drugId) {
        return ResponseEntity.ok(service.create(purchases, requestId, drugId));
    }

    @PutMapping("/{id}/{requestId}/{drugId}")
    public ResponseEntity<Purchases> update(@PathVariable Long id, @RequestBody Purchases purchases,
                                            @PathVariable Long requestId, @PathVariable Long drugId) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(purchases, id, requestId, drugId));
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
