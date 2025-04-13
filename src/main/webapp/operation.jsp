<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />

    <title>Liste des comptes</title>
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

        #compteDest {
            display: none; /* Cacher la section du compte de destination par d�faut */
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
                        <a class="nav-link" href="#">� propos</a>
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
                <h2>Op�rations</h2>
            </div>
        </div>
     
  
        <!-- Formulaire pour choisir le type d'op�ration et le montant -->
        <div class="row">
            <div class="col-md-6">
                <form action="GestionOperationServlet?numeroCompte=${compte.numeroCompte}" method="post">
 						<div class="row">
					 <div class="col-md-6">
					        <%-- R�cup�rer le num�ro de compte � partir de l'attribut de session --%>
					        <% String numeroCompte = (String) request.getSession().getAttribute("numeroCompte"); %>
					        
					        <%-- Afficher le num�ro de compte dans un input --%>
					        <label for="inputNumeroCompte">Num�ro de compte:</label>
					        <input type="text" id="inputNumeroCompte" name="numeroCompte" value="<%= numeroCompte %>" readOnly>
					    </div>
					</div>
                    <div class="form-group">
                        <label for="typeOperation">Type d'op�ration:</label>
                        <select class="form-control" id="typeOperation" name="typeOperation" onchange="showHideCompteDest()">
                            <option value="retrait">Retrait</option>
                            <option value="versement">Versement</option>
                            <option value="virement">Virement</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="montant">Montant:</label>
                        <input type="number" class="form-control" id="montant" name="montant" required>
                    </div>
                    <!-- Section pour le compte de destination -->
                   <div id="compteDest" class="form-group">
					    <label for="numCompteDest">Compte de destination:</label>
					    <select class="form-control" id="numCompteDest" name="numCompteDest" required>
                            <c:forEach var="compte" items="${comptes}">
                             <option value="${compte.numeroCompte}">${compte.numeroCompte}</option>
        </c:forEach>
    </select>
</div>
                    <button type="submit" class="btn btn-primary">Effectuer</button>
                </form>
            </div>
        </div>
        <!-- Liste des op�rations pour ce compte -->
        <div class="row">
            <div class="col-md-12">
                <h3>Liste des op�rations pour le compte num�ro ${requestScope.numeroCompte}</h3>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Num�ro d'op�ration</th>
                            <th>Type</th>
                            <th>Montant</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="operation" items="${requestScope.listeOperations}">
                            <tr>
                                <td>${operation.numOperation}</td>
                                <td>${operation.type}</td>
                                <td>${operation.montant}</td>
                                <td>${operation.date}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function showHideCompteDest() {
            var selectBox = document.getElementById("typeOperation");
            var selectedValue = selectBox.options[selectBox.selectedIndex].value;
            var compteDest = document.getElementById("compteDest");

            if (selectedValue === "virement") {
                compteDest.style.display = "block"; 
            } else {
                compteDest.style.display = "none"; 
            }
        }
    </script>
</body>
</html>
