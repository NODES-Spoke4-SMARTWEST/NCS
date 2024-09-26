package com.application.nodes.SearchManagement;

import com.application.nodes.UserManagement.Competence;
import com.application.nodes.UserManagement.Interest;
import java.util.ArrayList;

public class SearchForm {
    private ArrayList<Competence> competences;
    private ArrayList<Interest> interests;

    public SearchForm() {
        this.competences = new ArrayList<>();
        this.interests = new ArrayList<>();
    }

    public void create() {
        // Implementation for creating the form
    }

    public void addCompetence(Competence competence) {
        this.competences.add(competence);
    }

    public void removeCompetence(Competence competence) {
        this.competences.remove(competence);
    }

    public void addInterest(Interest interest) {
        this.interests.add(interest);
    }

    public void removeInterest(Interest interest) {
        this.interests.remove(interest);
    }

    public ArrayList<Competence> getCompetences() {
        return competences;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }
}

