CREATE TABLE IF NOT EXISTS warehouse_operation
(
    id                 bigserial PRIMARY KEY,
    warehouse_id       bigint NOT NULL,
    product_id         bigint NOT NULL,
    type_id            bigint NOT NULL,
    count              bigint NOT NULL,
    date_of_operation  date NOT NULL
);

ALTER TABLE warehouse_operation
    ADD CONSTRAINT fk_type_id FOREIGN KEY (type_id)
        REFERENCES warehouse_type_operation (id);

ALTER TABLE warehouse_operation
    ADD CONSTRAINT fk_warehouse_id FOREIGN KEY (warehouse_id)
        REFERENCES warehouse (id);

ALTER TABLE warehouse_operation
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES product (id);

ALTER TABLE bank_operation ADD COLUMN date_of_operation date NOT NULL;
