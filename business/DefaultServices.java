package com.gl.todo_ameliored_version.business;

import com.gl.todo_ameliored_version.beans.Etudiant;
import com.gl.todo_ameliored_version.beans.Option;
import com.gl.todo_ameliored_version.dao.EtudiantDao;
import com.gl.todo_ameliored_version.dao.EtudiantDaoJdbc;
import com.gl.todo_ameliored_version.dao.OptionDao;
import com.gl.todo_ameliored_version.dao.OptionDaoJdbc;
import java.sql.SQLException;
import java.util.List;

public class DefaultServices implements Service{

    private static DefaultServices instance;
    private EtudiantDao etudiantDao;
    private OptionDao optionDao;

    private DefaultServices() throws SQLException {
        this.etudiantDao = new EtudiantDaoJdbc();
        this.optionDao = new OptionDaoJdbc();
    }

    public static DefaultServices getInstance() throws SQLException {

        if(instance == null) instance = new DefaultServices();

        return instance;

    }

    @Override
    public Etudiant add(Etudiant etudiant) throws SQLException {
        return etudiantDao.add(etudiant);
    }

    @Override
    public Etudiant update(Etudiant etudiant) throws SQLException {
        return etudiantDao.update(etudiant);
    }

    @Override
    public Etudiant delete(Etudiant etudiant) throws SQLException {
        return etudiantDao.delete(etudiant);
    }

    @Override
    public Etudiant getEtudiant(String cne) throws SQLException {
        return etudiantDao.getEtudiant(cne);
    }

    @Override
    public List<Etudiant> getEtudiants() throws SQLException {
        return etudiantDao.getEtudiants();
    }

    //options

    @Override
    public Option add(Option option) throws SQLException {
        return optionDao.add(option);
    }

    @Override
    public Option update(Option option) throws SQLException {
        return optionDao.update(option);
    }

    @Override
    public Option delete(Option option) throws SQLException {
        return optionDao.delete(option);
    }

    @Override
    public Option getOption(String nom) throws SQLException {
        return optionDao.getOption(nom);
    }

    @Override
    public List<Option> getOptions() throws SQLException {
        return optionDao.getOptions();
    }
}
