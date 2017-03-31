-- MySQL Script generated by MySQL Workbench
-- Fri Mar 31 10:30:33 2017
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
-- Table `emall`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emall`.`cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` VARCHAR(128) NOT NULL,
  `items` TEXT NULL,
  `created_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `changed_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_customer` (`customer_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
