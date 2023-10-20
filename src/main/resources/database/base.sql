create table vehicule(
    id serial primary key,
    nom_modele varchar(50) not null
);

create table kilometrage(
    id serial primary key,
    idVehicule int references vehicule(id),
    debutKm decimal not null check ( debutKm > 0 ),
    finKm decimal not null check ( finKm > debutKm ),
    date_trajet timestamp not null check ( date_trajet <= now() )
);

create table utilisateur(
    id serial primary key,
    email varchar(50) not null,
    mdp varchar(200) not null
);
