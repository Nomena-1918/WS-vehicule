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

select * from vehicule;

