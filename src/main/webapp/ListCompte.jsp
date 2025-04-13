<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
     <script>
		function confirmerSuppression(numeroCompte) {
		    if (confirm("Êtes-vous sûr de vouloir supprimer ce compte ?")) {
		        window.location.href = "CompteSuppServlet?numeroCompte=${compte.numeroCompte}" + numeroCompte;
		    }
		}
	</script>
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
                <h2>Liste des comptes</h2>
            </div>
        </div>
        <!-- Liste des comptes -->
        <div class="row">
            <div class="col-md-6">
                <input type="text" id="searchInput" onkeyup="searchTable()" placeholder="Rechercher un numéro de compte..">
            </div>
            <div class="col-md-6">
                <a href="code.jsp" id="addAccountBtn" class="btn btn-success">Ajouter un compte</a>
            </div>
        </div>
        <!-- Barre de recherche -->

        <table id="comptesTable" class="table table-striped">
            <thead>
                <tr class="text-center">
                    <th>Numéro </th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>CIN</th>
                    <th>Téléphone</th>
                    <th>Solde</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="compte" items="${listComptes}">
                    <tr class="text-center">
                        <td><c:out value="${compte.numeroCompte}" /></td>
                        <td><c:out value="${compte.nom}" /></td>
                        <td><c:out value="${compte.prenom}" /></td>
                        <td><c:out value="${compte.cin}" /></td>
                        <td><c:out value="${compte.numeroTelephone}" /></td>
                        <td><c:out value="${compte.soldeCompte}" /></td>
                        <td>
                            <a href="ConsulterCompteServlet?numeroCompte=${compte.numeroCompte}" class="btn btn-primary btn-sm">Consulter</a>
                            <a href="CompteModServlet?numeroCompte=${compte.numeroCompte}" class="btn btn-primary btn-sm">Modifier</a>
                            <a href="OperationServlet?numeroCompte=${compte.numeroCompte}" class="btn btn-primary btn-sm">Opération</a>
                            <button onclick="confirmerSuppression('${compte.numeroCompte}')" class="btn btn-danger btn-sm">Supprimer</button>
                            
                            
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
