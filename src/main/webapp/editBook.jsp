<%-- 
    Document   : editBook
    Created on : Nov 30, 2017, 2:10:24 PM
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

        <title>Edit Book</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-8">
                    <h1>Edit Book</h1>

                    <form method ="POST" 
                          action = "${pageContext.request.contextPath}/BookController?action=editbook">

                        <input type="hidden" name="bookId" value ="${eBook.bookId}">

                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" value="${eBook.title}"
                                   id="title" name="title">
                        </div>
                        <div class="form-group">
                            <label for="isbn">ISBN:</label>
                            <input type="text" class="form-control" value="${eBook.isbn}"
                                   id="isbn" name="isbn">
                        </div>
                        <div class="form-group">
                            <label for="author">Author:</label>
                            <!--the issue is here-->
                            <select class="form-control" id="authorId" name="author">
                                <c:forEach var="a" items="${editAuthorList}">
                                    <option value="${a.authorId}">                                        
                                        ${a.authorName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">Save</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </body>
</html>

