<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
     
    <title>Liste des assurances</title>
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
                <h2>Liste des assurances</h2>
            </div>
        </div>
        <!-- Liste des comptes -->
        <div class="row">
            <div class="col-md-6">
                <input type="text" id="searchInput" onkeyup="searchTable()" placeholder="Rechercher un numéro de compte..">
            </div>
            <div class="col-md-6">
                <a href="assurance.jsp" id="addAccountBtn" class="btn btn-success">Ajouter une assuarnce</a>
            </div>
        </div>
        <!-- Barre de recherche -->

        <table id="comptesTable" class="table table-striped">
            <thead>
                <tr class="text-center">
                
                    <th>Numéro du compte</th>
                    <th>Type d'assurance</th>
                    <th>date debut</th>
                     <th>date fin</th>
                    <th>Etat</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="assurance" items="${listAssurances}">
                    <tr class="text-center">
                        <td style="display: none;"><c:out value="${assurance.id}" /></td>
                        <td><c:out value="${assurance.numeroCompte}" /></td>
                        <td><c:out value="${assurance.typeAssurance}" /></td>
                        <td><c:out value="${assurance.dateDebut}" /></td>
                         <td><c:out value="${assurance.dateFin}" /></td>
                        <td><c:out value="${assurance.etat}" /></td>
                      
                        <td>
                            <a href="ConsulterAssuranceServlet?numeroCompte=${assurance.numeroCompte}" class="btn btn-primary btn-sm">Consulter</a>
                            <a href="AssuranceModServlet?id=${assurance.id}"${assurance.id}" class="btn btn-primary btn-sm">Modifier</a>
                            <a href="AssuranceRésiliationServlet?id=${assurance.id}" class="btn btn-danger btn-sm">Résiliation</a>
                            
                            
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        function searchTable() {
            // Déclaration des variables
            var input, filter, table, tr, td, i, txtValue;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("comptesTable");
            tr = table.getElementsByTagName("tr");

            // Boucle à travers toutes les lignes et cache celles qui ne correspondent pas à la recherche
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0]; // Première colonne (numéro de compte)
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</body>

</html>
