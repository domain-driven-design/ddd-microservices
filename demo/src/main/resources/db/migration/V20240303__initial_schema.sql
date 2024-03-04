CREATE TABLE `calculation_transaction` (
    `id` VARCHAR(255) NOT NULL,
    `biz_type` VARCHAR(255) NOT NULL,
    `biz_id` VARCHAR(255) NOT NULL,
    `mode` VARCHAR(255) NOT NULL,
    `essential` TEXT NULL,
    `error` TEXT NULL,
    `status` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
);
