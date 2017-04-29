-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Sam 29 Avril 2017 à 13:24
-- Version du serveur :  5.7.14
-- Version de PHP :  7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd_projetjee`
--

-- --------------------------------------------------------

--
-- Structure de la table `accesdoc`
--

CREATE TABLE `accesdoc` (
  `Contributeur_ID` int(4) NOT NULL,
  `Document_ID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `amis`
--

CREATE TABLE `amis` (
  `Amis_ID` int(4) NOT NULL,
  `Autilisateur_ID` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `amis`
--

INSERT INTO `amis` (`Amis_ID`, `Autilisateur_ID`) VALUES
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `Commentaire_ID` int(4) NOT NULL,
  `commentaire` text COLLATE utf8_unicode_ci,
  `AutCommentaire_ID` int(4) DEFAULT NULL,
  `Cdoc_ID` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

CREATE TABLE `document` (
  `Document_ID` int(4) NOT NULL,
  `Document_Name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Editeur_ID` int(4) DEFAULT NULL,
  `Type_ID` int(4) DEFAULT NULL,
  `DatePubli` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `document`
--

INSERT INTO `document` (`Document_ID`, `Document_Name`, `Editeur_ID`, `Type_ID`, `DatePubli`) VALUES
(15, 'AffichagePdf.pdf', 12, 0, '2017-04-28'),
(21, 'club.sql', 1, 0, '2017-04-29'),
(20, 'equipe.sql', 1, 0, '2017-04-29'),
(18, 'grille-yams.pdf', 12, 0, '2017-04-28'),
(9, 'TestSingleton.java', 1, 0, '2017-04-30');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `Message_ID` int(4) NOT NULL,
  `Emeteur_ID` int(4) DEFAULT NULL,
  `Recepteur_ID` int(4) DEFAULT NULL,
  `DateMess` datetime DEFAULT NULL,
  `Contenu_Msg` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `typedoc`
--

CREATE TABLE `typedoc` (
  `TypeDoc_ID` int(4) NOT NULL,
  `TypeDoc_Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TDocument_ID` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `Utilisateur_ID` int(4) NOT NULL,
  `Utilisateur_Name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Utilisateur_Fname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Utilisateur_DatN` date DEFAULT NULL,
  `Utilisateur_Pseudo` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Utilisateur_Mdp` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Utilisateur_email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`Utilisateur_ID`, `Utilisateur_Name`, `Utilisateur_Fname`, `Utilisateur_DatN`, `Utilisateur_Pseudo`, `Utilisateur_Mdp`, `Utilisateur_email`) VALUES
(1, 'Komah', 'Momo', '2015-10-14', 'mkomah', 'km123', 'km@gmail.com'),
(2, 'nour', 'kadre', '2015-10-14', 'knour', 'kn123', 'kn@gmail.com'),
(3, 'nianfo', 'solo', '2009-12-22', 'snianfo', 'sn123', 'snia@gmail.com'),
(10, 'niso', 'souleymane', '1970-01-01', 'snianfo01', 'sn1234856', NULL),
(11, 'niso', 'souleymane', '1970-01-01', 'snianfo01', 'sn1234dd', 'snianfo@gmail.fr'),
(12, 'NISO', 'souleymaneN', '1970-01-01', 'snianfo01', 's34789', 'snianfo@gmail.org'),
(13, 'bosoto', 'camille', '1970-01-01', 'bosto01', '77974', 'bosto@mail.org'),
(14, 'barou', 'camille', '3891-11-02', 'barou01', 's787+', 'bosto@mail.org');

-- --------------------------------------------------------

--
-- Structure de la table `versiondoc`
--

CREATE TABLE `versiondoc` (
  `VersionDoc_ID` int(4) NOT NULL,
  `Vdocument_ID` int(4) DEFAULT NULL,
  `Vutilisateur_ID` int(4) DEFAULT NULL,
  `VersionDoc_Name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DatEnregistrement` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `versiondoc`
--

INSERT INTO `versiondoc` (`VersionDoc_ID`, `Vdocument_ID`, `Vutilisateur_ID`, `VersionDoc_Name`, `DatEnregistrement`) VALUES
(1, 7, 1, NULL, '2017-04-24'),
(2, 6, 1, NULL, '2017-04-24'),
(3, 4, 1, NULL, '2017-04-24'),
(4, 8, 1, NULL, '2017-04-24'),
(5, 7, 1, NULL, '2017-04-24'),
(6, 6, 1, NULL, '2017-04-24'),
(7, 6, 1, NULL, '2017-04-24'),
(8, 8, 1, 'club.sql.txt', '2017-04-25'),
(9, 21, 1, 'club.sql.txt', '2017-04-29'),
(10, 20, 1, 'equipe.sql.txt', '2017-04-29'),
(11, 9, 1, 'TestSingleton.java.txt', '2017-04-29');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `accesdoc`
--
ALTER TABLE `accesdoc`
  ADD PRIMARY KEY (`Contributeur_ID`,`Document_ID`);

--
-- Index pour la table `amis`
--
ALTER TABLE `amis`
  ADD PRIMARY KEY (`Amis_ID`),
  ADD KEY `Autilisateur_ID` (`Autilisateur_ID`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`Commentaire_ID`),
  ADD KEY `Cdoc_ID` (`Cdoc_ID`),
  ADD KEY `AutCommentaire_ID` (`AutCommentaire_ID`);

--
-- Index pour la table `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`Document_Name`),
  ADD UNIQUE KEY `Document_Name` (`Document_Name`),
  ADD KEY `Editeur_ID` (`Editeur_ID`),
  ADD KEY `Document_ID` (`Document_ID`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`Message_ID`),
  ADD KEY `Recepteur_ID` (`Recepteur_ID`),
  ADD KEY `Emeteur_ID` (`Emeteur_ID`);

--
-- Index pour la table `typedoc`
--
ALTER TABLE `typedoc`
  ADD PRIMARY KEY (`TypeDoc_Name`,`TypeDoc_ID`),
  ADD KEY `TDocument_ID` (`TDocument_ID`),
  ADD KEY `TypeDoc_ID` (`TypeDoc_ID`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`Utilisateur_ID`),
  ADD UNIQUE KEY `UC_uTILISATEUR` (`Utilisateur_Pseudo`,`Utilisateur_email`,`Utilisateur_Mdp`,`Utilisateur_ID`);

--
-- Index pour la table `versiondoc`
--
ALTER TABLE `versiondoc`
  ADD KEY `Vdocument_ID` (`Vdocument_ID`),
  ADD KEY `Vutisateur_ID` (`Vutilisateur_ID`),
  ADD KEY `VersionDoc_ID` (`VersionDoc_ID`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `document`
--
ALTER TABLE `document`
  MODIFY `Document_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT pour la table `typedoc`
--
ALTER TABLE `typedoc`
  MODIFY `TypeDoc_ID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `Utilisateur_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `versiondoc`
--
ALTER TABLE `versiondoc`
  MODIFY `VersionDoc_ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `accesdoc`
--
ALTER TABLE `accesdoc`
  ADD CONSTRAINT `accesdoc_ibfk_1` FOREIGN KEY (`Contributeur_ID`) REFERENCES `utilisateur` (`Utilisateur_ID`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`AutCommentaire_ID`) REFERENCES `utilisateur` (`Utilisateur_ID`),
  ADD CONSTRAINT `commentaire_ibfk_2` FOREIGN KEY (`Cdoc_ID`) REFERENCES `document` (`Document_ID`);

--
-- Contraintes pour la table `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `document_ibfk_1` FOREIGN KEY (`Editeur_ID`) REFERENCES `utilisateur` (`Utilisateur_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
