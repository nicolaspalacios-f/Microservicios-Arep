package co.edu.escuelaing.arep.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Hilo")
public class Hilo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "author")
    private String author;
    @Column(name = "title")
    private String title;

    public Hilo() {
    }

    public Hilo(Long id, String author, String title, String texto) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Hilo(String author, String title, String texto) {
        this.author = author;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitlt(String title) {
        this.title = title;
    }

}
