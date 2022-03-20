package com.gl.todo_ameliored_version.presentation.controllers;

import com.gl.todo_ameliored_version.beans.Etudiant;
import com.gl.todo_ameliored_version.business.DefaultServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/listEtudiants")
@MultipartConfig
public class ListEtudiantServlet extends HttpServlet {

    private DefaultServices defaultServices;
    private List<Etudiant> etudiants;
    private Gson gson;
    private GsonBuilder gsonBuilder;

    @Override
    public void init() throws ServletException {
        try {
            defaultServices = DefaultServices.getInstance();
            gsonBuilder = new GsonBuilder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            etudiants = defaultServices.getEtudiants();
            gson = gsonBuilder.create();
            String JsonEtudiants = gson.toJson(etudiants);

            resp.getWriter().append(JsonEtudiants);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }


}
