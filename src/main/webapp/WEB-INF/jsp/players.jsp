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
                            <th><spring:message code="players.name.history"/></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>

        <div class="modal fade" tabindex="-1" id="editRow">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="modalTitle"></h4>
                        <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
                    </div>
                    <div class="modal-body">
                        <form id="detailsForm">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="name" class="col-form-label"><spring:message code="players.name"/></label>
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="<spring:message code="players.name"/>">
                            </div>

<%--                            <div class="form-group">--%>
<%--                                <label for="clan" class="col-form-label"><spring:message code="players.clan"/></label>--%>
<%--                                <input type="text" class="form-control" id="clan" name="clan"--%>
<%--                                       placeholder="<spring:message code="players.clan"/>">--%>
<%--                            </div>--%>

                            <div class="form-group">
                                <label for="army" class="col-form-label"><spring:message code="players.army"/></label>
                                <input type="number" class="form-control" id="army" name="army" placeholder="0">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                            <span class="fa fa-close"></span>
                            <spring:message code="common.cancel"/>
                        </button>
                        <button type="button" class="btn btn-primary" onclick="save()">
                            <span class="fa fa-check"></span>
                            <spring:message code="common.save"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>

    <jsp:include page="fragments/footer.jsp"/>
    </body>

    <jsp:include page="fragments/i18n.jsp">
        <jsp:param name="page" value="players"/>
    </jsp:include>
</html>
