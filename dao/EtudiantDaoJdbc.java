package com.gl.todo_ameliored_version.dao;
import com.gl.todo_ameliored_version.beans.Etudiant;
import com.gl.todo_ameliored_version.beans.Option;
import com.gl.todo_ameliored_version.utils.MySql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoJdbc implements EtudiantDao{

    private MySql mySql;
    private Connection con;

    public EtudiantDaoJdbc() throws SQLException {
        mySql = MySql.getInstance();
        con = mySql.getConnection();
    }

    @Override
    public Etudiant add(Etudiant etudiant) throws SQLException {

        String query = "insert into db.etudiant values ('"+etudiant.getCne()+"','"+etudiant.getPrenom()+"','"+etudiant.getNom()+"','"+etudiant.getIdOption().getNom()+"')";

        Statement stmt = con.createStatement();

        for(Etudiant etudiant_ : getEtudiants()){

            if(etudiant_.equals(etudiant)){
                return null;
            }
        }

        stmt.executeUpdate(query);

        return etudiant;
    }

    @Override
    public Etudiant update(Etudiant etudiant) throws SQLException {

        String query = "update db.etudiant set cne = '"+etudiant.getCne()+"' , prenom = '"+etudiant.getPrenom()+"',nom = '"+etudiant.getNom()+"',idOption = '"+etudiant.getIdOption().getNom()+"' where cne = '"+etudiant.getCne()+"' ";
        Statement stmt = con.createStatement();

        for(Etudiant etudiant_ : getEtudiants()){
            if(etudiant_.equals(etudiant)){
                stmt.executeUpdate(query);
                return etudiant;
            }
        }
        return null;
    }

    @Override
    public Etudiant delete(Etudiant etudiant) throws SQLException {

        String query = "delete from db.etudiant where cne = '"+etudiant.getCne()+"' ";
        Statement stmt = con.createStatement();

        for(Etudiant etudiant_ : getEtudiants()){
            if(etudiant_.equals(etudiant)){
                stmt.executeUpdate(query);
                return etudiant;
            }
        }

        return null;
    }

    @Override
    public Etudiant getEtudiant(String cne) throws SQLException {

        String query = "select * from db.etudiant where cne = '"+cne+"' ";
        Statement stmt = con.createStatement();
        ResultSet res  =stmt.executeQuery(query);
        if(!res.next()) return null;

        String prenom = res.getString("prenom");
        String nom = res.getString("nom");
        String idOption = res.getString("idOption");
        Etudiant etudiant = new Etudiant(cne,prenom,nom);
        res.next();
        String query2 = "select description from db.option where nom = '"+idOption+"' ";
        Statement stmt2 = con.createStatement();
        ResultSet res2 = stmt2.executeQuery(query2);
        if(res2.next()) return null;
        String description = res2.getString("description");
        res2.next();
        etudiant.setIdOption(new Option(idOption,description));

        return etudiant;
    }

    @Override
    public List<Etudiant> getEtudiants() throws SQLException {

        List<Etudiant> etudiants = new ArrayList<>();

        String query = "select * from db.etudiant";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while(res.next()){

            String cne = res.getString("cne");
            String prenom = res.getString("prenom");
            String nom = res.getString("nom");
            String idOption = res.getString("idOption");

            //getting the description.

            String query2 = "select description from db.option where nom = '"+idOption+"' ";
            Statement stmt2 = con.createStatement();
            ResultSet res2 = stmt2.executeQuery(query2);

            if(!res2.next()) return null;
            String description = res2.getString("description");
            Etudiant etudiant = new Etudiant(cne,prenom,nom);
            etudiant.setIdOption(new Option(idOption,description));

            etudiants.add(etudiant);
        }


        return etudiants;
    }
}
