<%-- 
    Document   : teachers
    Created on : Jun 1, 2017, 4:35:10 PM
    Author     : 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/schooladmincss.css">
        <title>Teachers</title>

        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/groupproject"
                           user="root"  password="1234"
                           scope="session"/>

    </head>
    <body>
        <sql:query dataSource="${snapshot}" var="result">
            SELECT * from teacher;
        </sql:query>


        <h2> Teachers</h2>
        <div class="content">

            

            <div id="left">
                <table>
                    <tr id='firstRow'>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th></th>
                        <th></th>
                    </tr>
                    <c:forEach var="row" items="${result.rows}">
                        <tr>
                            <td><c:out value="${row.ID}"/></td>
                            <td><c:out value="${row.FirstName}"/></td>
                            <td><c:out value="${row.LastName}"/></td>
                            <td><c:out value="${row.Phone}"/></td>
                            <td><c:out value="${row.Email}"/></td>
                            <td><form  method="post">
                                    <button name="idS"
                                            type="submit"
                                            value="${row.ID}">Details
                                    </button>
                                </form></td>
                            <td><form  method="post">
                                    <button name="DidS"
                                            type="submit"
                                            value="${row.ID}" onClick="window.location.reload()">Delete
                                    </button>
                                </form></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div id="right">
                <form method="post">
                    Enter First Name
                    <input type="text" name="fname">
                    Enter Last Name
                    <input type="text" name="lname">
                    Enter ID
                    <input type="text" name="ID">
                    Enter Phone
                    <input type="text" name="phone">
                    Enter Email
                    <input type="text" name="email">
                    <input type="submit" value="submit">
                </form>
            </div>

            <c:if test="${pageContext.request.method=='POST'}">
                <c:if test="${param.ID!=null}">
                    <c:catch var="exception">
                        <sql:update dataSource="${snapshot}" var="updatedTable">
                            INSERT INTO teacher(FirstName, LastName, ID, Phone, Email) VALUES (?,?, ?, ?, ?)
                            <sql:param value="${param.fname}" />
                            <sql:param value="${param.lname}" />
                            <sql:param value="${param.ID}" />
                            <sql:param value="${param.phone}" />
                            <sql:param value="${param.email}" />
                        </sql:update>
                    </c:catch>
                </c:if>
                <c:if test="${param.DidS!=null}">
                    <sql:update dataSource="${snapshot}" var="count">
                        DELETE FROM coursemodule 
                        WHERE coursemodule.TeacherID = ?
                        <sql:param value="${param.DidS}" />
                    </sql:update>

                    <sql:update dataSource="${snapshot}" var="count">
                        DELETE FROM teacher 
                        WHERE teacher.ID = ?
                        <sql:param value="${param.DidS}" />
                    </sql:update>
                </c:if>
                <c:if test="${param.idS!=null}">
                    <sql:query dataSource="${snapshot}" var="res1">
                        SELECT teacher.FirstName, teacher.LastName,teacher.Email, teacher.Phone
                        FROM teacher
                        WHERE teacher.ID = ?
                        <sql:param value="${param.idS}" />
                    </sql:query>
                    <sql:query dataSource="${snapshot}" var="res2">
                        SELECT coursemodule.CourseID, coursemodule.Schedule, course.Name
                        FROM coursemodule
                        INNER JOIN course
                        ON coursemodule.CourseID=course.ID
                        WHERE coursemodule.TeacherID = ?
                        <sql:param value="${param.idS}" />
                    </sql:query>
                    <div>
                        <br><br>
                        <table>
                            <tr>

                            </tr>
                            <tr id='firstRow'>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Course ID</th>
                                <th>Course Name</th>
                                <th>Schedule</th>
                            </tr>
                            <c:forEach var="row1" items="${res1.rows}">
                                <td><c:out value="${row1.FirstName}"/></td>
                                <td><c:out value="${row1.LastName}"/></td>
                                <td><c:out value="${row1.Email}"/></td>
                                <td><c:out value="${row1.Phone}"/></td>
                            </c:forEach>
                            <c:forEach var="row2" items="${res2.rows}">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><c:out value="${row2.CourseID}"/></td>
                                    <td><c:out value="${row2.Name}"/></td>
                                    <td><c:out value="${row2.Schedule}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>    
                </c:if>
            </c:if>
                        
        </div>
        <div>
    </body>
</html>