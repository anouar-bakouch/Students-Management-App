package com.gl.todo_ameliored_version.presentation.controllers;

import com.gl.todo_ameliored_version.beans.Option;
import com.gl.todo_ameliored_version.business.DefaultServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOptions")
@MultipartConfig
public class ListOptionServlet extends HttpServlet {

    private DefaultServices defaultServices;
    private List<Option> options;
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

     try{

         options = defaultServices.getOptions();
         gson = gsonBuilder.create();

         String JsonOptions = gson.toJson(options);
         resp.getWriter().append(JsonOptions);

     }catch (Exception e){
         e.printStackTrace();
         System.out.println(e.getMessage());
     }

    }
}













