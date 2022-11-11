package co.edu.escuelaing.arep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.escuelaing.arep.entities.Hilo;
import co.edu.escuelaing.arep.entities.Post;
import co.edu.escuelaing.arep.services.HiloService;
import co.edu.escuelaing.arep.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private HiloService hiloService;

    @PostMapping("/savePost")
    private ResponseEntity<Boolean> save(@RequestBody Post post) {
        try {
            postService.create(post);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/saveHilo")
    private ResponseEntity<Boolean> save(@RequestBody Hilo hilo) {
        try {
            hiloService.create(hilo);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
