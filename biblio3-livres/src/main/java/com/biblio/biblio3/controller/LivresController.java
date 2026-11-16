package com.biblio.biblio3.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.biblio.biblio3.model.livre;
import com.biblio.biblio3.service.livreservice;

import java.util.List;
@RestController
@RequestMapping("/api/livres")
public class LivresController {
 private final livreservice service;
 @Autowired
 public LivresController(livreservice service) {
 this.service = service;
 }
 @GetMapping
 public List<livre> getAll() {
 return service.getAllLivres();
 }
 @GetMapping("/{id}")
 public ResponseEntity<livre> getById(@PathVariable Long id) {
 return service.getLivreById(id)
 .map(ResponseEntity::ok)
 .orElseGet(() -> ResponseEntity.notFound().build());
 }
 @GetMapping("/disponibles")
 public List<livre> getDisponibles() {
 return service.getLivresDisponibles();
 }
 @PostMapping
 public ResponseEntity<?> create(@RequestBody livre livre) {
 try {
 return ResponseEntity.status(201).body(service.createLivre(livre));
 } catch (IllegalArgumentException e) {
 return ResponseEntity.badRequest().body(e.getMessage());
 }
 }
 @PutMapping("/{id}")
 public ResponseEntity<?> update(@PathVariable Long id, @RequestBody livre details) {
 try {
 return ResponseEntity.ok(service.updateLivre(id, details));
 } catch (IllegalArgumentException e) {
 return ResponseEntity.badRequest().body(e.getMessage());
 }
 }
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id) {
 try {
 service.deleteLivre(id);
 return ResponseEntity.noContent().build();
 } catch (IllegalArgumentException e) {
 return ResponseEntity.notFound().build();
 }
 }
}