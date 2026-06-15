-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: leafmusic
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albums` (
  `id_album` int NOT NULL AUTO_INCREMENT,
  `name` varchar(191) NOT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `release_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_artist` int DEFAULT NULL,
  `upload_by` varchar(191) DEFAULT NULL,
  PRIMARY KEY (`id_album`),
  KEY `fk_album_artist` (`id_artist`),
  CONSTRAINT `albums_ibfk_1` FOREIGN KEY (`id_artist`) REFERENCES `artists` (`id_artist`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (45,'Tâm Trạng Tan Hơi Chậm Một Chút','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/0d33a788-f601-48f3-aff3-72634292aeee_T%C3%A2m%20Tr%E1%BA%A1ng%20Tan%20H%C6%A1i%20Ch%E1%BA%ADm%20M%E1%BB%99t%20Ch%C3%BAt.jpg','2018-04-30 00:00:00',25,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(46,'Bùa Yêu','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/c2d4c336-4c1c-4e38-b8bb-58093eab2874_B%C3%B9a%20Y%C3%AAu.jpg','2021-07-16 00:00:00',25,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(47,'Ba Chấm','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/ac687183-61ce-4496-b5fc-d91625d1246a_3cham.jpg','2024-08-22 00:00:00',26,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(48,'Chạm Khẽ Tim Anh Một Chút Thôi','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/353804d8-c3db-42ac-a26a-d0702f0103bd_Ch%E1%BA%A1m%20Kh%E1%BA%BD%20Tim%20Anh%20M%E1%BB%99t%20Ch%C3%BAt%20Th%C3%B4i.jpg','2025-12-13 00:00:00',26,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(49,'1989','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/18556dfb-59e0-48e7-a5f1-996e96954057_1989.jpg','1989-12-03 00:00:00',27,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(50,'Midnights','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/bf43cace-d638-4dc8-8fd6-80d19dfd9160_Midnights.jpg','2020-06-18 00:00:00',27,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(51,'After Hours','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/3653d394-a88c-4f30-9ef8-8f3dcc455b49_After%20Hours.jpg','2023-06-08 00:00:00',28,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(52,'Dawn FM','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/7928a6cf-410b-4226-8e15-35bb7c48f0ea_Dawn%20FM.jpg','2021-02-20 00:00:00',28,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(53,'M-TP M-TP','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/49027bbb-7db0-4422-8d9c-6fdf82e3237f_ab67616d0000b273794744c57c9f35db88249842.jpg','2017-07-14 00:00:00',24,'1640e5ec-2247-4050-8e14-27b4a3a1e999'),(54,'Chúng ta','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Album/276e0b6a-f288-49b6-b059-6408f03d8903_Ch%C3%BAng%20Ta%20C%E1%BB%A7a%20Hi%E1%BB%87n%20T%E1%BA%A1i.webp','2024-07-12 00:00:00',24,'1640e5ec-2247-4050-8e14-27b4a3a1e999');
/*!40000 ALTER TABLE `albums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `id_artist` int NOT NULL AUTO_INCREMENT,
  `name` varchar(191) NOT NULL,
  `image_url` varchar(191) DEFAULT NULL,
  PRIMARY KEY (`id_artist`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (24,'Sơn Tùng M-TP','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Artist/d414b53a-5b2f-4a23-9d73-171441fc9c50_images.jpg'),(25,'Bích Phương','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Artist/50b2a84b-87e6-478f-855d-f9f33d3d005d_9a8a68d403d3402c4f9976c65c2db3b2.jpg'),(26,'Noo Phước Thịnh','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Artist/97c45e12-8b47-4f1f-a729-f74e2548942a_ab6761610000e5eb8db5f2015bbfe707163221f8.jpg'),(27,'Taylor Swift','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Artist/f14b4b8b-7f4d-40c1-a981-c1a38cd69632_dbcb76c431a945a910f8924d4ae86136.jpg'),(28,'The Weeknd','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Artist/f609e2a3-415c-46e8-a225-3a506abce155_f2ea929678113e1869a05dbbb460523f.jpg');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_list`
--

DROP TABLE IF EXISTS `custom_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_list` (
  `id_list` int NOT NULL AUTO_INCREMENT,
  `name` varchar(191) DEFAULT NULL,
  `id_user` varchar(191) DEFAULT NULL,
  `state` int DEFAULT NULL,
  PRIMARY KEY (`id_list`),
  KEY `fk_customlist_user` (`id_user`),
  CONSTRAINT `fk_customlist_user` FOREIGN KEY (`id_user`) REFERENCES `user_accounts` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_list`
--

LOCK TABLES `custom_list` WRITE;
/*!40000 ALTER TABLE `custom_list` DISABLE KEYS */;
INSERT INTO `custom_list` VALUES (38,'test','fe6737c7-097f-4955-aebe-083d73b564f3',0),(39,'sontung','1640e5ec-2247-4050-8e14-27b4a3a1e999',1),(41,'sontung (Copy)','6f0ef99c-3600-4a3b-b238-b1ffd63b9c15',0);
/*!40000 ALTER TABLE `custom_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_songlist`
--

DROP TABLE IF EXISTS `custom_songlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_songlist` (
  `id_item` int NOT NULL AUTO_INCREMENT,
  `id_list` int DEFAULT NULL,
  `id_song` int DEFAULT NULL,
  PRIMARY KEY (`id_item`),
  UNIQUE KEY `unique_song_per_list` (`id_list`,`id_song`),
  KEY `fk_customsonglist_song` (`id_song`),
  CONSTRAINT `fk_customsonglist_list` FOREIGN KEY (`id_list`) REFERENCES `custom_list` (`id_list`) ON DELETE CASCADE,
  CONSTRAINT `fk_customsonglist_song` FOREIGN KEY (`id_song`) REFERENCES `songs` (`id_song`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_songlist`
--

LOCK TABLES `custom_songlist` WRITE;
/*!40000 ALTER TABLE `custom_songlist` DISABLE KEYS */;
INSERT INTO `custom_songlist` VALUES (44,38,90),(45,39,73),(47,41,73),(48,41,93);
/*!40000 ALTER TABLE `custom_songlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_playlists`
--

DROP TABLE IF EXISTS `favorite_playlists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_playlists` (
  `id_playlist` int NOT NULL AUTO_INCREMENT,
  `name` varchar(191) NOT NULL,
  `id_user` varchar(191) DEFAULT NULL,
  `id_song` int DEFAULT NULL,
  PRIMARY KEY (`id_playlist`),
  KEY `id_user` (`id_user`),
  KEY `id_song` (`id_song`),
  CONSTRAINT `favorite_playlists_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user_accounts` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorite_playlists_ibfk_2` FOREIGN KEY (`id_song`) REFERENCES `songs` (`id_song`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_playlists`
--

LOCK TABLES `favorite_playlists` WRITE;
/*!40000 ALTER TABLE `favorite_playlists` DISABLE KEYS */;
INSERT INTO `favorite_playlists` VALUES (80,'Bai hat yeu thich cua fe6737c7-097f-4955-aebe-083d73b564f3','fe6737c7-097f-4955-aebe-083d73b564f3',90),(81,'Bai hat yeu thich cua 1640e5ec-2247-4050-8e14-27b4a3a1e999','1640e5ec-2247-4050-8e14-27b4a3a1e999',69),(82,'Bai hat yeu thich cua 6f0ef99c-3600-4a3b-b238-b1ffd63b9c15','6f0ef99c-3600-4a3b-b238-b1ffd63b9c15',75);
/*!40000 ALTER TABLE `favorite_playlists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `id_genre` int NOT NULL AUTO_INCREMENT,
  `name` varchar(191) NOT NULL,
  PRIMARY KEY (`id_genre`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (15,'V-Pop'),(16,'Pop'),(17,'Ballad'),(18,'Dance Pop'),(19,'R&B');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songs` (
  `id_song` int NOT NULL AUTO_INCREMENT,
  `name` varchar(191) NOT NULL,
  `play` int DEFAULT NULL,
  `image_url` varchar(191) DEFAULT NULL,
  `file_url` varchar(191) DEFAULT NULL,
  `release_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_artist` int DEFAULT NULL,
  `id_album` int DEFAULT NULL,
  `id_genre` int DEFAULT NULL,
  `upload_by` varchar(191) DEFAULT NULL,
  PRIMARY KEY (`id_song`),
  KEY `fk_songs_singer_id` (`id_artist`),
  KEY `fk_song_album` (`id_album`),
  KEY `fk_song_genre` (`id_genre`),
  CONSTRAINT `fk_song_album` FOREIGN KEY (`id_album`) REFERENCES `albums` (`id_album`) ON DELETE CASCADE,
  CONSTRAINT `fk_song_genre` FOREIGN KEY (`id_genre`) REFERENCES `genres` (`id_genre`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES (69,'Có Khi Nào Rời Xa',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/25a98d44-0325-4fbe-aba4-b75ec5f192ff_C%C3%B3%20Khi%20N%C3%A0o%20R%E1%BB%9Di%20Xa.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/c7393bae-e449-4988-bb80-8ed942ed82d0_C%C3%B3%20Khi%20N%C3%A0o%20R%E1%BB%9Di%20Xa.mp3','2021-05-13 00:00:00',25,45,17,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(70,'Mình Yêu Nhau Đi',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/7e53d0f6-b38e-408b-afa2-fb1a8969cb36_M%C3%ACnh%20Y%C3%AAu%20Nhau%20%C4%90i.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/70a1cb56-1d90-4b03-a462-6d551a970816_M%C3%ACnh%20Y%C3%AAu%20Nhau%20%C4%90i.mp3','2024-07-11 00:00:00',25,45,17,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(71,'Vẫn ',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/48ff1bae-c868-494d-a2fb-a55ab6f6e985_V%E1%BA%ABn.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/2b09359b-44fb-408f-a88a-0a4fda28569c_V%E1%BA%ABn.mp3','2023-11-17 00:00:00',25,48,17,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(72,'Bùa Yêu',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/5746c030-d18d-4132-a7d2-1b1a440a2a22_B%C3%B9a%20Y%C3%AAu.png','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/5a667458-a17a-4519-a0fa-200140ef5c9e_B%C3%B9a%20Y%C3%AAu.mp3','2024-05-16 00:00:00',25,46,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(73,'Đi đu đưa đi',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/8b44a8a4-2eb9-4c3d-aa27-0996c4bbaa97_%C4%90i%20%C4%91u%20%C4%91%C6%B0a%20%C4%91i.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/397b833e-7ad1-4c97-a87d-566f61549f3e_%C4%90i%20%C4%90u%20%C4%90%C6%B0a%20%C4%90i.mp3','2022-07-08 00:00:00',25,46,18,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(74,'Xa em',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/1be99d0b-5e45-4723-b4c1-e881646d2897_Xa%20em.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/a64ee82b-7582-4f07-80b4-aeecfb239136_Xa%20Em.mp3','2021-07-22 00:00:00',26,47,17,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(75,'Đổi thay',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/fc32b715-dd5d-430a-8d27-a889b0b30176_%C4%90%E1%BB%95i%20thay.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/069f3344-76a3-4ce5-ab95-dd74ec97b6a6_%C4%90%E1%BB%95i%20Thay.mp3','2024-07-18 00:00:00',26,47,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(76,'Như phút ban đầu',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/eff4cbdb-929d-4b5d-a80b-9bfef6f52a8b_Nh%C6%B0%20ph%C3%BAt%20ban%20%C4%91%E1%BA%A7u.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/c4d4b73f-c419-4202-841d-13372c91b6ff_Nh%C6%B0%20Ph%C3%BAt%20Ban%20%C4%90%E1%BA%A7u.mp3','2024-08-22 00:00:00',26,47,17,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(77,'Chạm Khẽ Tim Anh Một Chút Thôi',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/ad07b2cb-d1fb-440a-a6a7-f36c019cb873_Ch%E1%BA%A1m%20kh%E1%BA%BD%20tim%20anh%20m%E1%BB%99t%20ch%C3%BAt%20th%C3%B4i.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/789e749e-44b1-415c-87b2-0788d70f6388_Ch%E1%BA%A1m%20Kh%E1%BA%BD%20Tim%20Anh%20M%E1%BB%99t%20Ch%C3%BAt%20Th%C3%B4i.mp3','2024-07-11 00:00:00',26,48,17,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(78,'Cause I Love You',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/26df892f-cf63-47c9-a30c-53f03af9f83a_Cause%20I%20Love%20You.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/dc6b71fd-661f-4c72-856d-c9904b665e00_Cause%20I%20Love%20You.mp3','2024-06-13 00:00:00',26,48,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(79,'Blank Space',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/2fd41d56-bc89-4374-8d8c-ab7da5b301a1_Blank%20Space.png','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/b39a7d9e-6c44-47f4-8844-dee7dc6ab681_Blank%20Space.mp3','2024-11-14 00:00:00',27,49,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(80,'Style',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/a55e02ac-31ab-4823-a4f5-5a89681ffdd4_Taylor_Swift_-_Style_%28Official_Single_Cover%29.png','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/0da95c50-0e5b-4dde-b7c3-7aaa78161321_Style.mp3','2024-12-18 00:00:00',27,49,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(81,'Shake It Off',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/673809fe-fa07-4d7a-9c43-df2dc6945b09_Blank%20Space.png','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/737be1d6-fdad-4a77-a31a-8a04976cd036_Shake%20It%20Off.mp3','2022-07-17 00:00:00',27,49,18,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(82,'Anti-Hero',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/1b431d1e-751b-4c29-a27b-afb9d4453528_Taylor_Swift_-_Anti-Hero.png','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/ce8195f2-8871-4f1a-afef-e90a3d198676_Anti-hero.mp3','2023-06-24 00:00:00',27,50,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(83,'Karma',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/a3a8ec3d-948e-4366-874e-71e49c4a493d_Karrma.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/639fdd49-029b-48d3-855c-0a70f33939a4_Karma.mp3','2024-06-15 00:00:00',27,50,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(84,'Blinding Lights',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/46484ab6-216e-41ce-ad39-a6fa9e930e6c_Blinding%20Lights.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/7d2b4873-a756-41f9-9108-b21502bbf4a9_Blinding%20Lights.mp3','2023-02-03 00:00:00',28,51,18,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(85,'Save Your Tears',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/c758e192-ec9b-43e3-a6ac-04510b70fcfc_Save%20Your%20Tears.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/44dd8ddc-a364-4094-861d-b89d50d6a1b2_Save%20Your%20Tears.mp3','2022-07-07 00:00:00',28,51,16,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(86,'In Your Eyes',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/f1bb3e1d-bb43-4172-ab11-a7d46f7d38b3_In%20Your%20Eyes.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/78ffe578-d616-47a5-b99a-9b68c5783806_In%20Your%20Eyes.mp3','2023-07-08 00:00:00',28,51,19,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(87,'Take My Breath',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/42b35801-bb97-4d6d-ba82-2c41b03b728c_Take%20my%20breath.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/f0a6f4cb-f6e3-475c-9085-bc570e98f6ca_Take%20My%20Breath.mp3','2023-06-15 00:00:00',28,52,18,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(88,'Sacrifice',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/6f98ae55-b87c-42a9-9132-8caeb233632a_Sacrifice.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/62ab594f-cce9-4cf3-b8a9-4b343ef59306_Sacrifice.mp3','2022-06-09 00:00:00',28,52,19,'6f0ef99c-3600-4a3b-b238-b1ffd63b9c15'),(89,'Em của ngày hôm qua',1,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/5e91451b-0032-4410-aa71-8504ba6f24ab_Em_c%E1%BB%A7a_ng%C3%A0y_h%C3%B4m_qua.png','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/2ceb43ec-9c0e-4085-82ea-5711cd772a0a_Em%20C%E1%BB%A7a%20Ng%C3%A0y%20H%C3%B4m%20Qua.mp3','2016-05-04 00:00:00',24,53,15,'1640e5ec-2247-4050-8e14-27b4a3a1e999'),(90,'Cơn mưa ngang qua',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/69010214-b857-4ddc-870d-5c623abe6b8c_S%C6%A1n_T%C3%B9ng_M-TP_-_C%C6%A1n_m%C6%B0a_ngang_qua.png','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/7524e36f-479f-4fbf-9748-2f05d75f2a09_C%C6%A1n%20M%C6%B0a%20Ngang%20Qua.mp3','2018-07-05 00:00:00',24,53,17,'1640e5ec-2247-4050-8e14-27b4a3a1e999'),(91,'Không phải dạng vừa đâu',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/bb88597f-7b21-4e56-afa9-1bd8051bc77d_Kh%C3%B4ng%20Ph%E1%BA%A3i%20D%E1%BA%A1ng%20V%E1%BB%ABa%20%C4%90%C3%A2u.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/5c9d8f99-1674-4a19-8e32-dd6f4c520d70_Kh%C3%B4ng%20Ph%E1%BA%A3i%20D%E1%BA%A1ng%20V%E1%BB%ABa%20%C4%90%C3%A2u.mp3','2017-06-07 00:00:00',24,53,18,'1640e5ec-2247-4050-8e14-27b4a3a1e999'),(92,'Chúng ta của hiện tại',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/1cf5fbd1-2633-48e5-90f1-1de64d24221d_Ch%C3%BAng_ta_c%E1%BB%A7a_hi%E1%BB%87n_t%E1%BA%A1i.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/4dd925e0-37e6-4b2b-a118-d9ec338c7b1e_Ch%C3%BAng%20Ta%20C%E1%BB%A7a%20Hi%E1%BB%87n%20T%E1%BA%A1i.mp3','2024-04-12 00:00:00',24,54,19,'1640e5ec-2247-4050-8e14-27b4a3a1e999'),(93,'Hãy trao cho anh',0,'http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Images/Song/d32ede72-149a-4bca-88dc-e87cea222522_Hay-Trao-Cho-Anh.jpg','http://res.cloudinary.com/dosjtfv6t/raw/upload/v1/LeaFMusic2/Audio/Song/b29f2aa7-48c7-4be9-8671-b35d084d8be7_H%C3%A3y%20Trao%20Cho%20Anh.mp3','2024-08-16 00:00:00',24,54,18,'1640e5ec-2247-4050-8e14-27b4a3a1e999');
/*!40000 ALTER TABLE `songs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload_requests`
--

DROP TABLE IF EXISTS `upload_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `upload_requests` (
  `id_request` int NOT NULL AUTO_INCREMENT,
  `email` varchar(191) NOT NULL,
  `message` text,
  `status` enum('PENDING','APPROVED','REJECTED') DEFAULT 'PENDING',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `reviewed_at` timestamp NULL DEFAULT NULL,
  `file_url` varchar(191) DEFAULT NULL,
  PRIMARY KEY (`id_request`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_requests`
--

LOCK TABLES `upload_requests` WRITE;
/*!40000 ALTER TABLE `upload_requests` DISABLE KEYS */;
INSERT INTO `upload_requests` VALUES (18,'sontungmtp@gmail.com','test','APPROVED','2026-06-15 19:01:00','2026-06-15 19:01:16',NULL);
/*!40000 ALTER TABLE `upload_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_accounts`
--

DROP TABLE IF EXISTS `user_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_accounts` (
  `id_user` varchar(191) NOT NULL,
  `username` varchar(191) NOT NULL,
  `password` varchar(191) NOT NULL,
  `email` varchar(191) DEFAULT NULL,
  `role` enum('USER','ADMIN') DEFAULT 'USER',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_artist` bigint DEFAULT NULL,
  `upload` int DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_accounts`
--

LOCK TABLES `user_accounts` WRITE;
/*!40000 ALTER TABLE `user_accounts` DISABLE KEYS */;
INSERT INTO `user_accounts` VALUES ('1640e5ec-2247-4050-8e14-27b4a3a1e999','sontungmtp','748c2c9b8e38df223727a09ee8d68425','sontungmtp@gmail.com','USER','2026-06-15 18:59:26',24,1),('6f0ef99c-3600-4a3b-b238-b1ffd63b9c15','thuanluong1230','748c2c9b8e38df223727a09ee8d68425','thuanluong1230@gmail.com','ADMIN','2026-06-15 17:41:36',NULL,0),('fe6737c7-097f-4955-aebe-083d73b564f3','thuanluong2403','748c2c9b8e38df223727a09ee8d68425','thuanluong2403@gmail.com','USER','2026-06-15 19:49:16',NULL,0);
/*!40000 ALTER TABLE `user_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'leafmusic'
--
/*!50003 DROP PROCEDURE IF EXISTS `clone_playlist` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `clone_playlist`(
    IN original_list_id INT,
    IN new_user_id VARCHAR(191)
)
BEGIN
    DECLARE new_list_id INT;

    -- Tạo playlist mới, set state = 0
    INSERT INTO custom_list (name, id_user, state)
    SELECT CONCAT(name, ' (Copy)'), new_user_id, 0
    FROM custom_list
    WHERE id_list = original_list_id;

    -- Lấy id_list mới
    SET new_list_id = LAST_INSERT_ID();

    -- Copy bài hát
    INSERT INTO custom_songlist (id_list, id_song)
    SELECT new_list_id, id_song
    FROM custom_songlist
    WHERE id_list = original_list_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-15 21:36:12
