CREATE TABLE `Enseignant` (
  `id_enseignant` int PRIMARY KEY,
  `mdp_enseignant` text,
  `nom` text,
  `prenom` text,
  `email` text,
  `tel` text,
  `adresse` text
);

CREATE TABLE `Eleve` (
  `id_eleve` int PRIMARY KEY,
  `mdp_eleve` text,
  `nom` text,
  `prenom` text,
  `email` text,
  `tel` text,
  `adresse` text
);

CREATE TABLE `RendezVous` (
  `id_rdv` int PRIMARY KEY,
  `id_creneau` int,
  `id_enseignant` int,
  `lieu` text,
  `etat` text
);

CREATE TABLE `Planning` (
  `id_creneau` int,
  `id_enseignant` int,
  PRIMARY KEY (`id_creneau`, `id_enseignant`)
);

CREATE TABLE `Creneau` (
  `id_creneau` int PRIMARY KEY,
  `jour` date,
  `heure` datetime
);

CREATE TABLE `RendezVousEleve` (
  `id_rdv` int,
  `id_eleve` int,
  PRIMARY KEY (`id_rdv`, `id_eleve`)
);

ALTER TABLE `RendezVous` ADD FOREIGN KEY (`id_enseignant`) REFERENCES `Enseignant` (`id_enseignant`);

ALTER TABLE `Planning` ADD FOREIGN KEY (`id_enseignant`) REFERENCES `Enseignant` (`id_enseignant`);

ALTER TABLE `Planning` ADD FOREIGN KEY (`id_creneau`) REFERENCES `Creneau` (`id_creneau`);

ALTER TABLE `RendezVousEleve` ADD FOREIGN KEY (`id_rdv`) REFERENCES `RendezVous` (`id_rdv`);

ALTER TABLE `RendezVousEleve` ADD FOREIGN KEY (`id_eleve`) REFERENCES `Eleve` (`id_eleve`);

ALTER TABLE `RendezVous` ADD FOREIGN KEY (`id_creneau`) REFERENCES `Creneau` (`id_creneau`);

