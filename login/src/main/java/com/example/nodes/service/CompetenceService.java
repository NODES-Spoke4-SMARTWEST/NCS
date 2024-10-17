package com.example.nodes.service;

import com.example.nodes.entity.Competence;
import com.example.nodes.repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public List<Competence> findAllCompetences() {
        return competenceRepository.findAll();
    }

    // Additional competence-related business logic can be added here
}

