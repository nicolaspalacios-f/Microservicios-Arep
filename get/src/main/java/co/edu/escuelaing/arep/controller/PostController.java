package co.edu.escuelaing.arep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.escuelaing.arep.entities.Post;

import co.edu.escuelaing.arep.services.PostService;

@RestController
@RequestMapping("/api/get")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    private ResponseEntity<?> getAllPost() {
        try {
            List<Post> publicaciones = postService.getAll();
            return new ResponseEntity<>(publicaciones, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error cargar todas las publicaciones", HttpStatus.NOT_FOUND);
        }
    }

}
