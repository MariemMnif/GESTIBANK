<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
  <meta charset="utf-8" />
  <title>Modifier une assurance</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    .hidden {
      display: none;
    }

    body {
      background-color: #f8f9fa;
    }

    .navbar {
      background-color: #007bff;
      border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    }

    .navbar-brand,
    .navbar-text {
      color: #ffffff;
      font-size: 24px;
    }

    .navbar-nav .nav-link {
      color: #ffffff;
      font-size: 18px;
      font-weight: bold;
      padding: 10px 15px;
    }

    .navbar-nav .nav-link:hover {
      background-color: rgba(255, 255, 255, 0.1);
      border-radius: 5px;
    }

    /* Styles de la carte */
    .card {
      border: none;
      border-radius: 10px;
      box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
      max-width: 100%;
    }

    .card-header {
      background-color: #007bff;
      color: #ffffff;
      border-radius: 10px 10px 0 0;
    }

    .card-body {
      background-color: #ffffff;
      border-radius: 0 0 10px 10px;
    }

    /* Styles du bouton primaire */
    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
      border-radius: 20px;
      padding: 10px 20px;
      font-size: 18px;
      font-weight: bold;
    }

    .btn-primary:hover {
      background-color: #0056b3;
      border-color: #0056b3;
    }

    /* Styles des champs de formulaire */
    .form-control:focus {
      box-shadow: none;
      border-color: #007bff;
    }

    .form-label {
      font-weight: bold;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .text-center {
      text-align: center;
    }
  </style>
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="#">Votre Application</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
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

  <div class="container-fluid my-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header text-center">
            <h3 class="card-title">Modifier une assurance</h3>
          </div>

          <div class="card-body">
            <form action="AssuranceModServlet" th:action="@{/assurance}" th:object="${assurance}" method="post">
              <div class="form-group">
                <label class="form-label">Numéro de compte</label>
                <input class="form-control" type="text" value="${assurance.numeroCompte}" name="numeroCompte" id="numeroCompte" required readonly>
              </div>
              <div class="form-group">
                <label class="form-label">Type d'assurance</label>
                <input class="form-control" type="text" value="${assurance.typeAssurance}" name="typeAssurance" id="typeAssurance" required readonly>
              </div>
              <div class="form-group">
    <label class="form-label">Montant initial</label>
    <input class="form-control" type="number" value="${assurance.montantInitial}" name="montantInitial" id="montantInitial">
</div>

              <div class="form-group" id="montantMensuelField" th:classappend="${assurance.typeAssurance == 'retraite' ? '' : 'hidden'}">
                <label class="form-label">Montant mensuel</label>
                <input class="form-control" type="number" value="${assurance.montantMensuel}" name="montantMensuel">
              </div>
              <div class="form-group">
                <label class="form-label">Date de début</label>
                <input class="form-control" type="date" value="${assurance.dateDebut}" name="dateDebut">
              </div>
               <div class="form-group">
                <label class="form-label">Date de Fin</label>
                <input class="form-control" type="date" value="${assurance.dateFin}" name="dateFin">
              </div>
             
              
              <button type="submit" class="btn btn-primary">Modifier</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    // Afficher ou masquer les champs en fonction du type d'assurance sélectionné
    document.addEventListener("DOMContentLoaded", function() {
      var typeAssurance = document.getElementById("typeAssurance").value;
      var montantMensuelField = document.getElementById("montantMensuelField");
      var dateFinField = document.getElementById("dateFinField");

      if (typeAssurance === "retraite") {
        montantMensuelField.style.display = "block";
        dateFinField.style.display = "block";
      } else {
        montantMensuelField.style.display = "none";
        dateFinField.style.display = "none";
      }
    });
  </script> 

<script>
    // Afficher ou masquer les champs en fonction du type d'assurance sélectionné
    document.addEventListener("DOMContentLoaded", function() {
        var typeAssurance = document.getElementById("typeAssurance").value;
        var montantInitialField = document.getElementById("montantInitial");

        if (typeAssurance === "retraite") {
            montantInitialField.readOnly = true;
        } else {
            montantInitialField.readOnly = false;
        }
    });
</script>

  
</body>

</html>
