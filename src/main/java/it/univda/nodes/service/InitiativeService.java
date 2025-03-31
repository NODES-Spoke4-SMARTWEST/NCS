package it.univda.nodes.service;

import it.univda.nodes.entity.Initiative;
import it.univda.nodes.repository.InitiativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitiativeService {

    @Autowired
    private InitiativeRepository initiativeRepository;

    public Initiative saveInitiative(Initiative initiative) {
        return initiativeRepository.save(initiative);
    }

    public Initiative findById(Long id) {return initiativeRepository.findById(id).orElse(null);}

    public void approveInitiative(Initiative initiative) {
        initiativeRepository.approveInitiative(initiative.getId(), initiative.isApproved());
    }
}
