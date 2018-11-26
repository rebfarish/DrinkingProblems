
CREATE TABLE IF NOT EXISTS `Answer` (
    `answer_id`     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `question_id`   INTEGER NOT NULL,
    `correct`       INTEGER NOT NULL,
                    FOREIGN KEY(`question_id`)
                    REFERENCES `Question`(`question_id`)
                    ON UPDATE NO ACTION ON DELETE CASCADE,
    `text` TEXT
);

CREATE  INDEX `index_Answer_question_id`
            ON `TABLE_NAME` (`question_id`);

CREATE TABLE IF NOT EXISTS `Question` (
    `question_id`   INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `randomAnswer`  INTEGER NOT NULL,
    `type`          INTEGER NOT NULL
    `text` TEXT
);
