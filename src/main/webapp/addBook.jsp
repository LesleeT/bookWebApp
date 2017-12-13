<%-- 
    Document   : addBook
    Created on : Nov 30, 2017, 2:10:36 PM
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
        <title>Add Book</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <h1>Add New Book</h1>
                    <form method = "POST"
                          action = "${pageContext.request.contextPath}/BookController?action=submitbook">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" name="title">
                        </div>
                        <div class="form-group">
                            <label for="isbn">ISBN:</label>
                            <input type="text" class="form-control" id="isbn" name="isbn">
                        </div>
                        <div class="form-group">
                            <label for="author">Author:</label>
                            <select class="form-control" id="author" name="author">
                                <c:forEach var="a" items="${authorList}">
                                    <option value="${a.authorId}">${a.authorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">Add</button>
                    </form>
                </div>
            </div>
        </div>
   
    </body>
</html>
