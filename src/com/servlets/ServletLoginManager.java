package com.servlets;

import com.entities.EntityAccountsManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletLoginManager", urlPatterns = "/login")
public class ServletLoginManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean areWarningsTriggered = false;
        String missingUsername = "";
        String missingPassword = "";

        String submittedUsername = request.getParameter("user");
        String submittedPassword = request.getParameter("pass");


        if (submittedUsername.equalsIgnoreCase("")) {
            missingUsername = "Field is empty. Enter username!";
            areWarningsTriggered = true;
        }
        if (submittedPassword.equalsIgnoreCase("")) {
            missingPassword = "Field is empty. Enter password!";
            areWarningsTriggered = true;
        }

        if (areWarningsTriggered) {
            request.setAttribute("missingUsername", missingUsername);
            request.setAttribute("missingPassword", missingPassword);
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        } else {
            if (EntityAccountsManager.checkIfUsernameAlreadyExists(submittedUsername)) {

                if (EntityAccountsManager.checkIfPasswordMatches(submittedPassword)) {
                    // da se sloji koda za startirane na login sesiqta

                    request.setAttribute("isLogged", submittedUsername);
                    request.setAttribute("successfulLog", "Welcome " + submittedUsername + " !");
                    request.setAttribute("username", submittedUsername);
                    RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                    view.forward(request, response);
                } else {
                    request.setAttribute("unsuccessfulLog", "Wrong password!");
                    submittedUsername = "No";
                    request.setAttribute("isLogged", submittedUsername);
                    RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                    view.forward(request, response);
                }
            } else {
                request.setAttribute("unsuccessfulLog", "Username " + submittedUsername + " does not exist! Please register");// HREF da se napravi v teksta
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
