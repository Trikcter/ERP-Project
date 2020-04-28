CREATE TABLE IF NOT EXISTS role
(
    id                 bigserial PRIMARY KEY,
    name               varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id                 bigserial PRIMARY KEY,
    login              varchar(255) NOT NULL,
    password           varchar(255) NOT NULL,
    first_name         varchar(255) NOT NULL,
    second_name        varchar(255) NOT NULL,
    surname            varchar(255) NOT NULL,
    is_deleted         boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS organization
(
    id                 bigserial PRIMARY KEY,
    inn                varchar(255) NOT NULL,
    kpp                varchar(255) NOT NULL,
    ogrn               varchar(255) NOT NULL,
    title              varchar(255) NOT NULL,
    balance_id         bigint NOT NULL,
    is_deleted         boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS balance
(
    id                 bigserial PRIMARY KEY,
    organization_id    bigint NOT NULL,
    all_balance        decimal NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id                 bigserial PRIMARY KEY,
    title              varchar(255) NOT NULL,
    code               varchar(255),
    price              decimal NOT NULL,
    is_deleted         boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS address
(
    id                 bigserial PRIMARY KEY,
    title              varchar(255) NOT NULL,
    is_deleted         boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS warehouse
(
    id                 bigserial PRIMARY KEY,
    title              varchar(255) NOT NULL,
    address_id         bigint NOT NULL,
    organization_id    bigint NOT NULL,
    is_deleted         boolean NOT NULL DEFAULT false
);

CREATE TABLE IF NOT EXISTS warehouse_condition
(
    id                 bigserial PRIMARY KEY,
    count              bigint NOT NULL,
    product_id         bigint NOT NULL,
    warehouse_id       bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS type_operation
(
    id                 bigserial PRIMARY KEY,
    name               varchar(255) NOT NULL,
    description        varchar(255)
);

CREATE TABLE IF NOT EXISTS bank_operation
(
    id                 bigserial PRIMARY KEY,
    balance_id         bigint NOT NULL,
    type_id            bigint NOT NULL,
    comment            varchar(255),
    sum                decimal NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    id                 bigserial PRIMARY KEY,
    balance_id         bigint NOT NULL,
    product_id         bigint NOT NULL,
    count              bigint NOT NULL
);

ALTER TABLE organization
    ADD CONSTRAINT fk_balance_id FOREIGN KEY (balance_id)
        REFERENCES balance (id);

ALTER TABLE balance
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (organization_id)
        REFERENCES organization (id);

ALTER TABLE warehouse
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (organization_id)
        REFERENCES organization (id);

ALTER TABLE warehouse
    ADD CONSTRAINT fk_address_id FOREIGN KEY (address_id)
        REFERENCES address (id);

ALTER TABLE warehouse_condition
    ADD CONSTRAINT fk_warehouse_id FOREIGN KEY (warehouse_id)
        REFERENCES warehouse (id);

ALTER TABLE warehouse_condition
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES product (id);

ALTER TABLE bank_operation
    ADD CONSTRAINT fk_type_id FOREIGN KEY (type_id)
        REFERENCES type_operation (id);

ALTER TABLE bank_operation
    ADD CONSTRAINT fk_balance_id FOREIGN KEY (balance_id)
        REFERENCES balance (id);

ALTER TABLE orders
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES product (id);

ALTER TABLE orders
    ADD CONSTRAINT fk_balance_id FOREIGN KEY (balance_id)
        REFERENCES balance (id);