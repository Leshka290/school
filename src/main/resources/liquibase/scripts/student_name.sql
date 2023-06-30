--liquibase formatted sql
--changeset alex:2
create index student_name_index on student (name);
