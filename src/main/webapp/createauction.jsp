<%@ page import="com.russell.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 8/8/2021
  Time: 12:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Auction</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("currentUser");

        if(user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
    %>

    <div class="container-fluid h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3">
                <form class="row text-center" action="RegisterServlet" method="post" style="width:340px">
                    <h1 class="text-center">Book Details</h1>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="ISBN" required="required" id="txt_isbn"
                               name="isbn">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Title" required="required" id="txt_title"
                               name="title">
                    </div>
                    <div class="form-group">
                        <select class="form-select" aria-label="Book Type" required="required" name="subcategory" id="sel_subcat">
                            <option value="FICTION">Fiction</option>
                            <option value="NONFICTION">NonFiction</option>
                            <option value="MAGAZINE">Magazine</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Genre" required="required" id="txt_genre"
                               name="genre">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="Pages" required="required" id="txt_pages"
                               name="pages">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Author" required="required" id="txt_author"
                               name="author">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Publisher" required="required"
                               id="txt_publisher" name="publisher">
                    </div>
                    <div class="form-group">
                        <label for="txt_publicationdate">Publication Date</label>
                        <input type="date" class="form-control" required="required"
                               id="txt_publicationdate" name="publicationDate">
                    </div>
                    <h1>Auction Details</h1>
                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="Start Price" required="required"
                               id="txt_startPrice" name="startPrice">
                    </div>
                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="Reserve Price" required="required"
                               id="txt_reservePrice" name="reservePrice">
                    </div>
                    <div class="form-group">
                        <label for="txt_closedate">Close Date</label>
                        <input type="datetime-local" class="form-control" required="required"
                               id="txt_closedate" name="closeDate">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">Create</button>
                    </div>
                    <label style="color: #FF0000;" id="lbl_invalid">${createResult}</label>
                </form>
            </div>
        </div>
    </div>

    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
