-- Réinitialisation des séquences et suppression des données
TRUNCATE vehicule, kilometrage, utilisateur, token_response RESTART IDENTITY;

TRUNCATE token_response  RESTART IDENTITY;
