-- MySQL Script generated by MySQL Workbench
-- Wed Feb 22 16:46:08 2017
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
-- Table `emall`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `emall`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `display_id` VARCHAR(64) NOT NULL,
  `display_name` VARCHAR(32) NOT NULL,
  `gender` TINYINT(4) UNSIGNED NOT NULL,
  `avatar` VARCHAR(128) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `email` VARCHAR(128) NOT NULL,
  `mobile_phone` VARCHAR(32) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changed_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_display_id` (`display_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
