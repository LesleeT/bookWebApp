<%-- 
    Document   : editAuthor
    Created on : Oct 16, 2017, 8:51:56 PM
    Author     : Leslee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
        <title>Edit Author</title>
    </head>
    <body> <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <h1>Edit Author</h1>

                    <form method ="POST" action = "${pageContext.request.contextPath}/authorController?action=editAuthor">

                        <input type="hidden" name="authorid" value="${eAuthor.authorId}">
                               Author name:<br>
                        <input type="text" name="author_name" value="${eAuthor.authorName}"><br>
                        Date added:
                        <input type="text" name="date_added" value="${eAuthor.dateAdded}"><br>
                        <br>
                        <input type="submit" class="btn btn-success" name="submit" value="Submit">
                    </form>
                </div>

                </body>
                </html>
