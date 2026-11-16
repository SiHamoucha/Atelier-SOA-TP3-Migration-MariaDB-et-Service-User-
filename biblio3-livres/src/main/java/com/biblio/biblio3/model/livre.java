package com.biblio.biblio3.model;
import jakarta.persistence.*;
@Entity
@Table(name = "livres")
public class livre {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(nullable = false, length = 150)
 private String titre;
 @Column(nullable = false, length = 100)
 private String auteur;
 @Column(nullable = false, unique = true, length = 20)
 private String isbn;
 private boolean disponible = true;
 protected livre() {
 // Constructeur requis par JPA.
 }
 public livre(String titre, String auteur, String isbn) {
 this.titre = titre;
 this.auteur = auteur;
 this.isbn = isbn;
 }
 // Getters et setters : Source > Generate Getters and Setters.
 public Long getId() {
	return id;
 }
 public void setId(Long id) {
	this.id = id;
 }
 public String getTitre() {
	return titre;
 }
 public void setTitre(String titre) {
	this.titre = titre;
 }
 public String getAuteur() {
	return auteur;
 }
 public void setAuteur(String auteur) {
	this.auteur = auteur;
 }
 public String getIsbn() {
	return isbn;
 }
 public void setIsbn(String isbn) {
	this.isbn = isbn;
 }
 public boolean isDisponible() {
	return disponible;
 }
 public void setDisponible(boolean disponible) {
	this.disponible = disponible;
 }
}