<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <jsp:include page="fragments/headTag.jsp"/>
    <body>
        <script src="js/common.js" defer></script>
        <script src="js/players.js" defer></script>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="jumbotron pt-5">
            <div class="container">
                <br/>
                <table class="table table-hover" id="datatable">
                    <thead class="thead-dark">
                        <tr>
                            <th><spring:message code="players.name"/></th>
                            <th><spring:message code="players.army"/></th>
                            <th><spring:message code="players.clan"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Clark</td>
                        <td>Kent</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>John</td>
                        <td>Carter</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    <jsp:include page="fragments/footer.jsp"/>
    </body>
</html>
