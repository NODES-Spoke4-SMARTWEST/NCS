package com.example.nodes.service;

import com.example.nodes.entity.Announcement;
import com.example.nodes.entity.Competence;
import com.example.nodes.entity.Interest;
import com.example.nodes.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAnnouncementsForUser(List<Competence> competences, List<Interest> interests) {
        return announcementRepository.findByCompetencesInAndInterestsIn(competences, interests);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }
}