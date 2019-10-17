package sample;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String email;
    private ArrayList<Group> groupMember = new ArrayList<>();
    private ArrayList<Group> groupLeader = new ArrayList<>();

    public User(){};

    public User (String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Group> getGroupMember(){
        return groupMember;
    }

    public void addGroupMember(Group group) {
        groupMember.add(group);
    }

    public ArrayList<Group> getGroupLeader() {
        return groupLeader;
    }

    public void addGroupLeader(Group group) {
        groupLeader.add(group);
    }

}
