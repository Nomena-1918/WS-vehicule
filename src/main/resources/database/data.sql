-- Insertion de données dans la table vehicule
INSERT INTO vehicule (nom_modele) VALUES
('Toyota Corolla'),
('Honda Civic'),
('Ford Mustang'),
('Chevrolet Silverado');

-- Insertion de données dans la table kilometrage
INSERT INTO kilometrage (idVehicule, debutKm, finKm, date_trajet) VALUES
(1, 10000, 12000, '2023-10-01 08:00:00'),
(2, 5000, 7000, '2023-10-02 14:30:00'),
(3, 20000, 22000, '2023-10-03 10:15:00'),
(4, 15000, 16000, '2023-10-04 09:45:00');

-- Insertion de données dans la table utilisateur avec des mots de passe originaux
INSERT INTO utilisateur (email, mdp) VALUES
('captain@spaceship.com', 'BeamMeUpScotty!'),
('guitar@rocknroll.com', 'Strum4Ever$'),
('coding@geekworld.com', 'ILoveCoding#'),
('bookworm@library.com', 'PageTurner42!');

