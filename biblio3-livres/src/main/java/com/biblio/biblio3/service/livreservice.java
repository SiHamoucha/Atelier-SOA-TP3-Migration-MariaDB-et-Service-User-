package com.biblio.biblio3.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblio.biblio3.model.livre;
import com.biblio.biblio3.repository.LivreRepository;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class livreservice {
 private LivreRepository repository = null;
 @Autowired
 public livreservice(LivreRepository repository) {
 this.repository = repository;
 }
 @Transactional(readOnly = true)
 public List<livre> getAllLivres() {
 return repository.findAll();
 }
 @Transactional(readOnly = true)
 public Optional<livre> getLivreById(Long id) {
 return repository.findById(id);
 }
 public livre createLivre(livre livre) {
 if (repository.existsByIsbn(livre.getIsbn())) {
 throw new IllegalArgumentException("Un livre avec cet ISBN existe déjà.");
 }
 return repository.save(livre);
 }
 public livre updateLivre(Long id, livre details) {
 livre livre = repository.findById(id)
 .orElseThrow(() -> new IllegalArgumentException("Livre introuvable : " + id));
 livre.setTitre(details.getTitre());
 livre.setAuteur(details.getAuteur());
 livre.setIsbn(details.getIsbn());
 livre.setDisponible(details.isDisponible());
 return repository.save(livre);
 }
 public void deleteLivre(Long id) {
 if (!repository.existsById(id)) {
 throw new IllegalArgumentException("Livre introuvable : " + id);
 }
 repository.deleteById(id);
 }
 @Transactional(readOnly = true)
 public List<livre> getLivresDisponibles() {
 return repository.findByDisponibleTrue();
 }
}
