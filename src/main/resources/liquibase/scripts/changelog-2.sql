--changeset mavile:3
ALTER TABLE subjects
    DROP CONSTRAINT FK_subjects_group_id;

--changeset mavile:4
ALTER TABLE subjects
    ADD CONSTRAINT FK_subjects_group_id
        FOREIGN KEY (group_id) REFERENCES groups (id)
            ON DELETE CASCADE;

--changeset mavile:5
ALTER TABLE subjects
    ADD COLUMN ext_id VARCHAR(255);

ALTER TABLE groups
    ADD COLUMN ext_id VARCHAR(255);
