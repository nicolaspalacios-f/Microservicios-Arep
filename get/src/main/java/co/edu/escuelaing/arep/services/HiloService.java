package co.edu.escuelaing.arep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.escuelaing.arep.entities.Hilo;
import co.edu.escuelaing.arep.repository.HiloReposository;

@Service
public class HiloService {

    @Autowired
    private HiloReposository hiloRepository;

    public List<Hilo> getAll() {
        return hiloRepository.findAll();
    }

}
