package com.gl.todo_ameliored_version.business;

import com.gl.todo_ameliored_version.beans.Etudiant;
import com.gl.todo_ameliored_version.beans.Option;
import java.sql.SQLException;
import java.util.List;

public interface Service {

    //Etudiant

    public Etudiant add(Etudiant etudiant) throws SQLException;

    public Etudiant update(Etudiant etudiant) throws SQLException;

    public Etudiant delete(Etudiant etudiant) throws SQLException;

    public Etudiant getEtudiant(String cne) throws SQLException;

    public List<Etudiant> getEtudiants() throws SQLException;

    //Option

    public Option add(Option option) throws SQLException;

    public Option update(Option option) throws SQLException;

    public Option delete(Option option) throws SQLException;

    public Option getOption(String nom) throws SQLException;

    public List<Option> getOptions() throws SQLException;

}
