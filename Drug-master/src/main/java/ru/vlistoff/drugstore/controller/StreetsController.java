package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.Streets;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.StreetsServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/streets")
public class StreetsController {
    private final StreetsServiceImpl service;

    public StreetsController(StreetsServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Streets>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Streets> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{cityId}")
    public ResponseEntity<Streets> create(@RequestBody Streets streets,@PathVariable Long cityId) {
        return ResponseEntity.ok(service.create(streets, cityId));
    }

    @PutMapping("/{id}/{cityId}")
    public ResponseEntity<Streets> update(@PathVariable Long id, @RequestBody Streets streets,@PathVariable Long cityId) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(streets, id, cityId));
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
