--liquibase formatted sql

--changeset mavile:1
create table subjects
(
    id              serial not null primary key,
    name            VARCHAR(255),
    type            VARCHAR(255),
    issue_date      DATE,
    expiration_date DATE,
    group_id        INTEGER
);

create table groups
(
    id    serial not null primary key,
    name  VARCHAR(255),
    color VARCHAR(255)
);


-- --rollback mavile:1
-- drop table subjects;
-- drop table groups;

--changeset mavile:2
INSERT INTO subjects (name, type, issue_date, expiration_date, group_id)
VALUES ('Physics', 'DOCUMENT', '2021-02-01 00:00:00', '2024-02-01 00:00:00', 2),
       ('History', 'CERTIFICATE', '2021-03-01 00:00:00', '2024-03-01 00:00:00', 3),
       ('Computer Science', 'PASSWORD', '2021-04-01 00:00:00', '2024-04-01 00:00:00', 1);

INSERT INTO subjects (name, type, issue_date, expiration_date, group_id)
VALUES ('Документ 1', 'DOCUMENT', '2021-01-01', '2024-01-15', 1),
       ('Документ 2', 'DOCUMENT', '2021-02-10', '2024-02-24', 2),
       ('Документ 3', 'DOCUMENT', '2021-03-05', '2024-03-13', 3),
       ('Документ 4', 'DOCUMENT', '2021-04-15', '2024-04-30', 2),
       ('Документ 5', 'DOCUMENT', '2021-05-20', '2024-06-05', 1);

INSERT INTO subjects (name, type, issue_date, expiration_date, group_id)
VALUES ('Сертификат поверки 1', 'METERING_DEVICE_VERIFICATION_CERTIFICATE', '2021-01-01', '2024-01-31', 3),
       ('Сертификат поверки 2', 'METERING_DEVICE_VERIFICATION_CERTIFICATE', '2021-02-10', '2024-02-28', 2),
       ('Сертификат поверки 3', 'METERING_DEVICE_VERIFICATION_CERTIFICATE', '2021-03-05', '2024-03-31', 1),
       ('Сертификат поверки 4', 'METERING_DEVICE_VERIFICATION_CERTIFICATE', '2021-04-15', '2024-04-30', 2),
       ('Сертификат поверки 5', 'METERING_DEVICE_VERIFICATION_CERTIFICATE', '2021-05-20', '2024-05-31', 1);

INSERT INTO subjects (name, type, issue_date, expiration_date, group_id)
VALUES ('Сертификат 1', 'CERTIFICATE', '2021-01-01', '2024-01-01', 3),
       ('Сертификат 2', 'CERTIFICATE', '2021-02-10', '2024-02-10', 2),
       ('Сертификат 3', 'CERTIFICATE', '2021-03-05', '2024-03-05', 1),
       ('Сертификат 4', 'CERTIFICATE', '2021-04-15', '2024-04-15', 2),
       ('Сертификат 5', 'CERTIFICATE', '2021-05-20', '2024-05-20', 1);

INSERT INTO subjects (name, type, issue_date, expiration_date, group_id)
VALUES ('Пароль 1', 'PASSWORD', '2021-01-01', '2024-01-03', 3),
       ('Пароль 2', 'PASSWORD', '2021-02-10', '2024-02-12', 2),
       ('Пароль 3', 'PASSWORD', '2021-03-05', '2024-03-07', 1),
       ('Пароль 4', 'PASSWORD', '2021-04-15', '2024-04-17', 2),
       ('Пароль 5', 'PASSWORD', '2021-05-20', '2024-05-22', 3);


INSERT INTO groups (name, color)
VALUES ('School', 'green'),
       ('University', 'red'),
       ('Work', 'blue');

-- --rollback mavile:2
-- DELETE FROM subjects WHERE type IN ('DOCUMENT', 'CERTIFICATE', 'PASSWORD');
-- DELETE FROM groups;

--changeset mavile:3
ALTER TABLE subjects
    ADD CONSTRAINT fk_subjects_group_id
        FOREIGN KEY (group_id) REFERENCES groups (id);