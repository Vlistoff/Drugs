package ru.vlistoff.drugstore.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.DrugPrices;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.DrugPricesServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/drug-prices")
public class DrugPricesController {
    private final DrugPricesServiceImpl service;

    public DrugPricesController(DrugPricesServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<DrugPrices>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugPrices> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{drugId}")
    public ResponseEntity<DrugPrices> create(@RequestBody DrugPrices drugPrices,@PathVariable Long drugId) {
        return ResponseEntity.ok(service.create(drugPrices, drugId));
    }

    @PutMapping("/{id}/{drugId}")
    public ResponseEntity<DrugPrices> update(@PathVariable Long id, @RequestBody DrugPrices drugPrices,
                                             @PathVariable Long drugId) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(drugPrices, id, drugId));
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
