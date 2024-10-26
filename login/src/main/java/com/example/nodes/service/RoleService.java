package com.example.nodes.service;

//import com.example.nodes.repository.BookingRepository;
import com.example.nodes.entity.Booking;
import com.example.nodes.entity.Hub;
import com.example.nodes.entity.Role;
import com.example.nodes.repository.BookingRepository;
import com.example.nodes.repository.RoleRepository;
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
