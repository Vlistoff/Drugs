package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.Manufacturer;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.ManufacturerServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerServiceImpl service;

    public ManufacturerController(ManufacturerServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Manufacturer>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{producingCountryId}")
    public ResponseEntity<Manufacturer> create(@RequestBody Manufacturer manufacturer,
                                               @PathVariable Long producingCountryId) {
        return ResponseEntity.ok(service.create(manufacturer, producingCountryId));
    }

    @PutMapping("/{id}/{producingCountryId}")
    public ResponseEntity<Manufacturer> update(@PathVariable Long id, @RequestBody Manufacturer manufacturer,
                                               @PathVariable Long producingCountryId) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(manufacturer, id, producingCountryId));
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
