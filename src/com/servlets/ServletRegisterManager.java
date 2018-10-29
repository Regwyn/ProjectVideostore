package com.servlets;

import com.entities.EntityAccountsManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletRegisterManager", urlPatterns = "/register")
public class ServletRegisterManager extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean areWarningsTriggered = false;
        String missingEmail = "";
        boolean isEmailValid = false;
        String missingUsername = "";
        String missingPassword = "";
        String missingRetypePassword = "";
        String[] validEmails = {"@abv.bg", "@gmail.com", "@yahoo.com", "@yahoo.co.uk"};

        String submittedEmail = request.getParameter("email");
        String submittedUsername = request.getParameter("user");
        String submittedPassword = request.getParameter("pass");
        String submittedRetypedPassword = request.getParameter("retypePass");

        if (submittedEmail.equalsIgnoreCase("")) {
            missingEmail = "Field is empty. Enter email!";
            areWarningsTriggered = true;
        } else {
            for (int i = 0; i < validEmails.length; i++) {
                if (submittedEmail.endsWith(validEmails[i])) {
                    isEmailValid = true;
                }
            }
            if (!isEmailValid) {
                missingEmail = "Enter valid e-mail";
                areWarningsTriggered = true;
            }
        }
        if (submittedUsername.equalsIgnoreCase("")) {
            missingUsername = "Field is empty. Enter username!";
            areWarningsTriggered = true;
        }
        if (submittedPassword.equalsIgnoreCase("")) {
            missingPassword = "Field is empty. Enter password!";
            areWarningsTriggered = true;
        }
        if (submittedRetypedPassword.equalsIgnoreCase("")) {
            missingRetypePassword = "Field is empty. Enter password!";
            areWarningsTriggered = true;
        }
        if (!submittedPassword.equalsIgnoreCase("") && !submittedRetypedPassword.equalsIgnoreCase("")) {
            if (!submittedPassword.equalsIgnoreCase(submittedRetypedPassword)) {
                missingPassword = "Different passwords. Both should match!";
                missingRetypePassword = "Different passwords. Both should match!";
                areWarningsTriggered = true;
            }
        }

        if (areWarningsTriggered) {
            request.setAttribute("missingEmail", missingEmail);
            request.setAttribute("missingUsername", missingUsername);
            request.setAttribute("missingPassword", missingPassword);
            request.setAttribute("missingRetypePassword", missingRetypePassword);
            RequestDispatcher view = request.getRequestDispatcher("register.jsp");
            view.forward(request, response);
        } else {
            if (EntityAccountsManager.checkIfUserAlreadyExists(submittedUsername, submittedEmail)) {
                request.setAttribute("unSuccessfulReg", "The username " + submittedUsername + " already exists!");
                RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                view.forward(request, response);
            } else {
                EntityAccountsManager.addNewUser(submittedEmail, submittedUsername, submittedPassword);
                request.setAttribute("successfulReg", submittedUsername + ", You have registered successfully!");
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
