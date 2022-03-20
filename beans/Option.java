package com.gl.todo_ameliored_version.beans;

public class Option {

    //attributes
    private String nom;
    private String description;

    //constructor
    public Option(String nom,String description){
        this.nom = nom;
        this.description = description;
    }

    //getters and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj){
        Option option = (Option) obj;
        return this.nom.equals(option.getNom());
    }

}
