-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : sam. 28 jan. 2023 à 19:33
-- Version du serveur : 5.7.34
-- Version de PHP : 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `istore`
--

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `pseudo` varchar(128) DEFAULT NULL,
  `email` varchar(512) DEFAULT NULL,
  `password` varchar(512) DEFAULT NULL,
  `role` varchar(64) NOT NULL DEFAULT 'visitor',
  `whitelisted` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `pseudo`, `email`, `password`, `role`, `whitelisted`) VALUES
(1, 'Gaby', 'gabi@gmail.com', 'gabi', 'admin', 0),
(2, 'toma', 'thomas@gmail.com', 'thomas', 'employee', 0),
(3, 'TEST', 'TEST', 'TEST', 'visitor', 0),
(4, 'TEST', 'TEST', 'TEST', 'visitor', 0),
(18, 'Gaby', 'titeux@gmail.com', 'nOT-��Q�5y�/8�˺��9eE�(��N,���', 'visitor', 0),
(19, 'Gaby', 'titeux@gmail.com', 'nOT-��Q�5y�/8�˺��9eE�(��N,���', 'visitor', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
