-- MySQL Script generated by MySQL Workbench
-- Thu Mar  9 17:13:07 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema emall
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema emall
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `emall` DEFAULT CHARACTER SET utf8 ;
USE `emall` ;

-- -----------------------------------------------------
-- Table `emall`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emall`.`product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type` SMALLINT(6) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `summary` VARCHAR(128) NOT NULL,
  `description` VARCHAR(512) NOT NULL,
  `price` MEDIUMINT(9) NOT NULL,
  `images` VARCHAR(512) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changed_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
