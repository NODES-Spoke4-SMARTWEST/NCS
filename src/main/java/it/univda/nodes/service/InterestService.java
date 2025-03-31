package it.univda.nodes.service;

import it.univda.nodes.entity.Interest;
import it.univda.nodes.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {

    @Autowired
    private InterestRepository interestRepository;

    public List<Interest> findAllInterests() {
        return interestRepository.findAll();
    }

    public Interest findById(long interest) {
        return interestRepository.findById(interest);
    }

    // Additional interest-related business logic can be added here
}
