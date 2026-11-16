package com.biblio.biblio3.users.service;

import com.biblio.biblio3.users.model.User;
import com.biblio.biblio3.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable : " + id));
    }

    @Transactional(readOnly = true)
    public List<User> searchByNom(String nom) {
        return repository.findByNomCompletContainingIgnoreCase(nom);
    }

    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé.");
        }
        if (repository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username déjà utilisé.");
        }
        return repository.save(user);
    }

    public User update(Long id, User details) {
        User user = getById(id);
        user.setNomComplet(details.getNomComplet());
        user.setEmail(details.getEmail());
        user.setUsername(details.getUsername());
        user.setPassword(details.getPassword());
        return repository.save(user);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur introuvable : " + id);
        }
        repository.deleteById(id);
    }
}