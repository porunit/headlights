create table common_types
(
    id   bigserial
        primary key,
    name varchar not null
);

create table users
(
    id       bigint not null
        primary key,
    password varchar(255),
    role     varchar(255),
    username varchar(255)
);

create table brands
(
    id   varchar not null
        primary key,
    name varchar not null
);

create table brand_models
(
    id       varchar not null
        primary key,
    brand_id varchar not null
        references brands,
    name     varchar not null
);

create table model_generation
(
    id         integer not null
        primary key,
    model_id   varchar not null
        references brand_models,
    car_period varchar not null
);

create table headlights
(
    id                    serial
        primary key,
    articul               varchar                                      not null,
    common_type_id        integer                                      not null
        constraint fk_headlights_common_type
            references common_types,
    country               varchar                                      not null,
    fb_side_type          varchar                                      not null,
    light_type            varchar                                      not null,
    lamps                 varchar                                      not null,
    corrector             varchar                                      not null,
    release_period        varchar                                      not null,
    lr_side_type          varchar                                      not null,
    sendable_by_ru_post   boolean                                      not null,
    replace_origin_number boolean                                      not null,
    pt_complection        varchar,
    pt_measure            varchar,
    tu_in_case            boolean,
    tu_mold_colour        varchar,
    tu_tuning             varchar,
    image_url             varchar,
    name                  varchar default 'defname'::character varying not null,
    price                 integer default 0                            not null,
    generation_id         integer
        constraint fk_headlights_generation
            references model_generation,
    manufacturer          varchar
);
create table bucket_items
(
    headlight_id integer not null
        constraint order_items_headlights_id_fk
            references headlights,
    count        integer not null,
    id           integer not null
        constraint order_items_pk
            primary key
);

create table orders
(
    id             integer not null
        constraint "Order_pk"
            primary key,
    date           varchar not null,
    user_id        integer
        constraint "Order_users_id_fk"
            references users,
    address        varchar not null,
    payment_method varchar
);

create table orders_items
(
    order_id bigint not null
        constraint fkij1wwgx6o198ubsx1oulpopem
            references orders,
    items_id bigint not null
        constraint uk_7qrg5pfgjon82yhgwfqrdijm5
            unique
        constraint fkol66sj9j6lm31o8rea1gw8ij0
            references bucket_items
);

CREATE SEQUENCE users_id_seq;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');

CREATE SEQUENCE model_generation_id_seq;
ALTER TABLE model_generation ALTER COLUMN id SET DEFAULT nextval('model_generation_id_seq');

INSERT INTO brands (id, name) VALUES ('audi', 'Audi');
INSERT INTO brands (id, name) VALUES ('chevrolet', 'Chevrolet');
INSERT INTO brands (id, name) VALUES ('ford', 'Ford');
INSERT INTO brands (id, name) VALUES ('honda', 'Honda');
INSERT INTO brands (id, name) VALUES ('hyundai', 'Hyundai');
INSERT INTO brands (id, name) VALUES ('kia', 'Kia');
INSERT INTO brands (id, name) VALUES ('mitsubishi', 'Mitsubishi');
INSERT INTO brands (id, name) VALUES ('nissan', 'Nissan');
INSERT INTO brands (id, name) VALUES ('opel', 'Opel');
INSERT INTO brands (id, name) VALUES ('renault', 'Renault');

INSERT INTO brand_models (id, brand_id, name) VALUES ('q5', 'audi', 'Q5');
INSERT INTO brand_models (id, brand_id, name) VALUES ('q3', 'audi', 'Q3');
INSERT INTO brand_models (id, brand_id, name) VALUES ('camaro', 'chevrolet', 'Camaro');
INSERT INTO brand_models (id, brand_id, name) VALUES ('cobalt', 'chevrolet', 'Cobalt');
INSERT INTO brand_models (id, brand_id, name) VALUES ('focus', 'ford', 'Focus');
INSERT INTO brand_models (id, brand_id, name) VALUES ('mustang', 'ford', 'Mustang');
INSERT INTO brand_models (id, brand_id, name) VALUES ('civic', 'honda', 'Civic');
INSERT INTO brand_models (id, brand_id, name) VALUES ('accord', 'honda', 'Accord');
INSERT INTO brand_models (id, brand_id, name) VALUES ('solaris', 'hyundai', 'Solaris');
INSERT INTO brand_models (id, brand_id, name) VALUES ('sonata', 'hyundai', 'Sonata');
INSERT INTO brand_models (id, brand_id, name) VALUES ('sportage', 'kia', 'Sportage');
INSERT INTO brand_models (id, brand_id, name) VALUES ('optima', 'kia', 'Optima');
INSERT INTO brand_models (id, brand_id, name) VALUES ('lancer', 'mitsubishi', 'Lancer');
INSERT INTO brand_models (id, brand_id, name) VALUES ('outlander', 'mitsubishi', 'Outlander');
INSERT INTO brand_models (id, brand_id, name) VALUES ('note', 'nissan', 'Note');
INSERT INTO brand_models (id, brand_id, name) VALUES ('skyline', 'nissan', 'Skyline');
INSERT INTO brand_models (id, brand_id, name) VALUES ('astra', 'opel', 'Astra');
INSERT INTO brand_models (id, brand_id, name) VALUES ('corsa', 'opel', 'Corsa');
INSERT INTO brand_models (id, brand_id, name) VALUES ('logan', 'renault', 'Logan');
INSERT INTO brand_models (id, brand_id, name) VALUES ('duster', 'renault', 'Duster');

INSERT INTO common_types (id, name) VALUES (1, 'COMMON');
INSERT INTO common_types (id, name) VALUES (2, 'TUNNED');
INSERT INTO common_types (id, name) VALUES (3, 'ANTIFOG');
INSERT INTO common_types (id, name) VALUES (4, 'BACKLIGHTS');

INSERT INTO model_generation (id, model_id, car_period) VALUES (1, 'q5', '2005-2007');
INSERT INTO model_generation (id, model_id, car_period) VALUES (2, 'q3', '2007-2009');
INSERT INTO model_generation (id, model_id, car_period) VALUES (3, 'camaro', '2009-2011');
INSERT INTO model_generation (id, model_id, car_period) VALUES (4, 'cobalt', '2011-2013');
INSERT INTO model_generation (id, model_id, car_period) VALUES (5, 'focus', '2013-2015');
INSERT INTO model_generation (id, model_id, car_period) VALUES (6, 'mustang', '2015-2017');
INSERT INTO model_generation (id, model_id, car_period) VALUES (7, 'civic', '2017-2019');
INSERT INTO model_generation (id, model_id, car_period) VALUES (8, 'accord', '2019-2021');
INSERT INTO model_generation (id, model_id, car_period) VALUES (9, 'solaris', '2021-2023');
INSERT INTO model_generation (id, model_id, car_period) VALUES (10, 'sonata', '2003-2005');
INSERT INTO model_generation (id, model_id, car_period) VALUES (11, 'sportage', '2005-2007');
INSERT INTO model_generation (id, model_id, car_period) VALUES (12, 'optima', '2007-2009');
INSERT INTO model_generation (id, model_id, car_period) VALUES (13, 'lancer', '2009-2011');
INSERT INTO model_generation (id, model_id, car_period) VALUES (14, 'outlander', '2011-2013');
INSERT INTO model_generation (id, model_id, car_period) VALUES (15, 'note', '2013-2015');
INSERT INTO model_generation (id, model_id, car_period) VALUES (16, 'skyline', '2015-2017');
INSERT INTO model_generation (id, model_id, car_period) VALUES (17, 'astra', '2017-2019');
INSERT INTO model_generation (id, model_id, car_period) VALUES (18, 'corsa', '2019-2021');
INSERT INTO model_generation (id, model_id, car_period) VALUES (19, 'logan', '2021-2023');
INSERT INTO model_generation (id, model_id, car_period) VALUES (20, 'duster', '2003-2005');

INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (10, 'A012', 4, 'USA', 'Left', 'Halogen', 'H17', 'Manual', '2020-2022', 'Left', true, false, 'Standard', 'Lumen', false, 'Silver', 'Classic', '/images/cat.jpg', 'Chevrolet A2', 14000, 3, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (9, 'A011', 3, 'Germany', 'Right', 'LED', 'H16', 'Auto', '2017-2019', 'Right', false, true, 'Premium', 'Lux', true, 'Black', 'Sport', '/images/cat.jpg', 'Audi A2', 16000, 1, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (14, 'A016', 4, 'South Korea', 'Left', 'Halogen', 'H21', 'Manual', '2014-2016', 'Left', true, false, 'Standard', 'Lumen', false, 'Silver', 'Classic', '/images/lojka.jpg', 'Kia A2', 13500, 12, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (7, 'A009', 1, 'Germany', 'Right', 'LED', 'H14', 'Manual', '2011-2013', 'Right', true, false, 'Standard', 'Lux', true, 'Black', 'Sport', '/images/rei.jpg', 'Opel A1', 15000, 17, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (13, 'A015', 3, 'South Korea', 'Right', 'LED', 'H20', 'Auto', '2011-2013', 'Right', false, true, 'Premium', 'Lux', true, 'Black', 'Sport', '/images/rei.jpg', 'Hyundai A2', 16500, 10, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (1, 'A003', 3, 'Germany', 'Right', 'LED', 'H8', 'Manual', '2011-2013', 'Right', true, false, 'Standard', 'Lux', true, 'Black', 'Sport', '/images/pie.jpg', 'Ford A1', 16000, 5, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (18, 'A020', 4, 'France', 'Left', 'Halogen', 'H25', 'Manual', '2008-2010', 'Left', true, false, 'Standard', 'Lumen', false, 'Silver', 'Classic', '/images/lojka.jpg', 'Renault A2', 14000, 20, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (12, 'A014', 2, 'Japan', 'Left', 'Halogen', 'H19', 'Manual', '2008-2010', 'Left', true, false, 'Standard', 'Lumen', false, 'Silver', 'Classic', '/images/rei.jpg', 'Honda A2', 12500, 8, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (3, 'A005', 1, 'South Korea', 'Right', 'LED', 'H10', 'Manual', '2017-2019', 'Right', true, true, 'Standard', 'Lux', true, 'Black', 'Sport', '/images/pen.jpg', 'Hyundai A1', 15500, 9, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (8, 'A010', 2, 'France', 'Left', 'Halogen', 'H15', 'Auto', '2014-2016', 'Left', false, true, 'Premium', 'Lumen', true, 'Silver', 'Classic', '/images/pie.jpg', 'Renault A1', 12000, 19, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (2, 'A004', 4, 'Japan', 'Left', 'Halogen', 'H9', 'Auto', '2014-2016', 'Left', false, true, 'Premium', 'Lumen', false, 'Silver', 'Classic', '/images/rei.jpg', 'Honda A1', 14000, 7, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (11, 'A013', 1, 'Germany', 'Right', 'LED', 'H18', 'Auto', '2005-2007', 'Right', false, true, 'Premium', 'Lux', true, 'Black', 'Sport', '/images/pie.jpg', 'Ford A2', 15500, 6, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (4, 'A006', 2, 'South Korea', 'Left', 'Halogen', 'H11', 'Auto', '2020-2022', 'Left', false, false, 'Premium', 'Lumen', false, 'Silver', 'Classic', '/images/pie.jpg', 'Kia A1', 12500, 11, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (6, 'A008', 4, 'Japan', 'Left', 'Halogen', 'H13', 'Auto', '2008-2010', 'Left', false, false, 'Premium', 'Lumen', false, 'Silver', 'Classic', '/images/pie.jpg', 'Nissan A1', 13500, 15, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (16, 'A018', 2, 'Japan', 'Left', 'Halogen', 'H23', 'Manual', '2020-2022', 'Left', true, false, 'Standard', 'Lumen', false, 'Silver', 'Classic', '/images/pie.jpg', 'Nissan A2', 12000, 16, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (5, 'A007', 3, 'Japan', 'Right', 'LED', 'H12', 'Manual', '2005-2007', 'Right', true, true, 'Standard', 'Lux', true, 'Black', 'Sport', '/images/pie.jpg', 'Mitsubishi A1', 16500, 13, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (15, 'A017', 1, 'Japan', 'Right', 'LED', 'H22', 'Auto', '2017-2019', 'Right', false, true, 'Premium', 'Lux', true, 'Black', 'Sport', '/images/pie.jpg', 'Mitsubishi A2', 15000, 14, 'Slavyansk');
INSERT INTO headlights (id, articul, common_type_id, country, fb_side_type, light_type, lamps, corrector, release_period, lr_side_type, sendable_by_ru_post, replace_origin_number, pt_complection, pt_measure, tu_in_case, tu_mold_colour, tu_tuning, image_url, name, price, generation_id, manufacturer) VALUES (17, 'A019', 3, 'Germany', 'Right', 'LED', 'H24', 'Auto', '2005-2007', 'Right', false, true, 'Premium', 'Lux', true, 'Black', 'Sport', '/images/pie.jpg', 'Opel A2', 16000, 18, 'Slavyansk');

CREATE FUNCTION public.set_id_as_lowercase_name() RETURNS trigger
    LANGUAGE plpgsql
AS $$
BEGIN
    NEW.id := lower(NEW.name);
    RETURN NEW;
END;
$$;
CREATE TRIGGER set_id BEFORE INSERT ON public.brand_models FOR EACH ROW EXECUTE FUNCTION public.set_id_as_lowercase_name();
CREATE TRIGGER set_id BEFORE INSERT ON public.brands FOR EACH ROW EXECUTE FUNCTION public.set_id_as_lowercase_name();