<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top py-0">
    <div class="container-fluid justify-content-end">
        <a class="navbar-brand">
            <img src="images/title_icon.ico" alt=""> <spring:message code="app.title"/>
        </a>
        <a class="btn btn-outline-primary mr-sm-2" href="${pageContext.request.contextPath}/clans">
            <spring:message code="navbar.clans"/>
        </a>
        <a class="btn btn-outline-primary mr-sm-2" href="${pageContext.request.contextPath}/players">
            <spring:message code="navbar.players"/>
        </a>
        <div class="collapse navbar-collapse justify-content-end" id="navbarColor01">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 align-items-center">
                <li class="nav-item">
                    <form class="form-inline my-2 my-lg-2 ml-auto">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" />
                        <button class="btn btn-outline-info my-1 mr-sm-2" type="submit"><spring:message code="navbar.search"/></button>
                    </form>
                </li>
                <li class="nav-item active">
                    <button class="btn btn-outline-success mr-sm-2" type="button"><spring:message code="navbar.login"/></button>
                </li>
                <li class="nav-item">
                    <button class="btn btn-outline-warning mr-sm-2" type="button"><spring:message code="navbar.registration"/></button>
                </li>
                <li class="nav-item">
                    <div class="btn-group" role="group" aria-label="Language group">
                        <button class="btn btn-outline-dark" type="button">
                            <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">
                                <img src="images/flag-EN.png" width="32" height="19" alt="EN">
                            </a>
                        </button>
                        <button class="btn btn-outline-dark" type="button">
                            <a href="${requestScope['javax.servlet.forward.request_uri']}?lang=ua">
                                <img src="images/flag-UA.png" width="32" height="19" alt="UA">
                            </a>
                        </button>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>
<script type="text/javascript">
    var localeCode = "${pageContext.response.locale}";
</script>
