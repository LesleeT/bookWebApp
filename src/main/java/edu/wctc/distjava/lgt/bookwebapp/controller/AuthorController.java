/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.controller;

import edu.wctc.distjava.lgt.bookwebapp.model.Author;
import edu.wctc.distjava.lgt.bookwebapp.model.AuthorDao;
import edu.wctc.distjava.lgt.bookwebapp.model.AuthorService;
import edu.wctc.distjava.lgt.bookwebapp.model.IAuthorDao;
import edu.wctc.distjava.lgt.bookwebapp.model.MySqlDataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leslee
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";
    public static final String AUTHOR_ID = "authorId";
    public static final String ADD_ACTION = "add";
    public static final String DELETE_ACTION = "delete";
    public static final String EDIT_ACTION = "edit";
    public static final String SUBMIT_AUTHOR_ACTION = "submitauthor";
    public static final String EDIT_AUTHOR_ACTION = "editAuthor";
    public static final String AUTHOR_NAME = "author_name";
    public static final String DATE_ADDED = "date_added";

    public static final String DESTINATION_AUTHOR_LIST = "/authorList.jsp";
    public static final String DESTINATION_ADD_AUTHOR = "/addAuthor.jsp";
    public static final String DESTINATION_EDIT_AUTHOR = "/editAuthor.jsp";
 

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String destination = "/authorList.jsp";//default
        try {

            IAuthorDao dao = new AuthorDao(
                    "com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/book",
                    "root", "admin",
                    new MySqlDataAccess()
            );

            AuthorService authorService = new AuthorService(dao);

            List<Author> authorList = null;
            String action = request.getParameter(ACTION);//key in the jsp page

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
                getAuthorList(authorList, authorService, request);

            } else if (action.equalsIgnoreCase(DELETE_ACTION)) {
                String authorId = request.getParameter(AUTHOR_ID);
                authorService.removeAuthorById(authorId);
                

            } else if (action.equalsIgnoreCase(ADD_ACTION)) {
                destination = DESTINATION_ADD_AUTHOR; 

            } else if (action.equalsIgnoreCase(SUBMIT_AUTHOR_ACTION)) {
                List<String> colNames = new ArrayList();
                colNames.add(AUTHOR_NAME);
                colNames.add(DATE_ADDED);

                List<Object> colValues = new ArrayList();
                colValues.add(request.getParameter(AUTHOR_NAME));
                colValues.add(new Date());

                authorService.addAuthor(colNames, colValues);

                destination = DESTINATION_AUTHOR_LIST;

                getAuthorList(authorList, authorService, request);
                
            } else if (action.equalsIgnoreCase(EDIT_ACTION)) {

                String authorId = request.getParameter(AUTHOR_ID);

                destination = DESTINATION_EDIT_AUTHOR;

                Author eAuthor = authorService.findAuthorById(authorId);
                request.setAttribute("eAuthor", eAuthor);

            } else if (action.equalsIgnoreCase(EDIT_AUTHOR_ACTION)) {

                List<String> colNames = new Vector();
                colNames.add(AUTHOR_NAME);
                colNames.add(DATE_ADDED);

                List<Object> colValues = new Vector();
                colValues.add(request.getParameter(AUTHOR_NAME));
                colValues.add(new Date());

                String authorId = request.getParameter("author_id");

                authorService.updateAuthor(colNames, colValues, authorId);

                destination = DESTINATION_AUTHOR_LIST;

                getAuthorList(authorList, authorService, request);
                 //authorList = authorService.getAuthorList();
                 //request.setAttribute("authorList", authorList);
            }

        } catch (Exception e) {
            //System.out.println("CAUGHT");
            //destination = "/authorList.jsp";
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    public void getAuthorList(List<Author> authorList, AuthorService authServ, HttpServletRequest request)
            throws SQLException, ClassNotFoundException {
        authorList = authServ.getAuthorList();
        request.setAttribute("authorList", authorList);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
