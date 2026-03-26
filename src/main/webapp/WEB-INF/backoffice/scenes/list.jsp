<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Gestion</div>
            <div class="hm-card-title">Scènes du HellMetz Festival</div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/scenes/edit" class="hm-btn-primary">
            <span>＋</span>
            <span>Nouvelle scène</span>
        </a>
    </div>

    <c:choose>
        <c:when test="${empty scenes}">
            <p>Aucune scène enregistrée pour le moment.</p>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="hm-table">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Description</th>
                        <th>Capacité</th>
                        <th>Type</th>          <%-- ✅ remplace "Année Création" --%>
                        <th>Superficie (m²)</th> <%-- ✅ remplace "Pays" --%>
                        <th>Plan technique</th>  <%-- ✅ remplace "Site web" --%>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${scenes}"> <%-- ✅ var "s" au lieu de "g" --%>
                        <tr>
                            <td>
                                <strong>${s.nom}</strong>
                            </td>
                            <td>
                                    ${s.description}
                            </td>
                            <td>
                                <span class="hm-pill">${s.capacite}</span> <%-- ✅ --%>
                            </td>
                            <td>${s.type_scene}</td> <%-- ✅ --%>
                            <td>${s.superficie}</td> <%-- ✅ --%>
                            <td>
                                <c:if test="${not empty s.url_plan_technique}"> <%-- ✅ --%>
                                    <a class="hm-link-site"
                                       href="${s.url_plan_technique}"
                                       target="_blank" rel="noopener noreferrer">
                                        ouvrir
                                    </a>
                                </c:if>
                            </td>
                            <td>
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/scenes/edit?id=${s.id}">
                                    ✏️ éditer
                                </a>
                                &nbsp;|&nbsp;
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/scenes/delete?id=${s.id}"
                                   onclick="return confirm('Supprimer cette scène ?');">
                                    🗑️ supprimer
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>