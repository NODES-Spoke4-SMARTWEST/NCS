package it.univda.nodes.service;

import it.univda.nodes.entity.Announcement;
import it.univda.nodes.entity.Competence;
import it.univda.nodes.entity.Interest;
import it.univda.nodes.entity.User;
import it.univda.nodes.repository.AnnouncementRepository;
import it.univda.nodes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Announcement> getAnnouncementsForUser(List<Competence> competences, List<Interest> interests) {
        return announcementRepository.findByCompetencesInAndInterestsIn(competences, interests);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    public List<Announcement> getAllActiveAnnouncements() {
        return announcementRepository.findByIsDeletedFalse();
    }

    public void flagAsDeleted(Long id, String username) {
        Optional<Announcement> optional = announcementRepository.findById(id);
        User currentUser = userRepository.findByName(username); // assumes this method exists

        if (optional.isPresent()) {
            Announcement announcement = optional.get();

            if (announcement.getAuthor().getId().equals(currentUser.getId())) {
                announcement.setDeleted(true);
                announcementRepository.save(announcement);
            } else {
                throw new SecurityException("Unauthorized attempt to delete another user's announcement.");
            }
        } else {
            throw new IllegalArgumentException("Announcement or user not found.");
        }
    }
}