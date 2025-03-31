package it.univda.nodes.service;

import it.univda.nodes.entity.Competence;
import it.univda.nodes.repository.CompetenceRepository;
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

    public Competence findById(long competence) {
        return competenceRepository.findById(competence);
    }

    // Additional competence-related business logic can be added here
}

