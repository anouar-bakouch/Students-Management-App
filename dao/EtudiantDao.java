package com.gl.todo_ameliored_version.dao;

import com.gl.todo_ameliored_version.beans.Etudiant;

import java.sql.SQLException;
import java.util.List;

public interface EtudiantDao {

    public Etudiant add(Etudiant etudiant) throws SQLException;

    public Etudiant update(Etudiant etudiant) throws SQLException;

    public Etudiant delete(Etudiant etudiant) throws SQLException;

    public Etudiant getEtudiant(String cne) throws SQLException;

    public List<Etudiant> getEtudiants() throws SQLException;

}
