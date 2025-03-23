package com.example.nodes.service;

import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Interest;
import com.example.nodes.repository.InterestRepository;
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
