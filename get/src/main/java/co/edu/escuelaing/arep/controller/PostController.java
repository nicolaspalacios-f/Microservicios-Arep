package co.edu.escuelaing.arep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.escuelaing.arep.entities.Hilo;
import co.edu.escuelaing.arep.entities.Post;
import co.edu.escuelaing.arep.services.HiloService;
import co.edu.escuelaing.arep.services.PostService;

@RestController
@RequestMapping("/api/get")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private HiloService hiloService;

    @GetMapping("/posts")
    private ResponseEntity<?> getAllPost() {
        try {
            List<Post> publicaciones = postService.getAll();
            return new ResponseEntity<>(publicaciones, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error cargar todas las publicaciones", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/hilos")
    private ResponseEntity<?> getAllHilos() {
        try {
            List<Hilo> publicaciones = hiloService.getAll();
            return new ResponseEntity<>(publicaciones, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error cargar todas las publicaciones", HttpStatus.NOT_FOUND);
        }
    }
}
