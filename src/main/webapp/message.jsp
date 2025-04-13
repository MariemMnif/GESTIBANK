<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Message</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 40px; /* Augmenter la taille de la zone de texte */
            width: 500px; /* Définir une largeur plus grande */
            text-align: center;
        }
        h1 {
            color: #333;
            font-size: 36px;
            margin-bottom: 20px;
            text-transform: uppercase;
            letter-spacing: 2px;
            font-weight: bold;
        }
        .success-message, .error-message {
            font-size: 24px; /* Augmenter la taille du texte des messages */
            margin-bottom: 20px; /* Espacement entre les messages et le bouton */
        }
        .success-message {
            color: #4CAF50;
        }
        .error-message {
            color: #F44336;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Message</h1>
        
        <%-- Afficher le message de succès s'il est présent --%>
        <%
            String successMessage = (String) request.getAttribute("successMessage");
            if (successMessage != null) {
        %>
                <p class="success-message"><%= successMessage %></p>
        <%
            }
        %>
        
        <%-- Afficher le message d'erreur s'il est présent --%>
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
                <p class="error-message"><%= errorMessage %></p>
        <%
            }
        %>
        
         <a href="CompteListServlet" class="btn">Retour à la page d'accueil</a>
    
       
    </div>
</body>
</html>
