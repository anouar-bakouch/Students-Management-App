package com.gl.todo_ameliored_version.presentation.controllers;

import com.gl.todo_ameliored_version.beans.Option;
import com.gl.todo_ameliored_version.business.DefaultServices;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/addOption")
@MultipartConfig
public class AddOptionServlet extends HttpServlet {

    private DefaultServices defaultServices;

    @Override
    public void init() throws ServletException {
        try {
            defaultServices = DefaultServices.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    try {
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");

        if (defaultServices.add(new Option(nom, description)) == null)
            resp.getWriter().append("0");
        else
            resp.getWriter().append("1");

    }catch(Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
    }

    }
}










