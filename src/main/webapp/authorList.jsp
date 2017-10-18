<%-- 
    Document   : authorList
    Created on : Sep 19, 2017, 8:35:53 PM
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
        <title>Author List</title>
    </head>
    <body>
        <h1>Author List</h1>
        <div>
            <table border ="1">
                <c:forEach var="a" items="${authorList}">
                    <tr>

                        <td>${a.authorName}</td>
                        <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}"/></td>
                        <td>${a.authorId}</td>
                        <td><input type="button" class="btn btn-info" value="Edit" onclick="location.href = 'authorController?action=edit&authorId=${a.authorId}'"</td>
                        <td><input type="button" class="btn btn-danger" value="Delete" onclick="location.href = 'authorController?action=delete&authorId=${a.authorId}'"</td>
                    </tr> 
                </c:forEach>
            </table> 
        </div>

        <input type="button" class="btn btn-primary" value="Add" onclick="location.href = 'authorController?action=add'">


        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>
