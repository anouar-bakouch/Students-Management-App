package com.gl.todo_ameliored_version.dao;

import com.gl.todo_ameliored_version.beans.Option;

import java.sql.SQLException;
import java.util.List;

public interface OptionDao {

    public Option add(Option option) throws SQLException;

    public Option update(Option option) throws SQLException;

    public Option delete(Option option) throws SQLException;

    public Option getOption(String nom) throws SQLException;

    public List<Option> getOptions() throws SQLException;

}
