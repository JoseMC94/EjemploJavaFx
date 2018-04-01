/*
Navicat MySQL Data Transfer

Source Server         : ConecionJavaFx
Source Server Version : 50718
Source Host           : localhost:3308
Source Database       : db_javafx

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-03-31 21:42:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alumno
-- ----------------------------
DROP TABLE IF EXISTS `alumno`;
CREATE TABLE `alumno` (
  `AlumnoId` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `fechaIngreso` date NOT NULL,
  `edad` int(11) NOT NULL,
  `genero` varchar(255) NOT NULL,
  `CentrosEstudiosId` int(11) NOT NULL,
  `CarreraId` int(11) NOT NULL,
  PRIMARY KEY (`AlumnoId`),
  KEY `fk_Alumno_CentrosEstudios1_idx` (`CentrosEstudiosId`),
  KEY `fk_Alumno_Carrera1_idx` (`CarreraId`),
  CONSTRAINT `fk_Alumno_Carrera1` FOREIGN KEY (`CarreraId`) REFERENCES `carrera` (`CarreraId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Alumno_CentrosEstudios1` FOREIGN KEY (`CentrosEstudiosId`) REFERENCES `centrosestudios` (`CentrosEstudiosId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alumno
-- ----------------------------
INSERT INTO `alumno` VALUES ('1', 'Jose Antonio', 'Maldonado Candela', '2018-03-14', '24', 'M', '2', '1');
INSERT INTO `alumno` VALUES ('3', 'Maria Magdalena', 'Farfan Ochatoma', '2018-03-16', '21', 'F', '1', '1');

-- ----------------------------
-- Table structure for carrera
-- ----------------------------
DROP TABLE IF EXISTS `carrera`;
CREATE TABLE `carrera` (
  `CarreraId` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_carrera` varchar(255) NOT NULL,
  `cantidad_clases` varchar(45) NOT NULL,
  PRIMARY KEY (`CarreraId`),
  UNIQUE KEY `CarreraId_UNIQUE` (`CarreraId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carrera
-- ----------------------------
INSERT INTO `carrera` VALUES ('1', 'Ing. Sistemas', '20');

-- ----------------------------
-- Table structure for centrosestudios
-- ----------------------------
DROP TABLE IF EXISTS `centrosestudios`;
CREATE TABLE `centrosestudios` (
  `CentrosEstudiosId` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_centro` varchar(255) NOT NULL,
  PRIMARY KEY (`CentrosEstudiosId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of centrosestudios
-- ----------------------------
INSERT INTO `centrosestudios` VALUES ('1', 'UNSCH');
INSERT INTO `centrosestudios` VALUES ('2', 'INDIFOR');
