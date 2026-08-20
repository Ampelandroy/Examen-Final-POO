1. Masquage du mot de passe
   Pour des raisons de sécurité et éviter d'exposer des identifiants sur Git, aucun mot de passe n'est écrit en dur dans le code.
   À la place, j'ai utilisé une variable d'environnement nommée "DB_PASSWORD". Dans mon code Java, la classe DatabaseConnection récupère cette valeur dynamiquement au moment de la connexion avec la commande System.getenv("DB_PASSWORD").
   Pour configurer cette variable sur IntelliJ :
- Je suis allée dans le menu : Run > Edit Configurations...
- J'ai sélectionné la classe principale (ExamenFinalPooL1Application).
- Dans la section "Environment variables" (accessible via Modify options > Operating System > Environment variables), j'ai ajouté : DB_PASSWORD=mon_mot_de_passe
- J'ai validé avec Apply puis OK.

2. Instructions pour lancer et tester le projet
   Étape 1 : Préparation de la base de données (PostgreSQL)
- Créez une base de données nommée : association_db
- Exécutez les deux scripts SQL situés dans "src/main/resources/" :
    1. database.sql (pour créer les tables app_user et cash_flow)
    2. inserts.sql (pour insérer les jeux de données de test)

Étape 2 : Lancement dans IntelliJ
- Ouvrez le projet dans IntelliJ.
- Configurez la variable d'environnement DB_PASSWORD avec votre mot de passe PostgreSQL local (comme expliqué dans la partie 1).
- Lancez l'application via ExamenFinalPooL1Application.

Étape 3 : Endpoints à tester sur http://localhost:8080
- GET  /cash-flows                  (liste tous les flux)
- GET  /cash-flows?type=donation    (filtre uniquement les donations)
- GET  /cash-flows?type=expense     (filtre uniquement les dépenses)
- GET  /users/{id}/cash-flows       (historique d'un utilisateur, ex: /users/usr-1/cash-flows)
- GET  /balance                     (solde actuel calculé avec la date)
- POST /expenses                    (crée une nouvelle dépense)

Exemple de JSON pour le POST /expenses :
{
"userId": "usr-1",
"amount": 250.00,
"reason": "Achat matériel",
"frequency": "NONE"
}