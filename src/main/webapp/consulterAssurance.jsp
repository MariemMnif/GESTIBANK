<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulter une assurance</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .hidden {
            display: none;
        }

        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
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
            background-color: #f8f9fa; /* Change body background color */
            border-radius: 0 0 10px 10px;
        }
        
        /* Updated text styles */
        .text-bold {
            font-weight: bold;
        }

        .text-custom {
            color: #007bff;
            font-size: 20px;
            margin-bottom: 5px; /* Add spacing between paragraphs */
        }

        .text-value {
            color: #333333;
            font-size: 18px;
        }
        
        .text-center {
            text-align: center;
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

    <div class="container my-5"> 
        <div class="row justify-content-center">
            <div class="col-md-8"> <!-- Adjust column size -->
                <div class="card">
                    <div class="card-header text-center">
                        <h3 class="card-title">Consulter une assurance</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <p class="text-custom">Numero de compte: <span class="text-value">${assuranceK.numeroCompte}</span></p>
                                <p class="text-custom">Type d'assurance: <span class="text-value">${assuranceK.typeAssurance}</span></p>
                                <p class="text-custom">${assuranceK.typeAssurance == 'voyage' ? 'Montant' : 'Montant initial'}: <span class="text-value">${assuranceK.montantInitial}</span></p>
                                <p class="text-custom ${assuranceK.typeAssurance == 'retraite' ? '' : 'hidden'}">Montant mensuel: <span class="text-value">${assuranceK.montantMensuel}</span></p>
                                <p class="text-custom ${assuranceK.typeAssurance == 'retraite' ? '' : 'hidden'}">Montant: <span class="text-value">${assuranceK.montant}</span></p>
                            </div>
                            <div class="col-md-6">
                                <p class="text-custom">Date de début: <span class="text-value">${assuranceK.dateDebut}</span></p>
                                <p class="text-custom">Date de fin: <span class="text-value">${assuranceK.dateFin}</span></p>
                                <p class="text-custom">État: <span class="text-value">${assuranceK.etat}</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
