<%-- 
    Document   : courses
    Created on : Jun 1, 2017, 4:35:54 PM
    Author     : 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/schooladmincss.css">
        <title>COURSE</title>


        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/groupproject"
                           user="root"  password="1234"
                           scope="session"/>

    </head>
    <body>
        <sql:query dataSource="${snapshot}" var="result">
            SELECT * from course;
        </sql:query>


        <h2> Courses</h2>
        <div id="left">
            <table>
                <tr id='firstRow'>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
                        <td><c:out value="${row.ID}"/></td>
                        <td><c:out value="${row.Name}"/></td>
                        <td><c:out value="${row.Description}"/></td>
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

                Enter Name
                <input type="text" name="name">
                Enter Description
                <input type="text" name="des">
                Enter ID
                <input type="text" name="ID">
                <input type="submit" value="submit">
            </form>

        </div>
        <c:if test="${pageContext.request.method=='POST'}">
            <c:if test="${param.ID!=null}">
                <c:catch var="exception">
                    <sql:update dataSource="${snapshot}" var="updatedTable">
                        INSERT INTO course(Name, Description, ID) VALUES (?,?,?)
                        <sql:param value="${param.name}" />
                        <sql:param value="${param.des}" />
                        <sql:param value="${param.ID}" />
                    </sql:update>
                </c:catch>
            </c:if>
            <c:if test="${param.DidS!=null}">
                <sql:update dataSource="${snapshot}" var="count">
                    DELETE FROM studentgrades 
                    WHERE studentgrades.CourseID = ?
                    <sql:param value="${param.DidS}" />
                </sql:update>
                <sql:update dataSource="${snapshot}" var="count">
                    DELETE FROM coursemodule 
                    WHERE coursemodule.CourseID = ?
                    <sql:param value="${param.DidS}" />
                </sql:update>
                <sql:update dataSource="${snapshot}" var="count">
                    DELETE FROM course 
                    WHERE course.ID = ?
                    <sql:param value="${param.DidS}" />
                </sql:update>
            </c:if>
            <c:if test="${param.idS!=null}">
                <sql:query dataSource="${snapshot}" var="res1">
                    SELECT course.Name, course.Description
                    FROM course
                    WHERE course.ID = ?
                    <sql:param value="${param.idS}" />
                </sql:query>
                <sql:query dataSource="${snapshot}" var="res2">
                    SELECT v.* from(
                    SELECT teacher.LastName AS "tLastName", student.LastName AS "sLastName", coursemodule.Schedule
                    FROM coursemodule
                    INNER JOIN teacher
                    ON coursemodule.TeacherID=teacher.ID
                    INNER JOIN student
                    ON coursemodule.StudentID=student.ID
                    WHERE coursemodule.CourseID = ?
                    ORDER by teacher.LastName
                    <sql:param value="${param.idS}" />)
                    as v
                </sql:query>
                <div>
                    <br><br><br><br>
                        <table>
                            <tr>

                            </tr>
                            <tr id='firstRow'>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Teacher</th>
                                <th>Student</th>
                                <th>Schedule</th>
                            </tr>
                            <c:forEach var="row1" items="${res1.rows}">
                                <td><c:out value="${row1.Name}"/></td>
                                <td><c:out value="${row1.Description}"/></td>

                            </c:forEach>
                            <c:forEach var="row2" items="${res2.rows}">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td><c:out value="${row2.tLastName}"/></td>
                                    <td><c:out value="${row2.sLastName}"/></td>
                                    <td><c:out value="${row2.Schedule}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>    
            </c:if>
        </c:if>
    </body>
</html>