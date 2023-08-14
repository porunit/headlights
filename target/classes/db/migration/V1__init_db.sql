CREATE TABLE brands(
                       id serial PRIMARY KEY NOT NULL,
                       name varchar NOT NULL
);

CREATE TABLE headlights(
                           id serial PRIMARY KEY NOT NULL,
                           articul varchar NOT NULL,
                           common_type varchar NOT NULL,
                           name varchar NOT NULL,
                           price int NOT NULL,
                           country varchar NOT NULL,
                           fb_side_type varchar NOT NULL,
                           brand_id BIGINT REFERENCES brands(id) NOT NULL,
                           light_type varchar NOT NULL,
                           lamps varchar NOT NULL,
                           corrector varchar NOT NULL,
                           release_period varchar NOT NULL,
                           lr_side_type varchar NOT NULL,
                           sendable_by_ru_post boolean NOT NULL,
                           replace_origin_number boolean NOT NULL,
                           image_url varchar NOT NULL,
                           PT_complection varchar,
                           PT_measure varchar,
                           TU_in_case boolean,
                           TU_mold_colour varchar,
                           TU_tuning varchar,
);