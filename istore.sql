-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 09, 2023 at 03:23 PM
-- Server version: 8.0.31
-- PHP Version: 8.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `istore`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventories`
--

CREATE TABLE `inventories` (
  `id` int NOT NULL,
  `id_item` int NOT NULL,
  `id_store` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `inventories`
--

INSERT INTO `inventories` (`id`, `id_item`, `id_store`) VALUES
(1, 6, 1),
(2, 8, 1),
(4, 7, 1),
(5, 5, 1),
(6, 4, 1),
(7, 3, 1),
(8, 2, 1),
(9, 9, 2),
(10, 10, 2),
(11, 10, 2),
(12, 11, 2),
(13, 12, 2),
(14, 13, 2),
(15, 14, 2),
(16, 15, 2),
(17, 16, 2),
(18, 17, 2),
(19, 18, 2),
(20, 19, 2),
(21, 20, 2),
(22, 1, 1),
(23, 21, 3),
(24, 22, 3),
(25, 23, 3),
(26, 24, 3),
(27, 25, 3),
(28, 26, 3),
(29, 27, 3);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `price`) VALUES
(1, 'Cheese Pizza', 8),
(2, 'Veggie Pizza', 10),
(3, 'Pepperoni Pizza', 9),
(4, 'Meat Pizza', 11),
(5, 'Margherita Pizza', 8),
(6, 'BBQ Chicken Pizza', 12),
(7, 'Hawaiian Pizza', 12),
(8, 'Buffalo Pizza', 11),
(9, 'Hyundai Verna', 15000),
(10, 'Toyota Innova Crysta', 20000),
(11, 'Tesla Model X', 70000),
(12, 'Ford Fusion', 24000),
(13, 'Chevrolet Malibu', 23000),
(14, 'Chevrolet Silverado', 30000),
(15, 'Toyota Corolla', 21000),
(16, 'BMW E36', 12000),
(17, 'Nissan Altima', 24000),
(18, 'Peugeot 408', 48000),
(19, 'Citroën C4', 27000),
(20, 'Honda Civic', 22000),
(21, 'ASUS TUF FA507RM-ES73', 999),
(22, 'ASUS Vivobook S', 499),
(23, 'Lenovo V15 G2', 699),
(24, 'ASUS BR1100CKA', 129),
(25, 'Lenovo Ideapad 5i Pro', 599),
(26, 'ASUS VivoBook Flip', 499),
(27, 'Dell Latitude 5330', 1199),
(30, 'akkaa', 998);

-- --------------------------------------------------------

--
-- Table structure for table `stores`
--

CREATE TABLE `stores` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stores`
--

INSERT INTO `stores` (`id`, `name`) VALUES
(1, 'Tossers Pizzeria'),
(2, 'Elegant Automobile'),
(3, 'Blacklink PC'),
(8, 'aaa');

-- --------------------------------------------------------

--
-- Table structure for table `stores_access`
--

CREATE TABLE `stores_access` (
  `id` int NOT NULL,
  `id_store` int NOT NULL,
  `id_user` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `stores_access`
--

INSERT INTO `stores_access` (`id`, `id_store`, `id_user`) VALUES
(6, 3, 15),
(9, 8, 17),
(10, 2, 17),
(11, 3, 17),
(13, 1, 15);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `pseudo` varchar(128) NOT NULL,
  `email` varchar(512) NOT NULL,
  `password` varchar(128) NOT NULL,
  `role` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'visitor',
  `whitelisted` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `pseudo`, `email`, `password`, `role`, `whitelisted`) VALUES
(4, 'test', 'test@test.test', '����g\rT�RE2b�Twη��E�~�\0���', 'admin', 1),
(9, 'enzo', 'enzo@test.fr', '��\Z&\r�\r��NS�ju#�iB�4���I�N�z�', 'visitor', 0),
(15, 'a', 'a', 'ʗ������1��#�M����|Nr��w���H�', 'admin', 1),
(16, 'gabriel', 'gabriel@test.fr', '�]wH/#�I%�v����Ȗ����O��3�z�', 'visitor', 1),
(17, 'g', 'g', '�\n��aG�Ŵ�+}��� �8%0��Jd��#<��)', 'visitor', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventories`
--
ALTER TABLE `inventories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_item` (`id_item`),
  ADD KEY `id_store` (`id_store`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stores`
--
ALTER TABLE `stores`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stores_access`
--
ALTER TABLE `stores_access`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user_access` (`id_user`),
  ADD KEY `id_store_access` (`id_store`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventories`
--
ALTER TABLE `inventories`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `stores`
--
ALTER TABLE `stores`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `stores_access`
--
ALTER TABLE `stores_access`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `inventories`
--
ALTER TABLE `inventories`
  ADD CONSTRAINT `id_item` FOREIGN KEY (`id_item`) REFERENCES `items` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `id_store` FOREIGN KEY (`id_store`) REFERENCES `stores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `stores_access`
--
ALTER TABLE `stores_access`
  ADD CONSTRAINT `id_store_access` FOREIGN KEY (`id_store`) REFERENCES `stores` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `id_user_access` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
