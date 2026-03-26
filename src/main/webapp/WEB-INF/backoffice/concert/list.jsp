<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Gestion</div>
            <div class="hm-card-title">Concerts du HellMetz Festival</div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/concert/edit" class="hm-btn-primary">
            <span>＋</span>
            <span>Nouveau concert</span>
        </a>
    </div>

    <c:choose>
        <c:when test="${empty concert}">
            <p>Aucun concert enregistré pour le moment.</p>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="hm-table">
                    <thead>
                    <tr>
                        <th>Date et heure début</th>
                        <th>Date et heure fin</th>
                        <th>Statut</th>
                        <th>Scène</th>
                        <th>Heure balance début</th>
                        <th>Heure balance fin</th>
                        <th>Décibels max</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${concert}">
                        <tr>
                            <td>${c.dateDebut}</td>
                            <td>${c.dateFin}</td>
                            <td>${c.statut}</td>
                            <td>${c.scene.nom}</td>
                            <td>${c.balanceDebut}</td>
                            <td>${c.balanceFin}</td>
                            <td>${c.decibels}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/backoffice/concert/edit?id=${c.id}">✏️</a>
                                <a href="${pageContext.request.contextPath}/backoffice/concert/delete?id=${c.id}" onclick="return confirm('Supprimer ce concert ?');">🗑️</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>