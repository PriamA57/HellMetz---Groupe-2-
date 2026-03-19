<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">
    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Édition</div>
            <div class="hm-card-title">
                <c:choose>
                    <c:when test="${not empty scene.id}">Modifier la scène : ${scene.nom}</c:when>
                    <c:otherwise>Nouvelle scène</c:otherwise>
                </c:choose>
            </div>
        </div>
        <a href="${pageContext.request.contextPath}/backoffice/scenes" class="hm-btn-primary" style="background-color: #6c757d; color: white;">
            <span>←</span>
            <span>Retour à la liste</span>
        </a>
    </div>

    <div class="p-4">
        <form action="${pageContext.request.contextPath}/backoffice/scenes/edit" method="post">

            <input type="hidden" name="id" value="${scene.id}">

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="nom" class="form-label fw-bold">Nom de la scène <span class="text-danger">*</span></label>
                    <input type="text" id="nom" name="nom" class="form-control" value="${scene.nom}" required>
                </div>
                <div class="col-md-6">
                    <label for="capacite" class="form-label fw-bold">Capacité</label>
                    <input type="number" id="capacite" name="capacite" class="form-control" value="${scene.capacite}">
                </div>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label fw-bold">Description</label>
                <textarea id="description" name="description" class="form-control" rows="4">${scene.description}</textarea>
            </div>

            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="type_scene" class="form-label fw-bold">Type de scène</label>
                    <input type="text" id="type_scene" name="type_scene" class="form-control" value="${scene.type_scene}">
                </div>
                <div class="col-md-6">
                    <label for="superficie" class="form-label fw-bold">Superficie (m²)</label>
                    <input type="number" id="superficie" name="superficie" class="form-control" value="${scene.superficie}">
                </div>
            </div>

            <div class="mb-3">
                <label for="url_plan_technique" class="form-label fw-bold">URL Plan technique</label>
                <input type="url" id="url_plan_technique" name="url_plan_technique" class="form-control" value="${scene.url_plan_technique}">
            </div>

            <div class="form-check form-switch mb-4 mt-3">
                <input class="form-check-input" type="checkbox" id="actif" name="actif" value="true" ${scene.actif ? 'checked' : ''}>
                <label class="form-check-label fw-bold text-success" for="actif">Scène active (utilisée au festival)</label>
            </div>

            <div class="text-end mt-4 pt-3 border-top">
                <button type="submit" class="hm-btn-primary" style="border: none;">
                    <span>💾</span>
                    <span>Enregistrer les modifications</span>
                </button>
            </div>
        </form>
    </div>
</div>