<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
  <meta charset="utf-8" />
  <title>Nouveau compte</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
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
  </style>
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="#">Nom de l'Application</a>
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
            <h3 class="card-title">Créer un nouveau compte</h3>
          </div>
          <div class="card-body">
            <form action="AjoutCompteServlet" th:action="@{/compte}" th:object="${compte}" method="post">
              <div class="form-group">
                <label class="form-label">Nom</label>
                <input class="form-control" type="text" th:field="*{nom}" name="nom" placeholder="Entrez le nom"
                  required>
              </div>
              <div class="form-group">
                <label class="form-label">Prénom</label>
                <input class="form-control" type="text" th:field="*{prenom}" name="prenom"
                  placeholder="Entrez le prénom" required>
              </div>
              <div class="form-group">
                <label class="form-label">CIN</label>
                <input class="form-control" type="text" th:field="*{cin}" name="cin" placeholder="Entrez le CIN"
                   pattern="[0-9]{8}"  required>
              </div>
              <div class="form-group">
                <label class="form-label">Email</label>
                <input class="form-control" type="text" th:field="*{email}" name="email"
                  placeholder="Entrez l'email"  pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" required>
                  <span class="text-danger" id="emailError" style="display:none;">Veuillez entrer une adresse email valide.</span>
                  
              </div>
              <div class="form-group">
                <label class="form-label">Numéro de téléphone</label>
                <input class="form-control" type="text" th:field="*{numeroTelephone}" name="numeroTelephone"
                  placeholder="Entrez le numéro de téléphone"   pattern="[0-9]{8}" required>
              </div>
              <div class="form-group">
                <label class="form-label">Type de compte :</label>
                <select class="form-control" id="typeCompte" name="typeCompte" onchange="showHideFields()" required>
                  <option value="" disabled selected>Choisissez le type de compte</option>
                  <option value="courant" name="courant">Courant</option>
                  <option value="epargne" name="epargne">Epargne</option>
                </select>
              </div>
              <div class="form-group" id="decouvert" style="display: none;">
                <label class="form-label">Découvert</label>
                <input class="form-control" type="text" name="decouvert" id="decouvert"
                  th:field="*{decouvert}" placeholder="Entrez le découvert">
              </div>
              <div class="form-group" id="taux" style="display: none;">
                <label class="form-label">Taux</label>
                <input class="form-control" type="text" th:field="*{taux}" name="taux" id="taux"
                  placeholder="Entrez le taux">
              </div>
              <div class="form-group">
                <label class="form-label">Solde compte</label>
                <input name="soldeCompte" class="form-control" type="text" th:field="*{soldeCompte}"
                  name="soldeCompte" placeholder="Entrez le solde initial" required>
              </div>
              <div class="form-group text-center">
                <button type="submit" class="btn btn-primary" >Ajouter</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script>
    function showHideFields() {
      var selectedType = document.getElementById("typeCompte").value;
      var decouvertField = document.getElementById("decouvert");
      var tauxField = document.getElementById("taux");

      if (selectedType === "courant") {
        decouvertField.style.display = "block";
        tauxField.style.display = "none";
      } else if (selectedType === "epargne") {
        decouvertField.style.display = "none";
        tauxField.style.display = "block";
      } else {
        decouvertField.style.display = "none";
        tauxField.style.display = "none";
      }
    }
  </script>
</body>

</html>
