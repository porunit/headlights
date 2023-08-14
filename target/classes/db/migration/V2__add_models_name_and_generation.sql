CREATE TABLE brand_models
(
    id       serial PRIMARY KEY,
    brand_id BIGINT  NOT NULL REFERENCES brand_models (id),
    name     varchar NOT NULL
);

CREATE TABLE model_generation
(
    id       serial PRIMARY KEY,
    model_id BIGINT  NOT NULL REFERENCES brand_models (id),
    period   varchar NOT NULL
);

ALTER TABLE headlights
    ADD COLUMN generation_id BIGINT;
ALTER TABLE headlights
    ADD CONSTRAINT fk_headlights_model_id
        FOREIGN KEY (generation_id) REFERENCES model_generation (id);