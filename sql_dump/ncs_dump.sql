-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 18, 2025 alle 16:06
-- Versione del server: 10.4.25-MariaDB
-- Versione PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nodes`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `about`
--

CREATE TABLE `about` (
  `announcement` int(11) NOT NULL,
  `interest` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `announcement`
--

CREATE TABLE `announcement` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT 'New announcement',
  `author` bigint(20) DEFAULT NULL,
  `location` point DEFAULT NULL,
  `open` tinyint(1) NOT NULL DEFAULT 0,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `description` varchar(255) NOT NULL,
  `canceled` tinyint(1) NOT NULL DEFAULT 0,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `announcement`
--

INSERT INTO `announcement` (`id`, `name`, `author`, `location`, `open`, `date`, `description`, `canceled`, `title`) VALUES
(1, 'New announcement', 452, 0x00000000010100000000000000000000000000000000000000, 0, '2024-10-01 23:08:50', 'Sample a wide range of local wines, cheeses, and traditional dishes. Meet local chefs and winemakers, attend cooking demonstrations, and enjoy live entertainment.\r\nDate: October 12-14, 2024\r\nLocation: Aosta, Valle d\'Aosta', 0, 'Valle d\'Aosta Food and Wine Expo'),
(2, 'New announcement', 452, NULL, 0, '2024-10-16 15:26:58', 'This three-day event offers yoga sessions, guided meditation, nature walks, and wellness workshops. Enjoy the serene mountain scenery and connect with like-minded individuals.\r\nDate: May 3-5, 2024\r\nLocation: Saint-Vincent, Valle d\'Aosta', 0, 'Mountain Wellness Retreat'),
(3, 'New announcement', 54, NULL, 0, '2024-11-10 10:39:41', 'Witness live reenactments of medieval battles, explore historical camps, and enjoy traditional crafts and foods. Perfect for history enthusiasts and families alike.\r\nDate: June 10, 2024\r\nLocation: Fenis Castle, Valle d\'Aosta', 0, 'Historical Reenactment Day'),
(4, 'New announcement', 1204, NULL, 0, '2024-11-18 22:55:40', 'Enjoy a variety of winter sports, including skiing, snowboarding, and ice skating. Warm up with hot cocoa by the fire and savor local cuisine at the food stalls.\r\nDate: December 15-17, 2024\r\nLocation: Courmayeur, Valle d\'Aosta', 0, 'Valle d\'Aosta Winter Fest'),
(5, 'New announcement', 1204, NULL, 0, '2024-11-18 22:56:42', 'Featuring performances by local and international artists, food and craft vendors, and activities for the whole family. Bring your blanket and enjoy live music under the stars.\r\nDate: July 20-21, 2024\r\nLocation: Aosta, Valle d\'Aosta', 0, 'Valle d\'Aosta Summer Music Festival'),
(6, 'New announcement', 1204, NULL, 0, '2024-11-18 22:58:02', 'Participate in grape picking, wine tasting, and vineyard tours. Learn about the winemaking process and enjoy a traditional harvest feast with local delicacies and music.\r\nDate: September 14, 2024\r\nLocation: Donnas, Valle d\'Aosta', 0, 'Grape Harvest Celebration');

-- --------------------------------------------------------

--
-- Struttura della tabella `announcement_competence`
--

CREATE TABLE `announcement_competence` (
  `announcement_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `announcement_competence`
--

INSERT INTO `announcement_competence` (`announcement_id`, `competence_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `announcement_interest`
--

CREATE TABLE `announcement_interest` (
  `announcement_id` bigint(20) NOT NULL,
  `interest_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `announcement_interest`
--

INSERT INTO `announcement_interest` (`announcement_id`, `interest_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `booking`
--

CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `start_date` datetime(6) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `resource` bigint(20) DEFAULT NULL,
  `initiative_id` bigint(20) DEFAULT NULL,
  `initiative` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `booking`
--

INSERT INTO `booking` (`id`, `end_date`, `quantity`, `start_date`, `resource_id`, `resource`, `initiative_id`, `initiative`) VALUES
(1, '2024-10-25 00:00:00.000000', 1, '2024-10-24 00:00:00.000000', NULL, 0, NULL, NULL),
(2, '2024-10-01 01:00:00.000000', 4, '2024-10-01 00:00:00.000000', NULL, 0, NULL, NULL),
(52, '2024-10-31 05:00:00.000000', 6, '2024-10-30 04:00:00.000000', NULL, 1, NULL, NULL),
(102, '2024-11-10 00:00:00.000000', 3, '2024-11-08 00:00:00.000000', NULL, 0, NULL, NULL),
(103, '2024-10-05 09:09:00.000000', 5, '2024-10-02 09:09:00.000000', NULL, 2, NULL, NULL),
(152, '2024-10-27 00:00:00.000000', 6, '2024-11-17 00:00:00.000000', NULL, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `booking_seq`
--

CREATE TABLE `booking_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `booking_seq`
--

INSERT INTO `booking_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Struttura della tabella `booking_test`
--

CREATE TABLE `booking_test` (
  `id` bigint(20) NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `start_date` datetime(6) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `resource` bigint(20) DEFAULT NULL,
  `initiative_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `canceled` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `booking_test`
--

INSERT INTO `booking_test` (`id`, `end_date`, `quantity`, `start_date`, `resource_id`, `resource`, `initiative_id`, `user_id`, `canceled`) VALUES
(0, '2024-10-22 22:16:27.000000', 2, '2024-10-23 22:16:27.000000', NULL, NULL, NULL, NULL, b'0'),
(2, NULL, 1, NULL, NULL, NULL, NULL, NULL, b'0'),
(52, NULL, 1, NULL, NULL, NULL, NULL, NULL, b'0'),
(102, NULL, 0, NULL, NULL, NULL, NULL, NULL, b'0'),
(152, '2024-11-10 00:00:00.000000', 1, '2024-11-03 00:00:00.000000', NULL, 0, NULL, NULL, b'0'),
(202, '2024-10-20 00:00:00.000000', 4, '2024-10-12 00:00:00.000000', NULL, 2, NULL, NULL, b'0'),
(252, '2024-10-04 00:00:00.000000', 0, '2024-10-03 00:00:00.000000', NULL, NULL, 1, NULL, b'0'),
(302, '2024-11-10 00:00:00.000000', 0, '2024-11-03 00:00:00.000000', NULL, NULL, 2, NULL, b'0'),
(352, '2024-11-10 00:00:00.000000', 0, '2024-10-27 00:00:00.000000', NULL, NULL, 52, 1103, b'0'),
(353, '2024-10-20 00:00:00.000000', 5, '2024-10-12 00:00:00.000000', NULL, 0, NULL, 1103, b'0'),
(402, '2024-12-08 11:11:00.000000', 4, '2024-12-08 00:00:00.000000', NULL, 0, NULL, 54, b'0'),
(452, '2024-11-10 00:00:00.000000', 1, '2024-11-03 00:00:00.000000', NULL, 3, NULL, 54, b'0'),
(502, '2024-11-30 00:00:00.000000', 0, '2024-11-01 00:00:00.000000', NULL, NULL, 102, 2, b'0'),
(552, '2024-12-08 22:02:00.000000', 0, '2024-12-08 01:01:00.000000', NULL, NULL, 152, 2, b'0'),
(602, '2024-11-08 00:00:00.000000', 1, '2024-11-07 00:00:00.000000', NULL, 1, NULL, 1, b'0'),
(603, '2024-11-15 00:00:00.000000', 1, '2024-11-14 00:00:00.000000', NULL, 3, NULL, 1, b'0'),
(652, '0111-11-11 11:22:00.000000', 10, '1111-11-11 11:11:00.000000', NULL, 1, NULL, 1, b'1');

-- --------------------------------------------------------

--
-- Struttura della tabella `booking_test_seq`
--

CREATE TABLE `booking_test_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `booking_test_seq`
--

INSERT INTO `booking_test_seq` (`next_val`) VALUES
(751);

-- --------------------------------------------------------

--
-- Struttura della tabella `competence`
--

CREATE TABLE `competence` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `competence`
--

INSERT INTO `competence` (`id`, `name`) VALUES
(1, 'Programming'),
(2, 'Art'),
(3, 'Chemistry'),
(4, 'Economy'),
(5, 'Tourism');

-- --------------------------------------------------------

--
-- Struttura della tabella `district`
--

CREATE TABLE `district` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `location_id` bigint(20) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `district`
--

INSERT INTO `district` (`id`, `name`, `active`, `location_id`, `color`) VALUES
(1, 'Scientists', b'1', 0, '#AA0000'),
(2, 'Artists', b'1', 0, NULL),
(102, 'Programmers', b'1', NULL, '#A811FA'),
(103, 'Astronomers', b'1', NULL, '#FA65A8');

-- --------------------------------------------------------

--
-- Struttura della tabella `district_competence`
--

CREATE TABLE `district_competence` (
  `district_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `district_competence`
--

INSERT INTO `district_competence` (`district_id`, `competence_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `district_interest`
--

CREATE TABLE `district_interest` (
  `district_id` bigint(20) NOT NULL,
  `interest_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `district_interest`
--

INSERT INTO `district_interest` (`district_id`, `interest_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `district_seq`
--

CREATE TABLE `district_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `district_seq`
--

INSERT INTO `district_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Struttura della tabella `group_chat`
--

CREATE TABLE `group_chat` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT 'New chat',
  `purpose` varchar(255) DEFAULT 'Communicate',
  `creator` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `group_chat`
--

INSERT INTO `group_chat` (`id`, `name`, `purpose`, `creator`) VALUES
(1, 'New chat', 'Communicate', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `hub`
--

CREATE TABLE `hub` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `hub`
--

INSERT INTO `hub` (`id`, `name`, `latitude`, `longitude`, `active`, `description`, `type`, `creator_id`) VALUES
(0, 'Aosta Digital Hub', 45.7371, 7.3201, b'1', 'A state-of-the-art digital hub offering robust security measures, including biometric access and 24/7 surveillance. Information is managed through secure servers, and guidelines emphasize data protection and user privacy.', 'hub', 2),
(1, 'Mont Blanc Innovation Center', 45.8336, 6.8652, b'1', 'This hub focuses on innovative solutions with high-security standards, featuring encrypted communications and secure cloud storage. Guidelines highlight collaboration and responsible usage of resources.', 'hub', 2),
(2, 'Cogne Tech Hub', 45.6105, 7.3451, b'1', 'A hub designed for tech enthusiasts, offering advanced security protocols such as two-factor authentication and regular security audits. Information management ensures confidentiality and integrity. Guidelines include ethical hacking practices.', 'hub', 2),
(3, 'Saint-Vincent Business Hub', 45.7485, 7.6582, b'1', 'A business-centric hub with comprehensive security, including secure Wi-Fi and data encryption. Information guidelines promote transparency and accuracy, while usage guidelines focus on professional conduct.', 'hub', 2),
(52, 'Courmayeur Creative Space', 45.793, 6.9707, b'1', 'A hub for creatives with high-security measures, including secure access and frequent data backups. Information guidelines encourage sharing and protecting intellectual property. Usage guidelines foster a collaborative environment.', 'hub', 2),
(102, 'Chatillon Startup Incubator', 45.7502, 7.3507, b'0', 'Tailored for startups, this hub offers top-notch security with firewall protection and secure VPN access. Information guidelines ensure confidentiality of business plans. Usage guidelines support innovation and entrepreneurial growth.', 'hub', 653),
(103, 'Gressoney Valley Research Hub', 45.7825, 7.3178, b'0', 'A research-focused hub with stringent security protocols like restricted access and data encryption. Information guidelines prioritize accuracy and ethical research practices. Usage guidelines emphasize collaborative research.', 'hub', 653),
(104, 'La Thuile Tech Village', 45.7505, 7.5732, b'0', 'A tech village offering high-security features, including real-time threat monitoring and secure data storage. Information guidelines ensure data integrity and user privacy. Usage guidelines promote sustainable technology use.', 'hub', 653),
(105, 'Pont-Saint-Martin Community Hub', 45.7266, 7.1022, b'0', 'A community-oriented hub with secure access controls and encrypted communications. Information guidelines focus on community sharing and protecting personal data. Usage guidelines encourage community engagement and respect.', 'hub', 653),
(106, 'Valtournenche Eco Hub', 45.7036, 7.1374, b'0', 'An eco-friendly hub with strong security measures, including secure biometric access and encrypted data storage. Information guidelines emphasize eco-conscious data management. Usage guidelines support sustainable practices and innovation.', 'hub', 653);

-- --------------------------------------------------------

--
-- Struttura della tabella `hub_district`
--

CREATE TABLE `hub_district` (
  `hub_id` bigint(20) NOT NULL,
  `district_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `hub_district`
--

INSERT INTO `hub_district` (`hub_id`, `district_id`) VALUES
(0, 1),
(0, 2),
(1, 1),
(2, 1),
(3, 102),
(103, 102),
(104, 102),
(104, 103),
(105, 102),
(105, 103),
(106, 103);

-- --------------------------------------------------------

--
-- Struttura della tabella `hub_resource`
--

CREATE TABLE `hub_resource` (
  `hub_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `hub_seq`
--

CREATE TABLE `hub_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `hub_seq`
--

INSERT INTO `hub_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Struttura della tabella `hub_test`
--

CREATE TABLE `hub_test` (
  `id` bigint(20) NOT NULL,
  `shelter` tinyint(1) NOT NULL DEFAULT 0,
  `security` mediumtext NOT NULL DEFAULT 'void',
  `info_guidelines` mediumtext NOT NULL DEFAULT 'void',
  `location` point NOT NULL,
  `operative` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `initiative`
--

CREATE TABLE `initiative` (
  `id` bigint(20) NOT NULL,
  `approved` bit(1) NOT NULL DEFAULT b'0',
  `subject` varchar(255) NOT NULL DEFAULT 'Subject',
  `location_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `initiative`
--

INSERT INTO `initiative` (`id`, `approved`, `subject`, `location_id`, `creator_id`) VALUES
(1, b'0', 'Agricultural meeting', 0, 2),
(2, b'0', 'Distribution provision', 3, 2),
(52, b'0', 'Well-heeled special interests', 2, 653),
(102, b'0', 'Gathering', 0, 2),
(152, b'0', 'Healthy Democracy', 1, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `initiative_seq`
--

CREATE TABLE `initiative_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `initiative_seq`
--

INSERT INTO `initiative_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Struttura della tabella `interest`
--

CREATE TABLE `interest` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `interest`
--

INSERT INTO `interest` (`id`, `name`) VALUES
(1, 'Football'),
(2, 'Gaming'),
(3, 'Sudoku'),
(4, 'Debates'),
(5, 'Wrestling');

-- --------------------------------------------------------

--
-- Struttura della tabella `interested`
--

CREATE TABLE `interested` (
  `user` int(11) NOT NULL,
  `interest` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `item`
--

CREATE TABLE `item` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `end_time` datetime(6) NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `item`
--

INSERT INTO `item` (`id`, `description`, `end_time`, `start_time`, `title`, `type`, `user_id`) VALUES
(1, 'Description1', '2024-10-23 16:23:10.000000', '2024-10-24 16:23:10.000000', 'Title1', 'event', 653);

-- --------------------------------------------------------

--
-- Struttura della tabella `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `between` int(11) NOT NULL,
  `text` mediumtext NOT NULL DEFAULT 'void'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `needed_by`
--

CREATE TABLE `needed_by` (
  `announcement` int(11) NOT NULL,
  `competence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `notification`
--

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `user` int(11) NOT NULL,
  `open` tinyint(1) NOT NULL DEFAULT 0,
  `date` datetime NOT NULL DEFAULT current_timestamp(),
  `text` mediumtext NOT NULL DEFAULT 'void',
  `message` varchar(255) NOT NULL,
  `timestamp` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `resource`
--

CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `hub_id` bigint(20) DEFAULT NULL,
  `bookings` varchar(255) DEFAULT NULL,
  `deleted` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `resource`
--

INSERT INTO `resource` (`id`, `name`, `description`, `type`, `hub_id`, `bookings`, `deleted`) VALUES
(0, 'Resource2', 'Description2', 'Chair', 0, NULL, b'0'),
(1, 'Resource1', 'Description1', 'Chair', 0, '', b'0'),
(2, 'Resource3', 'Description3', 'Table', 0, NULL, b'0'),
(3, 'Resource1', 'Description1', 'Chair', 3, '', b'0'),
(4, NULL, 'DescriptionB', 'Bottle', 52, NULL, b'0'),
(5, NULL, 'DescriptionC', 'Camera', 52, NULL, b'1'),
(52, NULL, 'Reliable and fast internet connectivity to support all digital activities, including video conferencing, cloud computing, and large data transfers.', 'High-Speed Internet', 102, NULL, b'0'),
(53, NULL, 'Encrypted Wi-Fi networks that protect data transmitted over the internet, ensuring safe and private online communications.', 'Secure Wi-Fi Network', 102, NULL, b'0'),
(54, NULL, 'State-of-the-art conference rooms equipped with video conferencing technology to facilitate remote meetings and collaborations.', 'Conference Rooms with Video Conferencing', 103, NULL, b'0'),
(55, NULL, 'State-of-the-art conference rooms equipped with video conferencing technology to facilitate remote meetings and collaborations.', 'Conference Rooms with Video Conferencing', 104, NULL, b'0'),
(56, NULL, 'Comfortable and health-friendly work environments with adjustable furniture, good lighting, and proper ventilation to enhance productivity and well-being.', 'Ergonomic Workspaces', 105, NULL, b'0'),
(57, NULL, 'Comfortable and health-friendly work environments with adjustable furniture, good lighting, and proper ventilation to enhance productivity and well-being.', 'Ergonomic Workspaces', 103, NULL, b'0'),
(102, NULL, '', '', 2, NULL, b'0');

-- --------------------------------------------------------

--
-- Struttura della tabella `resource_seq`
--

CREATE TABLE `resource_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `resource_seq`
--

INSERT INTO `resource_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Struttura della tabella `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `hub` int(11) NOT NULL,
  `text` mediumtext NOT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(2, 'admin'),
(1, 'agent'),
(0, 'business');

-- --------------------------------------------------------

--
-- Struttura della tabella `role_seq`
--

CREATE TABLE `role_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `role_seq`
--

INSERT INTO `role_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Struttura della tabella `studied`
--

CREATE TABLE `studied` (
  `user` int(11) NOT NULL,
  `competence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `banned` tinyint(1) NOT NULL DEFAULT 0,
  `active` tinyint(1) NOT NULL DEFAULT 0,
  `availability` tinyint(1) NOT NULL DEFAULT 1,
  `location_id` bigint(20) DEFAULT NULL,
  `hub_id` bigint(20) DEFAULT NULL,
  `role` bigint(20) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`id`, `name`, `password`, `description`, `banned`, `active`, `availability`, `location_id`, `hub_id`, `role`) VALUES
(1, 'agent', '$2a$10$nyjPGmXaxlD6xu.3kxE0P.kItdps9L/EaBfDBHjLSztGRKcxv4qci', 'Enter your description', 0, 0, 0, 0, NULL, 1),
(2, 'business', '$2a$10$A0Lzm9OCNdnnuydYKbtVSegEjuR5tVD23zJYMg0MqpgNXYmzFjQjK', 'Enter your description', 0, 0, 1, 0, NULL, 0),
(54, 'agent2', '$2a$10$nyjPGmXaxlD6xu.3kxE0P.kItdps9L/EaBfDBHjLSztGRKcxv4qci', 'Enter your description', 0, 1, 0, 2, 3, 1),
(102, 'agent4', '$2a$10$nyjPGmXaxlD6xu.3kxE0P.kItdps9L/EaBfDBHjLSztGRKcxv4qci', '', 1, 1, 1, 0, 0, 1),
(452, 'agent3', '$2a$10$nyjPGmXaxlD6xu.3kxE0P.kItdps9L/EaBfDBHjLSztGRKcxv4qci', NULL, 0, 1, 1, 0, 0, 1),
(602, 'agent5', '$2a$10$DHwUWHJG7co3Sr1fO3TrmestiT5cq0OPVtvFB5/B.5wYPD/v.KHee', NULL, 0, 0, 1, NULL, NULL, 1),
(653, 'business2', '$2a$10$A0Lzm9OCNdnnuydYKbtVSegEjuR5tVD23zJYMg0MqpgNXYmzFjQjK', 'd', 0, 1, 1, 0, 0, 0),
(804, 'agent6', '$2a$10$VdwFkNeQq8.zcNCKAvAXwuh7POCeNeC4lJVwg4UXaPOIy08AYPqdS', NULL, 0, 0, 1, NULL, NULL, 1),
(903, 'agent7', '$2a$10$DbZHxM0B3F82xAaOh/ZrweuNmTDp6X.bH5MjPoedH4qRcHbboiLDq', NULL, 0, 0, 1, 2, NULL, 1),
(1053, 'agent8', '$2a$10$.EbGL.3OrUufJH1lFPPnceEVUdZe7InWf9P.7iDmaL3AqVm2n4h3S', 'Enter your description', 0, 0, 1, 2, NULL, 1),
(1103, 'agent9', '$2a$10$xR08krC98SSXUh9fyRG0lOvscx7jF4s2A4Nrp9hDKkdQpNWkoOofe', 'DescriptionK', 0, 0, 1, 0, NULL, 1),
(1153, 'admin2', '$2a$10$J3.Ck7GmPFw2clxkdG1VaudqVPRb9Hgm6OreSAhYn3.WsprmpdnDK', NULL, 0, 1, 1, NULL, NULL, 2),
(1203, 'admin10', '$2a$10$ez/qqMkMLT61DFgxul9NVedw1PSGeIK/j/fIbVoPOy60teO0YfpvC', NULL, 0, 0, 1, NULL, NULL, 1),
(1204, 'admin', '$2a$10$J3.Ck7GmPFw2clxkdG1VaudqVPRb9Hgm6OreSAhYn3.WsprmpdnDK', 'Enter your description', 0, 0, 0, 0, NULL, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `user_authorities`
--

CREATE TABLE `user_authorities` (
  `user_id` bigint(20) NOT NULL,
  `authorities` varbinary(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `user_competence`
--

CREATE TABLE `user_competence` (
  `user_id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user_competence`
--

INSERT INTO `user_competence` (`user_id`, `competence_id`) VALUES
(102, 1),
(452, 1),
(1053, 1),
(1103, 1),
(54, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `user_group`
--

CREATE TABLE `user_group` (
  `user_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user_group`
--

INSERT INTO `user_group` (`user_id`, `group_id`) VALUES
(102, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `user_interest`
--

CREATE TABLE `user_interest` (
  `user_id` bigint(20) NOT NULL,
  `interest_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user_interest`
--

INSERT INTO `user_interest` (`user_id`, `interest_id`) VALUES
(102, 1),
(452, 1),
(1053, 1),
(1103, 1),
(54, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(903, 1),
(1053, 2),
(1103, 1),
(1153, 1),
(1203, 1),
(1204, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(151);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `about`
--
ALTER TABLE `about`
  ADD PRIMARY KEY (`announcement`,`interest`);

--
-- Indici per le tabelle `announcement`
--
ALTER TABLE `announcement`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `announcement_competence`
--
ALTER TABLE `announcement_competence`
  ADD KEY `FKlqocn7arxy1viof4p7xrpobcb` (`competence_id`),
  ADD KEY `FKn50m5yikswa8ig7ofvhp2pr4i` (`announcement_id`);

--
-- Indici per le tabelle `announcement_interest`
--
ALTER TABLE `announcement_interest`
  ADD KEY `FK7xr916juqaj2cdh53gx8u0nkh` (`interest_id`),
  ADD KEY `FKohck1mr1ycktmltvcsupefkgq` (`announcement_id`);

--
-- Indici per le tabelle `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhocnv6x3nmc1pw469oi2jgvga` (`resource`);

--
-- Indici per le tabelle `booking_test`
--
ALTER TABLE `booking_test`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fok_resource` (`resource_id`),
  ADD KEY `FKd77369v07irxownb6arv0ltne` (`resource`),
  ADD KEY `fk_initiative` (`initiative_id`),
  ADD KEY `FK16xw9fd3mmsfilavs6o5loxp2` (`user_id`);

--
-- Indici per le tabelle `competence`
--
ALTER TABLE `competence`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `district`
--
ALTER TABLE `district`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK892iaifvmdkx4c1gbxo9o07ik` (`location_id`);

--
-- Indici per le tabelle `district_competence`
--
ALTER TABLE `district_competence`
  ADD PRIMARY KEY (`district_id`,`competence_id`),
  ADD KEY `FKask14oq6uw9focwe6cdoww756` (`competence_id`);

--
-- Indici per le tabelle `district_interest`
--
ALTER TABLE `district_interest`
  ADD PRIMARY KEY (`district_id`,`interest_id`),
  ADD KEY `FK2pvuxv6unsaucromh91bp3ore` (`interest_id`);

--
-- Indici per le tabelle `group_chat`
--
ALTER TABLE `group_chat`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `hub`
--
ALTER TABLE `hub`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjuet2ai16sfdopernef2ibrkr` (`creator_id`);

--
-- Indici per le tabelle `hub_district`
--
ALTER TABLE `hub_district`
  ADD PRIMARY KEY (`hub_id`,`district_id`),
  ADD KEY `FK9cdmhd364saivksvp5s9ktt9k` (`district_id`);

--
-- Indici per le tabelle `hub_resource`
--
ALTER TABLE `hub_resource`
  ADD PRIMARY KEY (`hub_id`,`resource_id`),
  ADD KEY `resource_id` (`resource_id`);

--
-- Indici per le tabelle `hub_test`
--
ALTER TABLE `hub_test`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `initiative`
--
ALTER TABLE `initiative`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfd2xbevkoncrattgampfc0nf0` (`location_id`),
  ADD KEY `FKlf3hv2y8qln4va6kejhp5xbqs` (`creator_id`);

--
-- Indici per le tabelle `interest`
--
ALTER TABLE `interest`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `interested`
--
ALTER TABLE `interested`
  ADD PRIMARY KEY (`user`,`interest`);

--
-- Indici per le tabelle `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh4epdoqikj4sfedlxcc9dwwnl` (`user_id`);

--
-- Indici per le tabelle `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `needed_by`
--
ALTER TABLE `needed_by`
  ADD PRIMARY KEY (`competence`);

--
-- Indici per le tabelle `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `resource`
--
ALTER TABLE `resource`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_hub` (`hub_id`);

--
-- Indici per le tabelle `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indici per le tabelle `studied`
--
ALTER TABLE `studied`
  ADD PRIMARY KEY (`user`,`competence`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK87oexwx4kp9yraqtegdil3pit` (`location_id`),
  ADD KEY `FK89jus4pq1pw853fs49t8426te` (`hub_id`),
  ADD KEY `FKl5alypubd40lwejc45vl35wjb` (`role`);

--
-- Indici per le tabelle `user_authorities`
--
ALTER TABLE `user_authorities`
  ADD KEY `FKmj13d0mnuj4cd8b6htotbf9mm` (`user_id`);

--
-- Indici per le tabelle `user_competence`
--
ALTER TABLE `user_competence`
  ADD KEY `FKdhxs42td9boxxg49x16nfxrwo` (`competence_id`),
  ADD KEY `FKbwq5scpi2xxdptgaui8c0jvhy` (`user_id`);

--
-- Indici per le tabelle `user_group`
--
ALTER TABLE `user_group`
  ADD KEY `FK8iqol4x5ecan482ip2ku9tbud` (`group_id`),
  ADD KEY `FK1c1dsw3q36679vaiqwvtv36a6` (`user_id`);

--
-- Indici per le tabelle `user_interest`
--
ALTER TABLE `user_interest`
  ADD KEY `FKb2c20k2dqknrm5t337typ3s1b` (`interest_id`),
  ADD KEY `FKdi9smphhv09dottb2sc1j3k64` (`user_id`);

--
-- Indici per le tabelle `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `role_id` (`role_id`);

--
-- Indici per le tabelle `user_seq`
--
ALTER TABLE `user_seq`
  ADD PRIMARY KEY (`next_val`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `announcement`
--
ALTER TABLE `announcement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `booking`
--
ALTER TABLE `booking`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=153;

--
-- AUTO_INCREMENT per la tabella `competence`
--
ALTER TABLE `competence`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `district`
--
ALTER TABLE `district`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT per la tabella `group_chat`
--
ALTER TABLE `group_chat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `initiative`
--
ALTER TABLE `initiative`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=153;

--
-- AUTO_INCREMENT per la tabella `interest`
--
ALTER TABLE `interest`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `item`
--
ALTER TABLE `item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `notification`
--
ALTER TABLE `notification`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1205;

--
-- AUTO_INCREMENT per la tabella `user_seq`
--
ALTER TABLE `user_seq`
  MODIFY `next_val` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1304;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `announcement_competence`
--
ALTER TABLE `announcement_competence`
  ADD CONSTRAINT `FKlqocn7arxy1viof4p7xrpobcb` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`),
  ADD CONSTRAINT `FKn50m5yikswa8ig7ofvhp2pr4i` FOREIGN KEY (`announcement_id`) REFERENCES `announcement` (`id`);

--
-- Limiti per la tabella `announcement_interest`
--
ALTER TABLE `announcement_interest`
  ADD CONSTRAINT `FK7xr916juqaj2cdh53gx8u0nkh` FOREIGN KEY (`interest_id`) REFERENCES `interest` (`id`),
  ADD CONSTRAINT `FKohck1mr1ycktmltvcsupefkgq` FOREIGN KEY (`announcement_id`) REFERENCES `announcement` (`id`);

--
-- Limiti per la tabella `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `FKhocnv6x3nmc1pw469oi2jgvga` FOREIGN KEY (`resource`) REFERENCES `resource` (`id`),
  ADD CONSTRAINT `fk_resource` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `booking_test`
--
ALTER TABLE `booking_test`
  ADD CONSTRAINT `FK16xw9fd3mmsfilavs6o5loxp2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK58kkua8u4jxibcektnaauvoks` FOREIGN KEY (`initiative_id`) REFERENCES `initiative` (`id`),
  ADD CONSTRAINT `FKd77369v07irxownb6arv0ltne` FOREIGN KEY (`resource`) REFERENCES `resource` (`id`),
  ADD CONSTRAINT `fk_initiative` FOREIGN KEY (`initiative_id`) REFERENCES `initiative` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `fok_resource` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `district`
--
ALTER TABLE `district`
  ADD CONSTRAINT `FK892iaifvmdkx4c1gbxo9o07ik` FOREIGN KEY (`location_id`) REFERENCES `hub` (`id`);

--
-- Limiti per la tabella `district_competence`
--
ALTER TABLE `district_competence`
  ADD CONSTRAINT `FKask14oq6uw9focwe6cdoww756` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`),
  ADD CONSTRAINT `FKp5euf6vydbh2wxjq9036ici14` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`);

--
-- Limiti per la tabella `district_interest`
--
ALTER TABLE `district_interest`
  ADD CONSTRAINT `FK2pvuxv6unsaucromh91bp3ore` FOREIGN KEY (`interest_id`) REFERENCES `interest` (`id`),
  ADD CONSTRAINT `FK3t2j1hchedyfdhqnb6u6ycfnj` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`);

--
-- Limiti per la tabella `hub`
--
ALTER TABLE `hub`
  ADD CONSTRAINT `FKjuet2ai16sfdopernef2ibrkr` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`);

--
-- Limiti per la tabella `hub_district`
--
ALTER TABLE `hub_district`
  ADD CONSTRAINT `FK9cdmhd364saivksvp5s9ktt9k` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`),
  ADD CONSTRAINT `FKn8y0vky1apnujot3563bmijo` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`id`);

--
-- Limiti per la tabella `hub_resource`
--
ALTER TABLE `hub_resource`
  ADD CONSTRAINT `hub_resource_ibfk_1` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`id`),
  ADD CONSTRAINT `hub_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`);

--
-- Limiti per la tabella `initiative`
--
ALTER TABLE `initiative`
  ADD CONSTRAINT `FKfd2xbevkoncrattgampfc0nf0` FOREIGN KEY (`location_id`) REFERENCES `hub` (`id`),
  ADD CONSTRAINT `FKlf3hv2y8qln4va6kejhp5xbqs` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`);

--
-- Limiti per la tabella `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FKh4epdoqikj4sfedlxcc9dwwnl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Limiti per la tabella `resource`
--
ALTER TABLE `resource`
  ADD CONSTRAINT `FKo8hg8w8s2laggw8k0e288e42v` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`id`),
  ADD CONSTRAINT `fk_hub` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK87oexwx4kp9yraqtegdil3pit` FOREIGN KEY (`location_id`) REFERENCES `hub` (`id`),
  ADD CONSTRAINT `FK89jus4pq1pw853fs49t8426te` FOREIGN KEY (`hub_id`) REFERENCES `hub` (`id`),
  ADD CONSTRAINT `FKl5alypubd40lwejc45vl35wjb` FOREIGN KEY (`role`) REFERENCES `role` (`id`);

--
-- Limiti per la tabella `user_authorities`
--
ALTER TABLE `user_authorities`
  ADD CONSTRAINT `FKmj13d0mnuj4cd8b6htotbf9mm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Limiti per la tabella `user_competence`
--
ALTER TABLE `user_competence`
  ADD CONSTRAINT `FKbwq5scpi2xxdptgaui8c0jvhy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKdhxs42td9boxxg49x16nfxrwo` FOREIGN KEY (`competence_id`) REFERENCES `competence` (`id`);

--
-- Limiti per la tabella `user_group`
--
ALTER TABLE `user_group`
  ADD CONSTRAINT `FK1c1dsw3q36679vaiqwvtv36a6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK8iqol4x5ecan482ip2ku9tbud` FOREIGN KEY (`group_id`) REFERENCES `group_chat` (`id`);

--
-- Limiti per la tabella `user_interest`
--
ALTER TABLE `user_interest`
  ADD CONSTRAINT `FKb2c20k2dqknrm5t337typ3s1b` FOREIGN KEY (`interest_id`) REFERENCES `interest` (`id`),
  ADD CONSTRAINT `FKdi9smphhv09dottb2sc1j3k64` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Limiti per la tabella `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
