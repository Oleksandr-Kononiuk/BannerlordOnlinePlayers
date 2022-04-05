<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <jsp:include page="fragments/headTag.jsp"/>
    <body>
        <jsp:include page="fragments/bodyHeader.jsp"/>

        <div class="jumbotron pt-5">
            <div class="container">
                <br/>
                <table class="table table-hover" id="datatable">
                    <thead class="thead-dark">
                    <tr>
                        <th><spring:message code="clans.name"/></th>
                        <th><spring:message code="clans.army"/></th>
                        <th><spring:message code="clans.members"/></th>
                        <th><spring:message code="clans.at.war"/></th>
                        <th><spring:message code="clans.at.alliance"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>

        <jsp:include page="fragments/footer.jsp"/>
    </body>

    <jsp:include page="fragments/i18n.jsp">
        <jsp:param name="page" value="clans"/>
    </jsp:include>
</html>
