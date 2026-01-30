CREATE DATABASE  IF NOT EXISTS `ipartube` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ipartube`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ipartube
-- ------------------------------------------------------
-- Server version	8.4.6

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
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuarios_id` bigint NOT NULL,
  `videos_id` bigint NOT NULL,
  `fecha` datetime NOT NULL,
  `texto` varchar(1000) NOT NULL,
  `comentario_padre_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_comentarios` (`usuarios_id`,`videos_id`,`fecha`),
  KEY `fk_comentarios_videos_idx` (`videos_id`),
  KEY `fk_comentarios_usuarios_idx` (`usuarios_id`),
  KEY `fk_comentarios_comentarios1_idx` (`comentario_padre_id`),
  CONSTRAINT `fk_comentarios_comentarios1` FOREIGN KEY (`comentario_padre_id`) REFERENCES `comentarios` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usuarios_has_videos_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usuarios_has_videos_videos1` FOREIGN KEY (`videos_id`) REFERENCES `videos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (3,1,1,'2026-01-28 09:41:00','¡El Rey del Pop!',NULL),(4,2,1,'2026-01-28 09:42:00','¡Sobrevalorado!',3),(5,1,4,'2026-01-28 09:43:00','Joder...',NULL),(6,1,1,'2026-01-28 10:56:38','Inmortal',3),(7,2,1,'2026-01-28 10:58:12','Mola',NULL),(8,2,3,'2026-01-28 10:58:37','¡El de la foto es el de Aerosmith!',NULL),(9,1,1,'2026-01-29 10:34:49','¡Y tanto!',7),(10,2,1,'2026-01-29 10:36:51','¡Tienes razón!',7),(11,1,3,'2026-01-29 10:45:09','Una buena selección',NULL),(12,1,3,'2026-01-29 10:45:21','¡Joder, tienes razón!',8);
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMINISTRADOR','Puede gestionar todos los videos'),(2,'USUARIO','Sólo puede gestionar sus propios videos');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `roles_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_usuarios_roles_idx` (`roles_id`),
  CONSTRAINT `fk_usuarios_roles` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Javier','javier@email.net','cMjMm3rVE20YK1num2ChHQ==:qc9TPUBe0igBPPOrxjYsTparCljXnmSGfTUs5YRPfzE=','https://picsum.photos/id/334/100/100',1),(2,'Pepe','pepe@email.net','m45Kuet0JlxsDinODzFgeg==:KTmkeeKe08AqoSrUFnXGOx54Xi/5Cgnc8psuBPu6rCk=','https://picsum.photos/id/633/100/100',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_le_gusta_videos`
--

DROP TABLE IF EXISTS `usuarios_le_gusta_videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_le_gusta_videos` (
  `usuarios_id` bigint NOT NULL,
  `videos_id` bigint NOT NULL,
  PRIMARY KEY (`usuarios_id`,`videos_id`),
  KEY `fk_usuarios_has_videos_videos2_idx` (`videos_id`),
  KEY `fk_usuarios_has_videos_usuarios2_idx` (`usuarios_id`),
  CONSTRAINT `fk_usuarios_has_videos_usuarios2` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_usuarios_has_videos_videos2` FOREIGN KEY (`videos_id`) REFERENCES `videos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_le_gusta_videos`
--

LOCK TABLES `usuarios_le_gusta_videos` WRITE;
/*!40000 ALTER TABLE `usuarios_le_gusta_videos` DISABLE KEYS */;
INSERT INTO `usuarios_le_gusta_videos` VALUES (1,1),(1,3),(2,3);
/*!40000 ALTER TABLE `usuarios_le_gusta_videos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text,
  `fecha` datetime DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) NOT NULL,
  `usuarios_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `videoUrl_UNIQUE` (`video_url`),
  UNIQUE KEY `imagenUrl_UNIQUE` (`imagen_url`),
  KEY `fk_videos_usuarios1_idx` (`usuarios_id`),
  CONSTRAINT `fk_videos_usuarios1` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'Michael Jackson en Auckly','Con efectos 3D de la época.\r\nComo se nota la diferencia...','2026-01-22 10:26:00','https://i.ytimg.com/vi/ChrLRauOR28/hq720.jpg','https://www.youtube.com/embed/ChrLRauOR28',1),(3,'Clásicos del Rock','',NULL,'https://i.ytimg.com/vi/4_O-y3SM3UM/hq720.jpg','https://www.youtube.com/embed/4_O-y3SM3UM',2),(4,'8BBB en directo','Video de la 8 Bit Bit Band en diciembre de 2025','2026-01-27 11:03:00','https://i.ytimg.com/vi/mzjqdhWxg8M/hq720.jpg','https://www.youtube.com/embed/mzjqdhWxg8M',2);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-30  8:42:33
