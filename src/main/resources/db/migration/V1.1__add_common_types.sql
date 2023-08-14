CREATE TABLE common_types(
    id bigserial PRIMARY KEY,
    name varchar NOT NULL
);

ALTER TABLE headlights RENAME COLUMN common_type TO common_type_id;

ALTER TABLE headlights ALTER COLUMN common_type_id TYPE INTEGER USING common_type_id::integer;

ALTER TABLE headlights
    ADD CONSTRAINT fk_headlights_common_type
        FOREIGN KEY (common_type_id) REFERENCES common_types(id);


