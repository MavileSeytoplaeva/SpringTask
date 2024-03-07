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
ALTER TABLE subjects
    ADD CONSTRAINT FK_subjects_group_id
        FOREIGN KEY (group_id) REFERENCES groups (id);




