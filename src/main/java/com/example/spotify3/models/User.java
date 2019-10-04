package com.example.spotify3.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;


    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    //mapping users to user_role table
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;

    public UserRole getUserRole() { return userRole; }

    public void setUserRole(UserRole userRole) { this.userRole = userRole; }

    //mapping User table to Song table
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_songs",
            joinColumns = {@JoinColumn(name = "songs_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Song> songs;
    public List<Song> getSongs(){ return songs; }
    public void setSongs(List<Song> songs) { this.songs = songs; }

    //this allows user to add songs to their profile
    public List<Song> addSong(Song song){
        if(songs == null)
            songs = new ArrayList<>();
        songs.add(song);

        return songs;
    }

}