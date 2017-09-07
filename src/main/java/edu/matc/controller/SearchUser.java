package edu.matc.controller;

import edu.matc.persistence.UserData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserData userData = new UserData();


        if (req.getParameter("search") != null && req.getParameter("search").equals("search")) {
            String firstName = req.getParameter("first_name_search");

            if (firstName != null && !firstName.isEmpty()) {
                req.setAttribute("users", userData.searchByFirstName(firstName));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            String lastName = req.getParameter("last_name_search");

            if (lastName != null && !lastName.isEmpty()) {
                req.setAttribute("users", userData.searchByLastName(lastName));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            String userId = req.getParameter("user_id_search");

            if (userId != null && !userId.isEmpty()) {
                req.setAttribute("users", userData.searchByUserId(userId));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            String dateOfBirth = req.getParameter("dob_serch");

            if (dateOfBirth != null && !dateOfBirth.isEmpty()) {
                req.setAttribute("users", userData.searchByDateOfBirth(dateOfBirth));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
                dispatcher.forward(req, resp);
                return;
            }

            String age = req.getParameter("age_search");

            if (age != null && !age.isEmpty()) {
                req.setAttribute("users", userData.searchByAge(age));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
                dispatcher.forward(req, resp);
                return;
            }

        } else {
            req.setAttribute("users", userData.getAllUsers());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}