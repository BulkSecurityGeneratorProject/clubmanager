CREATE TABLE `member_yearly_dues` (
	`id` BIGINT UNSIGNED NOT NULL,
	`points` FLOAT UNSIGNED NOT NULL,
	`amount_due` FLOAT UNSIGNED NOT NULL,
	`year` INT UNSIGNED NOT NULL,
	`member_id` BIGINT UNSIGNED NOT NULL
);