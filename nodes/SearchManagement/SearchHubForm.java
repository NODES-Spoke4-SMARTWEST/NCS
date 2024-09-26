package com.application.nodes.SearchManagement;

import com.application.nodes.MapManagement.Hub;
import com.application.nodes.ResourcesManagement.Resource;

import java.util.ArrayList;

public class SearchHubForm extends SearchForm {
    private ArrayList<Hub> hubs;
    private ArrayList<Resource> resources;

    public SearchHubForm() {
        super();
        this.hubs = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public void addHub(Hub hub) {
        this.hubs.add(hub);
    }

    public void removeHub(Hub hub) {
        this.hubs.remove(hub);
    }

    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    public void removeResource(Resource resource) {
        this.resources.remove(resource);
    }

    public ArrayList<Hub> getHubs() {
        return hubs;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }
}

