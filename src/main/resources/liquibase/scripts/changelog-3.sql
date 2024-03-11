--changeset mavile: 6
CREATE TABLE users (
    id              serial not null primary key,
    email           VARCHAR(255) UNIQUE,
    name            VARCHAR(255),
    password        VARCHAR(255)
);

--changeset mavile: 7
ALTER TABLE subjects
    ADD COLUMN user_id INTEGER,
    ADD CONSTRAINT FK_subjects_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE;

--changeset mavile: 8
CREATE TABLE user_subjects (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INT,
    subject_id INT,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

