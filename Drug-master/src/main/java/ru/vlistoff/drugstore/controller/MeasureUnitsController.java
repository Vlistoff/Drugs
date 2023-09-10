package ru.vlistoff.drugstore.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vlistoff.drugstore.model.MeasureUnits;
import ru.vlistoff.drugstore.service.DatabaseService;
import ru.vlistoff.drugstore.service.MeasureUnitsServiceImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/measure-units")
public class MeasureUnitsController {
    private final DatabaseService<MeasureUnits> service;

    public MeasureUnitsController(MeasureUnitsServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<MeasureUnits>> getAll() {
        if (service.getAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(service.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasureUnits> getById(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.getById(id).orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<MeasureUnits> create(@RequestBody MeasureUnits measureUnits) {
        return ResponseEntity.ok(service.create(measureUnits));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeasureUnits> update(@PathVariable Long id, @RequestBody MeasureUnits measureUnits) {
        if (service.getById(id).isPresent()) {
            return ResponseEntity.ok(service.update(measureUnits, id));
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
