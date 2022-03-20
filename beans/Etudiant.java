package com.gl.todo_ameliored_version.beans;

public class Etudiant {

    //attributes
    private String cne;
    private String prenom;
    private String nom;
    private Option idOption;

    //constructor
    public Etudiant(String cne,String prenom,String nom){
        this.cne = cne;
        this.prenom = prenom;
        this.nom = nom;
    }

    //getters and setters
    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Option getIdOption() {
        return idOption;
    }

    public void setIdOption(Option idOption) {
        this.idOption = idOption;
    }

    @Override
    public boolean equals(Object obj){
        Etudiant etudiant = (Etudiant) obj;
        return this.cne.equals(etudiant.getCne());
    }
}
