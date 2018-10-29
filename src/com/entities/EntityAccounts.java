package com.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", schema = "videostore_accounts")
public class EntityAccounts {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_taken_videotapes", joinColumns=@JoinColumn(name="id"))
    @Column(name = "taken_videotapes")
    private List<String> namesOfTakenVideotapes = new ArrayList<>();
    @ElementCollection
    @Column(name = "taken_videotapes")
    @CollectionTable(name = "user_book_dates", joinColumns=@JoinColumn(name="id"))
    private List<String> videotapeBookDate = new ArrayList<>();
    @ElementCollection
    @Column(name = "taken_videotapes")
    @CollectionTable(name = "user_return_dates", joinColumns=@JoinColumn(name="id"))
    private List<String> videotapeReturnDate = new ArrayList<>();

    public EntityAccounts() {
    }

    public EntityAccounts(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

    public List<String> getNamesOfTakenVideotapes() {
        return namesOfTakenVideotapes;
    }

    public void setNamesOfTakenVideotapes(List<String> namesOfTakenVideotapes) {
        this.namesOfTakenVideotapes = namesOfTakenVideotapes;
    }

    public List<String> getVideotapeBookDate() {
        return videotapeBookDate;
    }

    public void setVideotapeBookDate(List<String> videotapeBookDate) {
        this.videotapeBookDate = videotapeBookDate;
    }

    public List<String> getVideotapeReturnDate() {
        return videotapeReturnDate;
    }

    public void setVideotapeReturnDate(List<String> videotapeReturnDate) {
        this.videotapeReturnDate = videotapeReturnDate;
    }
}
