<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire artiste</title>
</head>

<body>

<h1>Ajouter un artiste</h1>

<form method="post" action="${pageContext.request.contextPath}/artiste/form">

    <label for="nom">Nom :</label>
    <input type="text" name="nom" id="nom">

    <label for="groupe">Groupe :</label>

    <select name="groupeId" id="groupe">
        <option value="">-- Choisir un groupe --</option>

        <c:forEach var="groupe" items="${groupes}">
            <option value="${groupe.id}">
                ${groupe.nom}
            </option>
        </c:forEach>
    </select>

    <button type="submit">Enregistrer</button>

</form>

</body>
</html>