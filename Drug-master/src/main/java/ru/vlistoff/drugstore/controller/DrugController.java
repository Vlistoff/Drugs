package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.vlistoff.drugstore.model.Drug;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.DrugServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/drugs")
public class DrugController {
    private final DrugServiceImpl service;

    public DrugController(DrugServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Drug>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drug> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create/{manufacturerId}/{measureUnitId}")
    public ResponseEntity<Drug> create(@RequestBody Drug drug,@PathVariable Long manufacturerId,
                                       @PathVariable Long measureUnitId) {
        return ResponseEntity.ok(service.create(drug, manufacturerId, measureUnitId));
    }

    @PutMapping("/{id}/{manufacturerId}/{measureUnitId}")
    public ResponseEntity<Drug> update(@PathVariable Long id, @RequestBody Drug drug,@PathVariable Long manufacturerId,
                                       @PathVariable Long measureUnitId) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(drug, id, manufacturerId, measureUnitId));
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
