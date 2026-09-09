<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">

    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Gestion</div>
            <div class="hm-card-title">Artistes du HellMetz Festival</div>
        </div>

        <a href="${pageContext.request.contextPath}/backoffice/artiste/edit"
           class="hm-btn-primary">
            <span>＋</span>
            <span>Nouvel artiste</span>
        </a>
    </div>

    <c:choose>

        <c:when test="${empty artistes}">
            <p>Aucun artiste enregistré pour le moment.</p>
        </c:when>

        <c:otherwise>

            <div class="table-responsive">

                <table class="hm-table">

                    <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Nom de scène</th>
                        <th>Nationalité</th>
                        <th>Cachet</th>
                        <th>Groupe</th>
                        <th>Actions</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var="a" items="${artistes}">

                        <tr>

                            <td>
                                <strong>${a.nom}</strong>
                            </td>

                            <td>
                                ${a.prenom}
                            </td>

                            <td>
                                <strong>${a.nom_scene}</strong>
                            </td>

                            <td>
                                ${a.nationalite}
                            </td>

                            <td>
                                <span class="hm-pill">${a.cachet}</span>
                            </td>

                            <td>
                                ${a.id_groupe}
                            </td>

                            <td>
                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/artiste/edit?id=${a.id_artiste}">
                                    ✏️ éditer
                                </a>

                                &nbsp;|&nbsp;

                                <a class="hm-link-site"
                                   href="${pageContext.request.contextPath}/backoffice/artiste/delete?id=${a.id_artiste}"
                                   onclick="return confirm('Supprimer cet artiste ?');">
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