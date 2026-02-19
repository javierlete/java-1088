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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (3,1,1,'2026-01-28 09:41:00','¡El Rey del Pop!',NULL),(4,2,1,'2026-01-28 09:42:00','¡Sobrevalorado!',3),(5,1,4,'2026-01-28 09:43:00','Joder...',NULL),(6,1,1,'2026-01-28 10:56:38','Inmortal',3),(7,2,1,'2026-01-28 10:58:12','Mola',NULL),(8,2,3,'2026-01-28 10:58:37','¡El de la foto es el de Aerosmith!',NULL),(9,1,1,'2026-01-29 10:34:49','¡Y tanto!',7),(10,2,1,'2026-01-29 10:36:51','¡Tienes razón!',7),(11,1,3,'2026-01-29 10:45:09','Una buena selección',NULL),(12,1,3,'2026-01-29 10:45:21','¡Joder, tienes razón!',8),(13,2,1,'2026-02-19 09:46:00','Otro comentario más',NULL),(14,2,1,'2026-02-19 09:46:45','Bueno bueno...',3);
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `comentarios_completos`
--

DROP TABLE IF EXISTS `comentarios_completos`;
/*!50001 DROP VIEW IF EXISTS `comentarios_completos`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `comentarios_completos` AS SELECT 
 1 AS `c_id`,
 1 AS `c_fecha`,
 1 AS `c_texto`,
 1 AS `c_padre`,
 1 AS `c_videos_id`,
 1 AS `u_id`,
 1 AS `u_nombre`,
 1 AS `u_imagen_url`,
 1 AS `respuestas`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `copia_videos_usuario`
--

DROP TABLE IF EXISTS `copia_videos_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `copia_videos_usuario` (
  `id` bigint NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text,
  `fecha` datetime DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) NOT NULL,
  `usuarios_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `videoUrl_UNIQUE` (`video_url`),
  UNIQUE KEY `imagenUrl_UNIQUE` (`imagen_url`),
  KEY `fk_videos_usuarios2_idx` (`usuarios_id`),
  CONSTRAINT `fk_videos_usuarios2` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copia_videos_usuario`
--

LOCK TABLES `copia_videos_usuario` WRITE;
/*!40000 ALTER TABLE `copia_videos_usuario` DISABLE KEYS */;
INSERT INTO `copia_videos_usuario` VALUES (1,'Michael Jackson en Auckly','Con efectos 3D de la época.\r\nComo se nota la diferencia...','2026-01-22 10:26:00','https://i.ytimg.com/vi/ChrLRauOR28/hq720.jpg','https://www.youtube.com/embed/ChrLRauOR28',1),(5,'Prueba',NULL,NULL,NULL,'https://alskdfjlkajsdf',1);
/*!40000 ALTER TABLE `copia_videos_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `numero_comentarios`
--

DROP TABLE IF EXISTS `numero_comentarios`;
/*!50001 DROP VIEW IF EXISTS `numero_comentarios`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `numero_comentarios` AS SELECT 
 1 AS `COUNT(*)`*/;
SET character_set_client = @saved_cs_client;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMINISTRADOR','Puede gestionar todos los videos'),(2,'USUARIO','Sólo puede gestionar sus propios videos'),(4,'EDITOR','Edita los videos'),(5,'SIN_DESCRIPCION','NO SE HA PROPORCIONADO'),(6,'CON_DESCRIPCION','Este tiene descripción');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `roles_BEFORE_INSERT` BEFORE INSERT ON `roles` FOR EACH ROW BEGIN
	IF NEW.nombre = 'CABRONAZO' THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No se admiten nombres con palabrotas';
    END IF;
    
    IF NEW.descripcion IS NULL THEN
		SET NEW.descripcion = 'NO SE HA PROPORCIONADO';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

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
-- Temporary view structure for view `usuarios_completos`
--

DROP TABLE IF EXISTS `usuarios_completos`;
/*!50001 DROP VIEW IF EXISTS `usuarios_completos`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `usuarios_completos` AS SELECT 
 1 AS `u_id`,
 1 AS `u_nombre`,
 1 AS `u_email`,
 1 AS `u_password`,
 1 AS `u_imagen_url`,
 1 AS `r_id`,
 1 AS `r_nombre`,
 1 AS `r_descripcion`*/;
SET character_set_client = @saved_cs_client;

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
INSERT INTO `usuarios_le_gusta_videos` VALUES (1,1),(2,1),(2,3),(1,4),(2,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'Michael Jackson en Auckly','Con efectos 3D de la época.\r\nComo se nota la diferencia...','2026-01-22 10:26:00','https://i.ytimg.com/vi/ChrLRauOR28/hq720.jpg','https://www.youtube.com/embed/ChrLRauOR28',1),(3,'Clásicos del Rock','',NULL,'https://i.ytimg.com/vi/4_O-y3SM3UM/hq720.jpg','https://www.youtube.com/embed/4_O-y3SM3UM',2),(4,'8 Bit Big Band en directo','Video de la 8 Bit Bit Band en diciembre de 2025','2026-02-19 09:49:10','https://i.ytimg.com/vi/mzjqdhWxg8M/hq720.jpg','https://www.youtube.com/embed/mzjqdhWxg8M',2),(8,'80s','','2026-01-01 00:00:00','https://i.ytimg.com/vi/1jhhb1DJjUQ/hq720.jpg','https://www.youtube.com/embed/1jhhb1DJjUQ',1);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ipartube'
--

--
-- Dumping routines for database 'ipartube'
--
/*!50003 DROP FUNCTION IF EXISTS `megusta_usuario_video` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `megusta_usuario_video`(_usuario BIGINT, _video BIGINT) RETURNS tinyint(1)
    READS SQL DATA
BEGIN

DECLARE megusta BOOLEAN;

SELECT
			            COUNT(*)
			        FROM
			            usuarios_le_gusta_videos
			        WHERE
			            usuarios_id = _usuario AND videos_id = _video INTO megusta;
RETURN megusta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `numero_megusta_video` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `numero_megusta_video`(_id BIGINT) RETURNS bigint
    READS SQL DATA
BEGIN

DECLARE total BIGINT;

SELECT
	COUNT(*)
FROM
	usuarios_le_gusta_videos
WHERE
	videos_id = _id INTO total;

RETURN total;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `sumar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `sumar`(a INTEGER, b INTEGER) RETURNS int
    DETERMINISTIC
BEGIN

RETURN a + b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `argumentos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `argumentos`(por_defecto VARCHAR(50), IN entrada VARCHAR(50), OUT salida VARCHAR(50), INOUT entrada_salida VARCHAR(50))
BEGIN

SELECT 'RECIBIDO', por_defecto, entrada, salida, entrada_salida;

SET por_defecto = '5';
SET entrada = '6';
SET salida = '7';
SET entrada_salida = '8';

SELECT 'MODIFICADO', por_defecto, entrada, salida, entrada_salida;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `comentario_insertar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `comentario_insertar`(_usuario BIGINT, _video BIGINT, _fecha DATETIME, _texto VARCHAR(1000), _padre BIGINT)
BEGIN
INSERT INTO comentarios (usuarios_id, videos_id, fecha, texto, comentario_padre_id) VALUES (_usuario,_video,_fecha,_texto,_padre);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cursores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `cursores`(_usuario BIGINT)
BEGIN

DECLARE _id BIGINT;
DECLARE _titulo VARCHAR(255);
DECLARE _descripcion TEXT;
DECLARE _fecha DATETIME;
DECLARE _imagen_url VARCHAR(255);
DECLARE _video_url VARCHAR(255);
DECLARE _usuarios_id BIGINT;

DECLARE _fin BOOLEAN;

-- con.prepareStatement("...")
DECLARE _fila CURSOR FOR SELECT id, titulo, descripcion, fecha, imagen_url, video_url, usuarios_id FROM videos WHERE usuarios_id = _usuario;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET _fin = TRUE;

SET _fin = FALSE;

-- ps.executeQuery()
OPEN _fila;

_bucle: LOOP
	-- rs.next()
	FETCH _fila INTO _id, _titulo, _descripcion, _fecha, _imagen_url, _video_url, _usuarios_id;

	IF _fin THEN
		LEAVE _bucle;
	END IF;

	SELECT _id, _titulo, _descripcion, _fecha, _imagen_url, _video_url, _usuarios_id;
    
	INSERT INTO copia_videos_usuario (id, titulo, descripcion, fecha, imagen_url, video_url, usuarios_id) VALUES (_id, _titulo, _descripcion, _fecha, _imagen_url, _video_url, _usuarios_id);

END LOOP;

-- con.close()
CLOSE _fila;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `megusta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `megusta`(_usuario BIGINT, _video BIGINT)
BEGIN
INSERT INTO usuarios_le_gusta_videos (usuarios_id, videos_id) VALUES (_usuario,_video);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nomegusta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nomegusta`(_usuario BIGINT, _video BIGINT)
BEGIN
	DELETE FROM usuarios_le_gusta_videos WHERE usuarios_id=_usuario AND videos_id=_video;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos`()
BEGIN

    SELECT 
        `v`.`id` AS `v_id`,
        `v`.`titulo` AS `v_titulo`,
        `v`.`descripcion` AS `v_descripcion`,
        `v`.`fecha` AS `v_fecha`,
        `v`.`imagen_url` AS `v_imagen_url`,
        `v`.`video_url` AS `v_video_url`,
        `u`.`id` AS `u_id`,
        `u`.`nombre` AS `u_nombre`,
        `u`.`imagen_url` AS `u_imagen_url`,
        NUMERO_MEGUSTA_VIDEO(`v`.`id`) AS `numero_me_gusta`,
        FALSE AS `me_gusta`
    FROM
        (`ipartube`.`videos` `v`
        JOIN `ipartube`.`usuarios` `u` ON ((`v`.`usuarios_id` = `u`.`id`)));

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_borrar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_borrar`(_video BIGINT)
BEGIN
delete from videos where id=_video;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_borrar_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_borrar_usuario`(_video BIGINT, _usuario BIGINT)
BEGIN
delete from videos where id=_video AND usuarios_id=_usuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_insert`(_titulo VARCHAR(255), _descripcion TEXT, _imagen_url VARCHAR(255), _fecha DATETIME, _video_url VARCHAR(255), _usuario BIGINT)
BEGIN
insert into videos (titulo, descripcion, imagen_url, fecha, video_url, usuarios_id) VALUES (_titulo,_descripcion,_imagen_url,_fecha,_video_url,_usuario);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_por_id`(_usuario BIGINT, _video BIGINT)
BEGIN

    SELECT 
        `v`.`id` AS `v_id`,
        `v`.`titulo` AS `v_titulo`,
        `v`.`descripcion` AS `v_descripcion`,
        `v`.`fecha` AS `v_fecha`,
        `v`.`imagen_url` AS `v_imagen_url`,
        `v`.`video_url` AS `v_video_url`,
        `u`.`id` AS `u_id`,
        `u`.`nombre` AS `u_nombre`,
        `u`.`imagen_url` AS `u_imagen_url`,
        NUMERO_MEGUSTA_VIDEO(`v`.`id`) AS `numero_me_gusta`,
        MEGUSTA_USUARIO_VIDEO(_usuario, `v`.`id`) AS `me_gusta`
    FROM
        (`ipartube`.`videos` `v`
        JOIN `ipartube`.`usuarios` `u` ON ((`v`.`usuarios_id` = `u`.`id`)))
	WHERE v.id=_video;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_por_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_por_usuario`(_usuario BIGINT)
BEGIN

    SELECT 
        `v`.`id` AS `v_id`,
        `v`.`titulo` AS `v_titulo`,
        `v`.`descripcion` AS `v_descripcion`,
        `v`.`fecha` AS `v_fecha`,
        `v`.`imagen_url` AS `v_imagen_url`,
        `v`.`video_url` AS `v_video_url`,
        `u`.`id` AS `u_id`,
        `u`.`nombre` AS `u_nombre`,
        `u`.`imagen_url` AS `u_imagen_url`,
        NUMERO_MEGUSTA_VIDEO(`v`.`id`) AS `numero_me_gusta`,
        MEGUSTA_USUARIO_VIDEO(_usuario, `v`.`id`) AS `me_gusta`
    FROM
        (`ipartube`.`videos` `v`
        JOIN `ipartube`.`usuarios` `u` ON ((`v`.`usuarios_id` = `u`.`id`)))
	WHERE u.id=_usuario;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_update`(_titulo VARCHAR(255), _descripcion TEXT, _imagen_url VARCHAR(255), _fecha DATETIME, _video_url VARCHAR(255), _usuario BIGINT, _video BIGINT)
BEGIN
update videos set titulo=_titulo, descripcion=_descripcion, imagen_url=_imagen_url, fecha=_fecha, video_url=_video_url, usuarios_id=_usuario where id=_video;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_update_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_update_usuario`(_titulo VARCHAR(255), _descripcion TEXT, _imagen_url VARCHAR(255), _fecha DATETIME, _video_url VARCHAR(255), _usuario BIGINT, _video BIGINT, _usuario_verificar BIGINT)
BEGIN
update videos set titulo=_titulo, descripcion=_descripcion, imagen_url=_imagen_url, fecha=_fecha, video_url=_video_url, usuarios_id=_usuario where id=_video AND usuarios_id=_usuario_verificar;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `videos_completos_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `videos_completos_usuario`(_usuario BIGINT)
BEGIN

    SELECT 
        `v`.`id` AS `v_id`,
        `v`.`titulo` AS `v_titulo`,
        `v`.`descripcion` AS `v_descripcion`,
        `v`.`fecha` AS `v_fecha`,
        `v`.`imagen_url` AS `v_imagen_url`,
        `v`.`video_url` AS `v_video_url`,
        `u`.`id` AS `u_id`,
        `u`.`nombre` AS `u_nombre`,
        `u`.`imagen_url` AS `u_imagen_url`,
        NUMERO_MEGUSTA_VIDEO(`v`.`id`) AS `numero_me_gusta`,
        MEGUSTA_USUARIO_VIDEO(_usuario, `v`.`id`) AS `me_gusta`
    FROM
        (`ipartube`.`videos` `v`
        JOIN `ipartube`.`usuarios` `u` ON ((`v`.`usuarios_id` = `u`.`id`)));

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `comentarios_completos`
--

/*!50001 DROP VIEW IF EXISTS `comentarios_completos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `comentarios_completos` AS select `c`.`id` AS `c_id`,`c`.`fecha` AS `c_fecha`,`c`.`texto` AS `c_texto`,`c`.`comentario_padre_id` AS `c_padre`,`c`.`videos_id` AS `c_videos_id`,`u`.`id` AS `u_id`,`u`.`nombre` AS `u_nombre`,`u`.`imagen_url` AS `u_imagen_url`,(select count(0) from `comentarios` where (`comentarios`.`comentario_padre_id` = `c_id`)) AS `respuestas` from (`comentarios` `c` join `usuarios` `u` on((`c`.`usuarios_id` = `u`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `numero_comentarios`
--

/*!50001 DROP VIEW IF EXISTS `numero_comentarios`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `numero_comentarios` AS select count(0) AS `COUNT(*)` from `comentarios` `c` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `usuarios_completos`
--

/*!50001 DROP VIEW IF EXISTS `usuarios_completos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `usuarios_completos` AS select `u`.`id` AS `u_id`,`u`.`nombre` AS `u_nombre`,`u`.`email` AS `u_email`,`u`.`password` AS `u_password`,`u`.`imagen_url` AS `u_imagen_url`,`r`.`id` AS `r_id`,`r`.`nombre` AS `r_nombre`,`r`.`descripcion` AS `r_descripcion` from (`usuarios` `u` join `roles` `r` on((`u`.`roles_id` = `r`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-19 10:00:33
