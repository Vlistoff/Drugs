package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.Cities;
import ru.vlistoff.drugstore.service.CitiesServiceImpl;
import ru.vlistoff.drugstore.service.DatabaseService;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {
    private final DatabaseService<Cities> service;

    public CitiesController(CitiesServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Cities>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cities> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Cities> create(@RequestBody Cities cities) {
        return ResponseEntity.ok(service.create(cities));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cities> update(@PathVariable Long id, @RequestBody Cities cities) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(cities, id));
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
