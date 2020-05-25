ALTER TABLE type_operation RENAME TO financial_type_operation;

ALTER TABLE financial_type_operation ADD COLUMN organization_id bigint NOT NULL;
ALTER TABLE financial_type_operation ADD COLUMN is_deleted boolean NOT NULL DEFAULT false;

ALTER TABLE financial_type_operation
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (organization_id)
        REFERENCES organization (id);

CREATE TABLE IF NOT EXISTS warehouse_type_operation
(
    id                 bigserial PRIMARY KEY,
    name               varchar(255) NOT NULL,
    description        varchar(255),
    is_deleted         boolean NOT NULL DEFAULT false,
    organization_id    bigint NOT NULL
);

ALTER TABLE warehouse_type_operation
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (organization_id)
        REFERENCES organization (id);
