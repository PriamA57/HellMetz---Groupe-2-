<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="hm-card">

    <div class="hm-card-header">
        <div>
            <div class="hm-tag">Gestion</div>

            <div class="hm-card-title">
                <c:choose>
                    <c:when test="${empty artiste}">
                        Nouvel artiste
                    </c:when>
                    <c:otherwise>
                        Modifier l'artiste
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>


    <form method="post"
          action="${pageContext.request.contextPath}/backoffice/artiste/edit">

        <c:if test="${not empty artiste}">
            <input type="hidden"
                   name="id_artiste"
                   value="${artiste.id_artiste}">
        </c:if>


        <div class="row">

            <!-- NOM -->
            <div class="col-md-6 mb-3">
                <label for="nom" class="form-label">
                    Nom
                </label>

                <input type="text"
                       class="form-control"
                       id="nom"
                       name="nom"
                       value="${artiste.nom}"
                       required>
            </div>


            <!-- PRENOM -->
            <div class="col-md-6 mb-3">
                <label for="prenom" class="form-label">
                    Prénom
                </label>

                <input type="text"
                       class="form-control"
                       id="prenom"
                       name="prenom"
                       value="${artiste.prenom}">
            </div>


            <!-- NOM DE SCENE -->
            <div class="col-md-6 mb-3">
                <label for="nom_scene" class="form-label">
                    Nom de scène
                </label>

                <input type="text"
                       class="form-control"
                       id="nom_scene"
                       name="nom_scene"
                       value="${artiste.nom_scene}">
            </div>


            <!-- NATIONALITE -->
            <div class="col-md-6 mb-3">
                <label for="nationalite" class="form-label">
                    Nationalité
                </label>

                <input type="text"
                       class="form-control"
                       id="nationalite"
                       name="nationalite"
                       value="${artiste.nationalite}">
            </div>


            <!-- STYLE -->
            <div class="col-md-6 mb-3">
                <label for="id_style" class="form-label">
                    ID du style
                </label>

                <input type="number"
                       class="form-control"
                       id="id_style"
                       name="id_style"
                       value="${artiste.id_style}">
            </div>


            <!-- CACHET -->
            <div class="col-md-6 mb-3">
                <label for="cachet" class="form-label">
                    Cachet
                </label>

                <input type="text"
                       class="form-control"
                       id="cachet"
                       name="cachet"
                       value="${artiste.cachet}">
            </div>


            <!-- GROUPE -->
            <div class="col-md-6 mb-3">

                <label for="id_groupe" class="form-label">
                    Groupe
                </label>

                <select class="form-control"
                        id="id_groupe"
                        name="id_groupe">

                    <option value="">
                        -- Aucun groupe --
                    </option>

                    <c:forEach var="g" items="${groupes}">

                        <option value="${g.id}"
                            <c:if test="${artiste.id_groupe == g.id}">
                                selected
                            </c:if>>
                            ${g.nom}
                        </option>

                    </c:forEach>

                </select>

            </div>


            <!-- URL PHOTO -->
            <div class="col-md-6 mb-3">
                <label for="url_photo" class="form-label">
                    URL de la photo
                </label>

                <input type="text"
                       class="form-control"
                       id="url_photo"
                       name="url_photo"
                       value="${artiste.url_photo}">
            </div>


            <!-- BIOGRAPHIE -->
            <div class="col-12 mb-3">

                <label for="biographie" class="form-label">
                    Biographie
                </label>

                <textarea class="form-control"
                          id="biographie"
                          name="biographie"
                          rows="5">${artiste.biographie}</textarea>

            </div>


            <!-- FACEBOOK -->
            <div class="col-md-4 mb-3">

                <label for="url_facebook" class="form-label">
                    Facebook
                </label>

                <input type="url"
                       class="form-control"
                       id="url_facebook"
                       name="url_facebook"
                       value="${artiste.url_facebook}">

            </div>


            <!-- INSTAGRAM -->
            <div class="col-md-4 mb-3">

                <label for="url_instagram" class="form-label">
                    Instagram
                </label>

                <input type="url"
                       class="form-control"
                       id="url_instagram"
                       name="url_instagram"
                       value="${artiste.url_instagram}">

            </div>


            <!-- SPOTIFY -->
            <div class="col-md-4 mb-3">

                <label for="url_spotify" class="form-label">
                    Spotify
                </label>

                <input type="url"
                       class="form-control"
                       id="url_spotify"
                       name="url_spotify"
                       value="${artiste.url_spotify}">

            </div>


            <!-- CATERING -->
            <div class="col-12 mb-3">

                <label for="exigences_catering" class="form-label">
                    Exigences catering
                </label>

                <textarea class="form-control"
                          id="exigences_catering"
                          name="exigences_catering"
                          rows="4">${artiste.exigences_catering}</textarea>

            </div>

        </div>


        <!-- BOUTONS -->

        <div class="mt-4">

            <button type="submit" class="hm-btn-primary">
                💾 Enregistrer
            </button>

            <a href="${pageContext.request.contextPath}/backoffice/artiste"
               class="hm-link-site">
                Annuler
            </a>

        </div>

    </form>

</div>