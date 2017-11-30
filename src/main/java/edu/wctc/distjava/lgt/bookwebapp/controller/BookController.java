/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.lgt.bookwebapp.controller;

import edu.wctc.distjava.lgt.bookwebapp.model.Author;
import edu.wctc.distjava.lgt.bookwebapp.model.AuthorService;
import edu.wctc.distjava.lgt.bookwebapp.model.Book;
import edu.wctc.distjava.lgt.bookwebapp.model.BookFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
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
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    @EJB
    private AuthorService authorService;
    @EJB
    private BookFacade bookFacade;

    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";
    public static final String AUTHOR_ID = "authorId";
    public static final String ADD_ACTION = "add";
    public static final String DELETE_ACTION = "delete";
    public static final String EDIT_ACTION = "edit";
    public static final String SUBMIT_BOOK_ACTION = "submitbook";
    public static final String EDIT_BOOK_ACTION = "editbook";
    public static final String TITLE = "title";
    public static final String DATE_ADDED = "date_added";
    public static final String AUTHORID = "author_id";
    public static final String BOOKID = "book_id";
    public static final String ISBN = "isbn";
    public static final String AUTHOR = "author";

    public static final String DESTINATION_HOME = "/index.jsp";
    public static final String DESTINATION_BOOKLIST = "/bookList.jsp";
    public static final String DESTINATION_ADD_BOOK = "/addBook.jsp";
    public static final String DESTINATION_EDIT_BOOK = "/editBook.jsp";
    public static final String DESTINATION_ERROR = "/error.jsp";

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
        String destination = "/bookList.jsp";
        try {

            List<Book> bookList = null;
            String action = request.getParameter(ACTION);//key in the jsp page

            if (action.equalsIgnoreCase(LIST_ACTION)) {
                bookList = bookFacade.findAll();
                request.setAttribute("bookList", bookList);
                //getBookList(bookList, bookFacade, request);

            } else if (action.equalsIgnoreCase(DELETE_ACTION)) {
                String bookId = request.getParameter(BOOKID);
                bookFacade.removeBookById(bookId);
                getBookList(bookList, bookFacade, request);

            } else if (action.equalsIgnoreCase(ADD_ACTION)) {

                List<Author> authorList = authorService.findAll();
                request.setAttribute("authorList", authorList);
                destination = DESTINATION_ADD_BOOK;

            } else if (action.equalsIgnoreCase(SUBMIT_BOOK_ACTION)) {
                String title = request.getParameter(TITLE);
                String isbn = request.getParameter(ISBN);
                String authorId = request.getParameter(AUTHOR);
                bookFacade.addBook(title, isbn, authorId);

                destination = DESTINATION_BOOKLIST;

                getBookList(bookList, bookFacade, request);

            } else if (action.equalsIgnoreCase(EDIT_ACTION)) {

                destination = DESTINATION_EDIT_BOOK;

                String bookId = request.getParameter(BOOKID);
                Book eBook = bookFacade.findById(new Integer(bookId));
                request.setAttribute("eBook", eBook);

                List<Author> authorList = authorService.findAll();
                request.setAttribute("editAuthorList", authorList);

            } else if (action.equalsIgnoreCase(EDIT_BOOK_ACTION)) {

                destination = DESTINATION_BOOKLIST;
                String id = request.getParameter(BOOKID);
                String title = request.getParameter(TITLE);
                String isbn = request.getParameter(ISBN);
                String authorId = request.getParameter(AUTHOR); //only the author Id is passed in

                bookFacade.updateBook(id, title, isbn, authorId);//pass in the values from the form

                getBookList(bookList, bookFacade, request);
            }

        } catch (Exception e) {
            //System.out.println("CAUGHT");
            //destination = "/authorList.jsp";
            request.setAttribute("errMessage", e.getMessage());
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    public void getBookList(List<Book> bookList, BookFacade bf, HttpServletRequest request)
            throws SQLException, ClassNotFoundException, Exception {
        bookList = bf.findAll();
        request.setAttribute("bookList", bookList);

    }

    @Override
    public void init() throws ServletException {

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
