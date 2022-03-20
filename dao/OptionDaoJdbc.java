package com.gl.todo_ameliored_version.dao;

import com.gl.todo_ameliored_version.beans.Option;
import com.gl.todo_ameliored_version.utils.MySql;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OptionDaoJdbc implements OptionDao{

    private MySql mySql;
    private Connection con;

    public OptionDaoJdbc() throws SQLException {
        mySql = MySql.getInstance();
        con = mySql.getConnection();
    }

    @Override
    public Option add(Option option) throws SQLException {

        String query = "insert into db.option values ('"+option.getNom()+"','"+option.getDescription()+"') ";
        Statement stmt = con.createStatement();

        for(Option option_ : getOptions()){
            if(option_.equals(option)){
                return null;
            }
        }

        stmt.executeUpdate(query);

        return option;
    }


    @Override
    public Option update(Option option) throws SQLException {

        String query = "update db.option set nom = '"+option.getNom()+"',description = '"+option.getDescription()+"' where nom = '"+option.getNom()+"' ";
        Statement stmt = con.createStatement();

        for(Option option_ : getOptions()){

            if(option_.equals(option)){
                stmt.executeUpdate(query);
                return option;
            }

        }

        return null;
    }

    @Override
    public Option delete(Option option) throws SQLException {

        String query = "delete from db.option where nom ='"+option.getNom()+"'";
        Statement stmt = con.createStatement();

        for(Option option_ : getOptions()){

            if(option_.equals(option)){
                stmt.executeUpdate(query);
                return option;
            }

        }

        return null;
    }

    @Override
    public Option getOption(String nom) throws SQLException {

        String query = "select * from db.option where nom = '"+nom+"'";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        if(!res.next()) return null;
        String description = res.getString("description");
        Option option = new Option(nom,description);
        res.next();
        return option;

    }

    @Override
    public List<Option> getOptions() throws SQLException {

        List<Option> options = new ArrayList<>();

        String query = "select distinct * from db.option";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);

        while(res.next()){

            String nom = res.getString("nom");
            String description = res.getString("description");
            Option option = new Option(nom,description);

            options.add(option);
        }

        return options;
    }
}
