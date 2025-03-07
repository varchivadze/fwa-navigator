SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fw_nav
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `fw_nav`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`addresses` (
  `id` BIGINT NOT NULL,
  `street_address` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `unit` VARCHAR(50) NULL DEFAULT NULL,
  `latitude` VARCHAR(50) NULL DEFAULT NULL,
  `longitude` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fw_nav`.`edges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`edges` (
  `from_id` BIGINT NOT NULL,
  `to_id` BIGINT NOT NULL,
  `weight` DOUBLE NULL DEFAULT NULL,
  `full_path` VARCHAR(2000) NULL DEFAULT NULL,
  `busses` VARCHAR(2000) NULL DEFAULT NULL,
  INDEX `from_node` (`from_id` ASC) VISIBLE,
  INDEX `to_node` (`to_id` ASC) VISIBLE,
  CONSTRAINT `edges_ibfk_1`
    FOREIGN KEY (`from_id`)
    REFERENCES `fw_nav`.`addresses` (`id`),
  CONSTRAINT `edges_ibfk_2`
    FOREIGN KEY (`to_id`)
    REFERENCES `mydb`.`addresses` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fw_nav`.`pedestrians`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pedestrians` (
  `from_id` BIGINT NOT NULL,
  `to_id` BIGINT NOT NULL,
  `weight` DOUBLE NULL DEFAULT NULL,
  `full_path` VARCHAR(2000) NULL DEFAULT NULL,
  `busses` VARCHAR(2000) NULL DEFAULT NULL,
  INDEX `fk_pedestrians_addresses1_idx` (`to_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedestrian_addresses1`
    FOREIGN KEY (`from_id`)
    REFERENCES `fw_nav`.`addresses` (`id`),
  CONSTRAINT `fk_pedestrians_addresses1`
    FOREIGN KEY (`to_id`)
    REFERENCES `mydb`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fw_nav`.`transports`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transports` (
  `from_id` BIGINT NOT NULL,
  `to_id` BIGINT NOT NULL,
  `weight` DOUBLE NULL DEFAULT NULL,
  `full_path` VARCHAR(2000) NULL DEFAULT NULL,
  `busses` VARCHAR(2000) NULL DEFAULT NULL,
  INDEX `fk_bus_addresses1_idx` (`from_id` ASC) VISIBLE,
  INDEX `fk_bus_addresses2_idx` (`to_id` ASC) VISIBLE,
  CONSTRAINT `fk_bus_addresses1`
    FOREIGN KEY (`from_id`)
    REFERENCES `fw_nav`.`addresses` (`id`),
  CONSTRAINT `fk_bus_addresses2`
    FOREIGN KEY (`to_id`)
    REFERENCES `mydb`.`addresses` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
