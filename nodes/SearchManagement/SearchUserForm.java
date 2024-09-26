package com.application.nodes.SearchManagement;

import com.application.nodes.UserManagement.User;

import java.util.ArrayList;

public class SearchUserForm extends SearchForm {
    private String name;
    private ArrayList<User> users;

    public SearchUserForm() {
        super();
        this.users = new ArrayList<>();
    }

    public void searchName(String name) {
        this.name = name;
        // Implementation for searching by name
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public void setListofUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
