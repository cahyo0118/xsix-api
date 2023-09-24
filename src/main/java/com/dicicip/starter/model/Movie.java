package com.dicicip.starter.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    public Double rating;
    public String image;
    public Date created_at;
    public Date updated_at;

}
