-- I'll let Max set up flyway for us, it's far too complicated for me.  Until then, we'll just run this fancy sql script to keep our database up-to-date!


use mixr;

select * from recipes;
select * from ingredients;
select * from recipe_ingredients;

DELETE FROM recipe_ingredients WHERE recipe_id = 16;
DELETE FROM recipes WHERE id = 16;

select * from schema_version;

ALTER TABLE `mixr`.`recipe_ingredients` 
CHANGE COLUMN `amount` `amount` DOUBLE NULL DEFAULT NULL ;