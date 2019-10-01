//package com.example.spotify3.models;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "song")
//public class Song {
//
//    @Id
//    @Column
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String title;
//
//    @Column
//    private int length;
//
//    //mapping Song table to User table
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH,
//                    CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(name = "users",
//            joinColumns = {@JoinColumn(name = "song_id")},
//            inverseJoinColumns = @JoinColumn(name = "users_id"))
//    private List<User> users;
//
//    public Song() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getLength() {
//        return length;
//    }
//
//    public void setLength(int length) {
//        this.length = length;
//    }
//
//}
