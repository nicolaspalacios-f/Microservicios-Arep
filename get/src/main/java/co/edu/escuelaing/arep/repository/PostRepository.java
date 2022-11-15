package co.edu.escuelaing.arep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.escuelaing.arep.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
