<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8" />
    <title>Assurance</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .navbar-custom {
            background-color: #007bff;
        }

        .navbar-custom .navbar-brand,
        .navbar-custom .navbar-text {
            color: #ffffff;
            font-size: 24px;
        }

        .navbar-custom .navbar-nav .nav-link {
            color: #ffffff;
            font-size: 18px;
            font-weight: bold;
            padding: 10px 15px;
        }

        .navbar-custom .navbar-nav .nav-link:hover {
            background-color: rgba(255, 255, 255, 0.1);
            border-radius: 5px;
        }

        body {
            background-color: #f8f9fa;
        }

        .table th,
        .table td {
            white-space: nowrap;
        }

        #searchInput {
            width: 50%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        #addAccountBtn {
           margin-bottom: 10px;
           float: right;
           margin-left: 10px;
           font-size: 18px; /* Ajuster la taille de la police */
           padding: 12px 24px;
        }

        #montantMensuelField,
        #dateFinField {
            display: none; /* Cacher les champs montant mensuel et date de fin par défaut */
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container">
            <a class="navbar-brand" href="#">Votre Application</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Accueil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">À propos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid py-5">
        <div class="row">
            <div class="col-md-6">
                <h2>Assurance</h2>
            </div>
        </div>
     
        <!-- Formulaire pour choisir le type d'assurance et les attributs correspondants -->
        <div class="row">
            <div class="col-md-6">
                <form action="AssuranceServlet" method="post">
                    <div class="form-group">
                        <label for="inputNumeroCompte">Numéro de compte:</label>
                        <select class="form-control" id="inputNumeroCompte" name="numeroCompte">
                            <c:forEach var="compte" items="${comptes}">
                                <option value="${compte.numeroCompte}">${compte.numeroCompte}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="typeAssurance">Type d'assurance:</label>
                        <select class="form-control" id="typeAssurance" name="typeAssurance" onchange="showHideFields()">
                            <option value="retraite">Retraite</option>
                            <option value="voyage">Voyage</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="montantInitial">Montant initial:</label>
                        <input type="number" class="form-control" id="montantInitial" name="montantInitial" required>
                    </div>
                    <div class="form-group" id="montantMensuelField">
                        <label for="montantMensuel">Montant mensuel:</label>
                        <input type="number" class="form-control" id="montantMensuel" name="montantMensuel">
                    </div>
                    <div class="form-group">
                        <label for="dateDebut">Date de début:</label>
                        <input type="date" class="form-control" id="dateDebut" name="dateDebut">
                    </div>
                    <div class="form-group" id="dateFinField">
                        <label for="dateFin">Date de fin:</label>
                        <input type="date" class="form-control" id="dateFin" name="dateFin">
                    </div>
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                </form>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            showHideFields(); // Appeler la fonction une fois que le document est prêt
        });

        function showHideFields() {
            var typeAssurance = document.getElementById("typeAssurance").value;
            var montantMensuelField = document.getElementById("montantMensuelField");
            var dateFinField = document.getElementById("dateFinField");
            var montantLabel = document.querySelector('label[for="montantInitial"]');

            if (typeAssurance === "retraite") {
                montantMensuelField.style.display = "block";
                dateFinField.style.display = "block";
                montantLabel.innerHTML = "Montant initial:";
            } else if (typeAssurance === "voyage") {
                montantMensuelField.style.display = "none";
                dateFinField.style.display = "block";
                montantLabel.innerHTML = "Montant:";
            }
        }
    </script>
</body>
</html>
