package co.edu.escuelaing.arep.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.escuelaing.arep.entities.Post;
import co.edu.escuelaing.arep.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void create(Post post) {
        postRepository.save(post);
    }

}
