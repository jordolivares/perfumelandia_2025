package com.example.perfumelandia.service;

import com.example.perfumelandia.model.Rol;
import com.example.perfumelandia.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {return rolRepository.findAll();}

    public Rol findById(Long id) {return rolRepository.findById(id).get();}

    public Rol save(Rol rol) {return rolRepository.save(rol);}

    public void delete(Long id){rolRepository.deleteById(id);}

}
