package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.ProducingCountry;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.ProducingCountryServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/producing-country")
public class ProducingCountryController {
    private final DatabaseService<ProducingCountry> service;

    public ProducingCountryController(ProducingCountryServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ProducingCountry>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducingCountry> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProducingCountry> create(@RequestBody ProducingCountry producingCountry) {
        return ResponseEntity.ok(service.create(producingCountry));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProducingCountry> update(@PathVariable Long id, @RequestBody ProducingCountry producingCountry) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(producingCountry, id));
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
