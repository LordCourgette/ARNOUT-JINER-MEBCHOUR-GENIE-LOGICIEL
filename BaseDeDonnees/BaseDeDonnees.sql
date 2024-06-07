-- Supprimer la base de données si elle existe
DROP DATABASE IF EXISTS Borne;

-- Créer la base de données
CREATE DATABASE Borne;

-- Utiliser la base de données créée
USE Borne;

-- Table: Client
CREATE TABLE Client (
    nom VARCHAR(255),
    prenom VARCHAR(255),
    adresse VARCHAR(255),
    numeroMobile VARCHAR(255),
    adresseEmail VARCHAR(255),
    numeroCarteDebit VARCHAR(255),
    plaquesImmatriculation VARCHAR(255),
    PRIMARY KEY (numeroMobile)
);

INSERT INTO Client (nom, prenom, adresse, numeroMobile, adresseEmail, numeroCarteDebit, plaquesImmatriculation) VALUES
('Dupont', 'Jean', '123 Rue A', '0600000001', 'jean.dupont@example.com', '1111222233334444', 'AB-123-CD'),
('Martin', 'Alice', '456 Avenue B', '0600000002', 'alice.martin@example.com', '5555666677778888', 'EF-456-GH'),
('Lambert', 'Sophie', '789 Boulevard C', '0600000003', 'sophie.lambert@example.com', '9999000011112222', 'IJ-789-KL');

-- Table: StatutBorne
CREATE TABLE StatutBorne (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) UNIQUE
);

INSERT INTO StatutBorne (nom) VALUES
('Fonctionnelle'),
('Disfonctionnelle');

-- Table: Borne
CREATE TABLE Borne (
    identifiant VARCHAR(255) PRIMARY KEY,
    statutBorneId INT,
    capteurIdentifiant VARCHAR(255),
    FOREIGN KEY (statutBorneId) REFERENCES StatutBorne(id)
);

INSERT INTO Borne (identifiant, statutBorneId, capteurIdentifiant) VALUES
('BR001', 1, 'CP001'),
('BR002', 2, 'CP002');

-- Table: Capteur
CREATE TABLE Capteur (
    identifiant VARCHAR(255) PRIMARY KEY,
    statut VARCHAR(255)
);

INSERT INTO Capteur (identifiant, statut) VALUES
('CP001', 'Actif'),
('CP002', 'Inactif');

ALTER TABLE Borne ADD FOREIGN KEY (capteurIdentifiant) REFERENCES Capteur(identifiant);

-- Table: Reservation
CREATE TABLE Reservation (
    id INT AUTO_INCREMENT,
    date DATE,
    timeDebut TIME,
    timeFin TIME,
    clientMobile VARCHAR(255),
    borneIdentifiant VARCHAR(255),
    estConfirmee BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (clientMobile) REFERENCES Client(numeroMobile),
    FOREIGN KEY (borneIdentifiant) REFERENCES Borne(identifiant)
);

INSERT INTO Reservation (date, timeDebut, timeFin, clientMobile, borneIdentifiant, estConfirmee) VALUES
('2024-05-01', '10:00:00', '12:00:00', '0600000001', 'BR001', TRUE),
('2024-05-02', '14:00:00', '16:00:00', '0600000002', 'BR002', FALSE);

-- Vues

-- Vue: VClient

CREATE VIEW VClient AS
SELECT
    Client.nom AS nom,
    Client.prenom AS prenom,
    Client.adresse AS adresse,
    Client.numeroMobile AS numeroMobile,
    Client.adresseEmail AS adresseEmail,
    Client.numeroCarteDebit AS numeroCarteDebit,
    Client.plaquesImmatriculation AS plaquesImmatriculation
FROM
    Client;

-- Vue: VBorne

CREATE VIEW VBorne AS
SELECT
    Borne.identifiant AS identifiant,
    Borne.statutBorneId AS statutBorneId,
    StatutBorne.nom AS statutBorne,
    Borne.capteurIdentifiant AS capteurIdentifiant,
    Capteur.statut AS statutCapteur
FROM
    Borne
    JOIN StatutBorne ON Borne.statutBorneId = StatutBorne.id
    JOIN Capteur ON Borne.capteurIdentifiant = Capteur.identifiant;

-- Vue: VReservation

CREATE VIEW VReservation AS
SELECT
    Reservation.id AS id,
    Reservation.date AS date,
    Reservation.timeDebut AS timeDebut,
    Reservation.timeFin AS timeFin,
    Client.nom AS nomClient,
    Client.prenom AS prenomClient,
    Borne.identifiant AS identifiantBorne,
    Borne.statutBorneId AS statutBorneId,
    Reservation.estConfirmee AS estConfirmee
FROM
    Reservation
    JOIN Client ON Reservation.clientMobile = Client.numeroMobile
    JOIN Borne ON Reservation.borneIdentifiant = Borne.identifiant;

-- Vue: VCapteur

CREATE VIEW VCapteur AS
SELECT
    Capteur.identifiant AS identifiant,
    Capteur.statut AS statut
FROM
    Capteur;

-- Procédures

-- Procédure: PAddClient

DELIMITER $$

CREATE PROCEDURE PAddClient(
    IN p_nom VARCHAR(255),
    IN p_prenom VARCHAR(255),
    IN p_adresse VARCHAR(255),
    IN p_numeroMobile VARCHAR(255),
    IN p_adresseEmail VARCHAR(255),
    IN p_numeroCarteDebit VARCHAR(255),
    IN p_plaquesImmatriculation VARCHAR(255)
)
BEGIN
    INSERT INTO Client (nom, prenom, adresse, numeroMobile, adresseEmail, numeroCarteDebit, plaquesImmatriculation)
    VALUES (p_nom, p_prenom, p_adresse, p_numeroMobile, p_adresseEmail, p_numeroCarteDebit, p_plaquesImmatriculation);
END$$

DELIMITER ;

-- Procédure: PAddBorneEtCapteur

-- Ici nous avons fait le choix d'ajouter le capteur en même temps que la borne.

DELIMITER $$

CREATE PROCEDURE PAddBorneEtCapteur(
    IN p_identifiant VARCHAR(255),
    IN p_capteurIdentifiant VARCHAR(255)
)
BEGIN
    INSERT INTO Capteur (identifiant, statut)
    VALUES (p_capteurIdentifiant, 'Actif');
    INSERT INTO Borne (identifiant, statutBorneId, capteurIdentifiant)
    VALUES (p_identifiant, 1, p_capteurIdentifiant);
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE PGetBorne(
    IN p_date DATE,
    IN p_timeDebut TIME,
    IN p_timeFin TIME
)
BEGIN
    SELECT b.identifiant
    FROM borne.Borne b
    LEFT JOIN Reservation r ON b.identifiant = r.borneIdentifiant 
        AND r.date = p_date
        AND (
            (r.timeDebut <= p_timeDebut AND r.timeFin > p_timeDebut) OR
            (r.timeDebut < p_timeFin AND r.timeFin >= p_timeFin) OR
            (r.timeDebut >= p_timeDebut AND r.timeFin <= p_timeFin)
        )
    WHERE r.borneIdentifiant IS NULL;
END $$

DELIMITER ;
