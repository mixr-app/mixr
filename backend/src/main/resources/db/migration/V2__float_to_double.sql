
use mixr;

ALTER TABLE `mixr`.`recipe_ingredients`
CHANGE COLUMN `amount` `amount` DOUBLE NULL DEFAULT NULL ;