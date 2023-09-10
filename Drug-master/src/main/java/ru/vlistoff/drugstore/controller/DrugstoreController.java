package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.Drugstores;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.DrugstoresServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/drugstore")
public class DrugstoreController {
    private final DrugstoresServiceImpl service;

    public DrugstoreController(DrugstoresServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Drugstores>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drugstores> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{cityId}/{streetId}")
    public ResponseEntity<Drugstores> create(@RequestBody Drugstores drugstore,@PathVariable Long cityId,
                                             @PathVariable Long streetId) {
        return ResponseEntity.ok(service.create(drugstore, cityId, streetId));
    }

    @PutMapping("/{id}/{cityId}/{streetId}")
    public ResponseEntity<Drugstores> update(@PathVariable Long id, @RequestBody Drugstores drugstore,
                                             @PathVariable Long cityId, @PathVariable Long streetId) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(drugstore, id, cityId, streetId));
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
