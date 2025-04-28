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
  `image_url` varchar(191) DEFAULT NULL,
  `release_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_artist` int DEFAULT NULL,
  `upload_by` varchar(191) DEFAULT NULL,
  PRIMARY KEY (`id_album`),
  KEY `fk_album_artist` (`id_artist`),
  CONSTRAINT `albums_ibfk_1` FOREIGN KEY (`id_artist`) REFERENCES `artists` (`id_artist`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_album_artist` FOREIGN KEY (`id_artist`) REFERENCES `artists` (`id_artist`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `albums`
--

LOCK TABLES `albums` WRITE;
/*!40000 ALTER TABLE `albums` DISABLE KEYS */;
INSERT INTO `albums` VALUES (1,'MTP','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/39eee3e4-221e-4ec2-b259-cb957f311037_ab67616d0000b273794744c57c9f35db88249842.jpg','2025-04-04 00:00:00',1,'cda91250-a878-4f54-8d4d-285513856b7e'),(24,'Ai Cũng Phải Bắt Đầu Từ Đâu Đó','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/24c84dd8-95c6-4467-9d69-cdd1275eaa37_202311.png','2025-04-09 00:00:00',11,NULL),(25,'Hoàng','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/82ee4568-f81f-446f-a557-4a8b764ed506_112323.jpg','2025-04-02 00:00:00',13,NULL),(26,'Love Love','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/3918fd15-e97c-4650-9c42-fbc04d2d9de2_images%20%282%29.jpg','2025-04-06 00:00:00',14,NULL),(27,'Dreamee','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/4bb0723e-9d48-4712-bf87-322407824987_35234234.jpg','2025-04-06 00:00:00',15,NULL),(28,'Minh Tinh','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/16d346bb-ca61-4d8f-b641-b31c9363ed79_ffffsds.jpg','2025-04-12 00:00:00',16,NULL),(41,'Show của Đen','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/0de601a0-6341-4246-a14c-a316a18bbcdc_al_showcuaden.jpg','2025-04-13 00:00:00',22,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(42,'Kobukovu','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/6cadbcc7-98aa-4c9e-b9c5-a8f8363609cc_kobukovu.jpg','2025-04-06 00:00:00',22,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(43,'Giải cứu tiểu thư','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/1640ea15-6a10-44e4-b18d-3031e94313f9_artworks-488ab43c-a72b-4e56-8327-45ae3aeb032c-0-t500x500.jpg','2025-04-06 00:00:00',23,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(44,'Người quan trọng nhất','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Album/5a6ebf30-f41d-4312-ab88-0db175a01cba_artworks-b8112998-01a9-43f1-8671-fe8a66c68e43-0-t500x500.jpg','2025-04-05 00:00:00',23,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1');
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Sơn Tùng MTP','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/7e5ea9b5-1122-461b-bbd1-67ed81f90817_59696c9dba7a914d587d886049c10df6.jpg'),(11,'HIEUTHUHAI','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/4f8fe1b3-d66b-4784-8e67-4a6e94b4af9a_photo-3-16820399628461606778534.webp'),(12,'Jack','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/397949e9-0bdb-4d08-b951-26052faa1e96_Jakc.jpg'),(13,'Miu Lê','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/0b62ca07-2cca-4f6a-b435-6e7dc030b8f6_5-6.jpg'),(14,'Cẩm Ly','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/3b1b9fb4-24eb-4b0c-894f-28305ef0dc27_cam-ly-2-6886-1594433321-16766266191541684435596.jpg'),(15,'Chế Linh','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/a397eda8-f0fc-4010-b9e5-92c656e7b6d7_images%20%281%29.jpg'),(16,'Lê Bảo Bình','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/945b00b1-8c9b-4cfb-a47f-ee7c8f43dba5_images.jpg'),(22,'Đen','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/f4881fc1-b586-48ae-bfb3-7e345c3b36db_1596692465856_600.jpg'),(23,'Hồ Việt Trung','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Artist/2a5902d1-c1a9-4068-b882-b9369cede242_1525783830907_600.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_list`
--

LOCK TABLES `custom_list` WRITE;
/*!40000 ALTER TABLE `custom_list` DISABLE KEYS */;
INSERT INTO `custom_list` VALUES (11,'AD1','y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1',0),(12,'AD2','y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1',1),(23,'Test','y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1',1),(33,'AD2 (Copy)','cda91250-a878-4f54-8d4d-285513856b7e',0),(34,'Test (Copy)','cda91250-a878-4f54-8d4d-285513856b7e',0),(35,'ST2','cda91250-a878-4f54-8d4d-285513856b7e',1),(36,'ST23','y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_songlist`
--

LOCK TABLES `custom_songlist` WRITE;
/*!40000 ALTER TABLE `custom_songlist` DISABLE KEYS */;
INSERT INTO `custom_songlist` VALUES (22,11,33),(16,11,38),(18,12,33),(20,12,35),(40,33,33),(41,33,35),(42,35,63),(43,36,63);
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
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_playlists`
--

LOCK TABLES `favorite_playlists` WRITE;
/*!40000 ALTER TABLE `favorite_playlists` DISABLE KEYS */;
INSERT INTO `favorite_playlists` VALUES (71,'Temp','y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1',38),(74,'Bai hat yeu thich cua y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1','y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1',35),(75,'Bai hat yeu thich cua y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1','y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1',40);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,'Pop'),(2,'Rock'),(3,'Hip-Hop'),(4,'Jazz'),(5,'Classical');
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `songs`
--

LOCK TABLES `songs` WRITE;
/*!40000 ALTER TABLE `songs` DISABLE KEYS */;
INSERT INTO `songs` VALUES (33,'Không phải dạng vừa đâu',1,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/6289ba04-20f3-447b-92e5-cba08484ca69_089a2e63ec1d49c3f6be611f1aff1a89.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/14086e16-a383-49fe-a712-a26e314c28df_memories-30s-301801.mp3','2025-04-06 00:00:00',1,1,2,'cda91250-a878-4f54-8d4d-285513856b7e'),(34,'Hãy trao cho anh',1,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/eac3d2e2-5122-4657-b95b-88a373f70a3b_Hay-Trao-Cho-Anh.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/ee4cf18b-07f3-411e-88d5-a802d96a2f17_eclipse-30s-304515.mp3','2025-04-12 00:00:00',1,1,1,'cda91250-a878-4f54-8d4d-285513856b7e'),(35,'Chúng ta của hiện tại',2,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/78193395-a517-4f05-837c-1582310cd160_f0c6b74652e9ed643f3183c7617aaa30.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/1d729154-55cd-45c8-ac75-a7c41bd42751_world-news-30s-153711.mp3','2025-04-28 00:00:00',1,1,5,'cda91250-a878-4f54-8d4d-285513856b7e'),(37,'Như cách anh từng nói',1,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/ce7921b2-ee50-405c-9b22-11c9326ccdd1_01%20%282%29.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/aeb7005c-c302-43df-997a-1edb5a091a0f_motivating-indie-rock-30s-240055.mp3','2025-04-05 00:00:00',13,27,1,NULL),(38,'Đánh cắp mặt trăng',1,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/b0d3df12-e69d-4f9d-baea-88819895ad9d_01%20%281%29.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/b61d0a44-e16e-41b3-8b61-8279963d771b_rain-30s-301803.mp3','2025-04-22 00:00:00',12,26,1,NULL),(40,'Đừng yêu kí ức',2,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/32bf1c06-2ba2-4e6e-8cee-d1af58727029_01%20%283%29.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/b4a6c122-bed3-44c5-b9f6-b4081f5f6a4d_memories-30s-301801.mp3','2025-04-04 00:00:00',14,28,4,NULL),(63,'Friendship',0,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/ca2c1978-0ab8-4279-9eaa-40c26315ffe1_friendship.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/f0f50afc-dfbf-4750-a60f-8369f2b6104a_sample-3s.mp3','2025-04-11 00:00:00',22,41,4,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(64,'Luôn yêu đời',0,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/36e0c57a-2303-4f4b-a7f2-d46a16885813_lu%C3%B4n%20y%C3%AAu%20%C4%91%E1%BB%9Di.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/e760c13c-0814-40bd-a65d-1203ee4a6097_sample-3s.mp3','2025-04-06 00:00:00',22,41,1,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(65,'Làm gì phải hốt',0,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/f3e9e3ae-b628-4d65-9c63-7ec9785a49ab_l%C3%A0m%20g%C3%AC%20ph%E1%BA%A3i%20h%E1%BB%91t.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/7c411020-4842-4e47-afc0-7999bddef5df_sample-3s.mp3','2025-04-06 00:00:00',22,42,4,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(66,'Nấu ăn cho em',0,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/7e8f8515-24de-4cac-99f2-0987c5bdf6ca_n%E1%BA%A5u%20%C4%83n%20cho%20em.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/afb40fcd-513b-49df-a040-1cce25733285_sample-3s.mp3','2025-04-15 00:00:00',22,42,5,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(67,'Anh nguyện chết vì em',0,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/d4f87c01-d62a-4e9e-bf2a-561e18555f39_images.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/efc1747b-4db8-4880-b909-f7180e96c5a0_sample-3s.mp3','2025-04-19 00:00:00',23,43,5,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1'),(68,'Anh không tồn tại',0,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Song/67853075-11cb-450b-9f99-a243daa43ed3_trgfhfgh.jpg','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Audio/Song/fdb7970c-e432-464d-96c9-5c3f064d97f4_sample-3s.mp3','2025-04-06 00:00:00',23,44,1,'y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload_requests`
--

LOCK TABLES `upload_requests` WRITE;
/*!40000 ALTER TABLE `upload_requests` DISABLE KEYS */;
INSERT INTO `upload_requests` VALUES (1,'asdf','sdfg','REJECTED','2025-04-06 00:00:00','2025-04-27 22:16:00',NULL),(15,'dsfsdf@dgasdg.com','sdfgsdgsdg','PENDING','2025-04-24 10:46:05',NULL,'http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Request/03d15fd6-f5a4-45a7-9c6f-671a940b5373_484160651_1180699240093907_2923805657147139888_n.jpg'),(16,'sontung@gmail.com','test','APPROVED','2025-04-27 22:20:35','2025-04-27 22:21:54','http://res.cloudinary.com/dw0rr01cm/raw/upload/v1/LeaFMusic2/Images/Request/6c79bdbd-143e-4928-b0be-95f2803651b2_Screenshot%202025-03-10%20003021.png');
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
INSERT INTO `user_accounts` VALUES ('cda91250-a878-4f54-8d4d-285513856b7e','sontung','e10adc3949ba59abbe56e057f20f883e','sontung@gmail.com','USER','2025-04-16 12:55:12',1,1),('y5QJEMaM8Ow2xOqu5uF1hYwIWkLt1l0REwh2OFn1','admin','e10adc3949ba59abbe56e057f20f883e','admin@gmail.com','ADMIN','2025-03-14 16:41:55',NULL,1);
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

-- Dump completed on 2025-04-28 12:46:08
