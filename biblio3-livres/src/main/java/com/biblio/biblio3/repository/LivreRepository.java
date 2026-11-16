package com.biblio.biblio3.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.biblio.biblio3.model.livre;

import java.util.List;
import java.util.Optional;
public interface LivreRepository extends JpaRepository<livre, Long> {

 List<livre> findByDisponibleTrue();
 List<livre> findByAuteurContainingIgnoreCase(String auteur);
 List<livre> findByTitreContainingIgnoreCase(String titre);
 boolean existsByIsbn(String isbn);
 Optional<livre> findByIsbn(String isbn);
}
