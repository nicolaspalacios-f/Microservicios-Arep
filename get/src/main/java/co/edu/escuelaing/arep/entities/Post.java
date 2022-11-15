package co.edu.escuelaing.arep.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Publicaciones")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "author")
    private String author;
    @ManyToOne
    @JoinColumn(name = "hilo", nullable = false)
    private Hilo hilo;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;

    public Post() {
    }

    public Post(Long id, String author, Hilo hilo, String titulo, String texto) {
        this.id = id;
        this.author = author;
        this.hilo = hilo;
        this.title = titulo;
        this.text = texto;
    }

    public Post(String author, Hilo hilo, String titulo, String texto) {
        this.author = author;
        this.title = titulo;
        this.text = texto;
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

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Hilo getHilo() {
        return hilo;
    }

    public void setHilo(Hilo hilo) {
        this.hilo = hilo;
    }

}
