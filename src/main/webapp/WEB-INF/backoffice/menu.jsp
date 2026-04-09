<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>
    <div class="backoffice-logo">HellMetz</div>
    <div class="backoffice-subtitle">Backoffice organisateur</div>

    <nav class="backoffice-nav">
        <a href="${pageContext.request.contextPath}/backoffice/dashboard"
           class="<c:if test='${activeMenu eq "dashboard"}'>active</c:if>">
            <span>🏠</span>
            <span>Tableau de bord</span>
        </a>

        <a href="${pageContext.request.contextPath}/backoffice/groupes"
           class="<c:if test='${activeMenu eq "groupes"}'>active</c:if>">
            <span>🎸</span>
            <span>Groupes</span>
        </a>

        <a href="${pageContext.request.contextPath}/backoffice/scenes"
           class="<c:if test='${activeMenu eq "scenes"}'>active</c:if>">
            <span>🎬</span>
            <span>Scenes</span>
        </a>

        <a class="disabled">
            <span>🎵</span>
            <span>Concerts (à venir)</span>
        </a>

        <a class="disabled">
            <span>📦</span>
            <span>Logistique & Matériel (à venir)</span>
        </a>

        <a class="disabled">
            <span>🧑‍🤝‍🧑</span>
            <span>RH & Bénévoles (à venir)</span>
        </a>

        <a class="disabled">
            <span>📍</span>
            <span>Points d’intérêt (à venir)</span>
        </a>
    </nav>
</div>

 <%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
     	<div class="container-fluid">
     		<ul class="navbar-nav ms-auto">
     			<c:if test="${not empty utilisateurConnecte}">
     				<li class="nav-item d-flex align-items-center">
     					<span class="nav-link text-light me-3">
 						<i class="bi bi-person-circle"></i> Bonjour,
 						<strong>${utilisateurConnecte.prenom} ${utilisateurConnecte.nom}</strong>
 					</span>
     				</li>
     				<li class="nav-item">
     					<a class="btn btn-outline-danger btn-sm mt-1" href="${pageContext.request.contextPath}/logout">
     						<i class="bi bi-box-arrow-right"></i> Se déconnecter
     					</a>
     				</li>
     			</c:if>
     		</ul>

     		<ul class="navbar-nav me-auto">

     		  <%-- Visible par tous les utilisateurs connectés --%>
     		  <li class="nav-item">
     			<a class="nav-link"
                       			   href="${pageContext.request.contextPath}/backoffice/groupes">
     			  Gestion des Groupes
     			</a>
     		  </li>

     		  <%-- Visible uniquement par les ADMIN --%>
     		  <c:if test="${fn:contains(utilisateurConnecte.roles, 'ADMIN')}">
     			<li class="nav-item">
                    <a class="nav-link"
                         				 href="${pageContext.request.contextPath}/backoffice/utilisateurs">
     				Utilisateurs
     			  </a>
     			</li>
     		  </c:if>

     		  <%-- Visible par ADMIN et ORGANISATEUR --%>
     		  <c:if test="${fn:contains(utilisateurConnecte.roles, 'ADMIN')
 					 or fn:contains(utilisateurConnecte.roles, 'ORGANISATEUR')}">
     			<li class="nav-item">
     			  <a class="nav-link"
                         				 href="${pageContext.request.contextPath}/backoffice/festivals">
     				Festivals
     			  </a>
     			</li>
    		  </c:if>
    		</ul>
    	</div>
     </nav>



