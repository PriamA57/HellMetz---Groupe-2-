<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Édition</div>
            <div class="hm-card-title">
                <c:choose>
                    <c:when test="${not empty concert.id}">
                        Modifier le concert #${concert.id}
                    </c:when>
                    <c:otherwise>
                        Nouveau Concert
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/backoffice/concert" class="hm-btn-primary" style="background-color: #6c757d; color: white;">
            <span>←</span>
            <span>Retour à la liste</span>
        </a>
    </div>

    <div class="p-4">
        <form action="${pageContext.request.contextPath}/backoffice/concert/edit" method="post">
            <input type="hidden" name="id" value="${concert.id}">

            <div class="row mb-3">
                <div class="col-md-6">
                    <label>Date et heure début</label>
                    <input type="datetime-local" name="dateDebut" value="${concert.dateDebut}" required>
                </div>
                <div class="col-md-6">
                    <label>Date et heure fin</label>
                    <input type="datetime-local" name="dateFin" value="${concert.dateFin}" required>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-4">
                    <label>Statut</label>
                    <select name="statut">
                        <option value="PROGRAMME" ${concert.statut == 'PROGRAMME' ? 'selected' : ''}>Programmé</option>
                        <option value="ANNULE" ${concert.statut == 'ANNULE' ? 'selected' : ''}>Annulé</option>
                        <option value="REPORTE" ${concert.statut == 'REPORTE' ? 'selected' : ''}>Reporté</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label>Scène</label>
                    <input type="number" name="scene" value="${concert.scene}" required>
                </div>
                <div class="col-md-4">
                    <label>Édition</label>
                    <input type="number" name="edition" value="${concert.edition}" required>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label>Heure balance début</label>
                    <input type="datetime-local" name="balanceDebut" value="${concert.balanceDebut}">
                </div>
                <div class="col-md-6">
                    <label>Heure balance fin</label>
                    <input type="datetime-local" name="balanceFin" value="${concert.balanceFin}">
                </div>
            </div>

            <div class="mb-3">
                <label>Décibels max</label>
                <input type="number" name="decibels" value="${concert.decibels}">
            </div>

            <div class="text-end mt-4 pt-3 border-top">
                <button type="submit" class="hm-btn-primary" style="border: none;">
                    💾 Enregistrer
                </button>
            </div>
        </form>
    </div>
</div>