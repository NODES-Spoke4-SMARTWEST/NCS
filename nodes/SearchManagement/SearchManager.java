package com.application.nodes.SearchManagement;

import com.application.nodes.MapManagement.Coordinates;
import com.application.nodes.MapManagement.Hub;
import com.application.nodes.MapManagement.Map;
import com.application.nodes.ResourcesManagement.Resource;
import com.application.nodes.UserManagement.Competence;
import com.application.nodes.UserManagement.Interest;
import com.application.nodes.UserManagement.User;

import java.util.ArrayList;

public class SearchManager {

    private SearchHubForm currentHubForm;
    private SearchUserForm currentUserForm;
    private Map currentMap;

    public void askToSearchHub() {
        // Implementation here
    }

    public void selectHubCriteria(ArrayList<Competence> competences, ArrayList<Interest> interests) {
        if (currentHubForm == null) {
            currentHubForm = new SearchHubForm();
        }
        competences.forEach(currentHubForm::addCompetence);
        interests.forEach(currentHubForm::addInterest);
    }

    public void selectHubByResources(ArrayList<Resource> resources) {
        // Implementation here
    }

    public void selectHubByLocation(Coordinates location) {
        // Implementation here
    }

    public void chooseHub(Hub hub) {
        // Implementation here
    }

    private void setCurrentHubForm(SearchHubForm currentHubForm) {
        this.currentHubForm = currentHubForm;
    }

    private void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    public void askToSearchAgent() {
        // Implementation here
    }

    public void selectAgent(String name) {
        // Implementation here
    }

    public void selectAgentCriteria(ArrayList<Competence> competences, ArrayList<Interest> interests) {
        if (currentUserForm == null) {
            currentUserForm = new SearchUserForm();
        }
        competences.forEach(currentUserForm::addCompetence);
        interests.forEach(currentUserForm::addInterest);
    }

    public void selectAgent(User user) {
        // Implementation here
    }

    public void askToChat(User user) {
        // Implementation here
    }

    private void setCurrentUserForm(SearchUserForm currentUserForm) {
        this.currentUserForm = currentUserForm;
    }
}
