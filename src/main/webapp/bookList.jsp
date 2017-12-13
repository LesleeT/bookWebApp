<%-- 
    Document   : bookList
    Created on : Nov 16, 2017, 8:21:21 PM
    Author     : Leslee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Book List</title>
    </head>
    <body>
        <h1>Book List</h1>
        <div>
            <table border ="1">
                <c:forEach var="b" items="${bookList}">
                    <tr>
                        <td>${b.title}</td>
                        <td>${b.bookId}</td>
                        <td>${b.isbn}</td>
                        <td>${b.author.authorId}</td>
                        <td><input type="button" class="btn btn-info" value="Edit" onclick="location.href = 'BookController?action=edit&bookId=${b.bookId}'"</td>
                        <td><input type="button" class="btn btn-danger" value="Delete" onclick="location.href = 'BookController?action=delete&bookId=${b.bookId}'"</td>
                    </tr> 
                </c:forEach>
            </table> 
        </div>

        <input type="button" class="btn btn-primary" value="Add" onclick="location.href = 'BookController?action=add'">
        <div>${errMessage}</div>

        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>
