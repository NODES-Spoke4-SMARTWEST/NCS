package it.univda.nodes.service;

//import com.example.nodes.repository.BookingRepository;
import it.univda.nodes.entity.Role;
import it.univda.nodes.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
