ALTER TABLE organization ALTER COLUMN balance_id DROP NOT NULL;

ALTER TABLE product ADD COLUMN organization_id bigint;
ALTER TABLE organization ADD COLUMN owner_id bigint;

ALTER TABLE product
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (organization_id)
        REFERENCES organization (id);

ALTER TABLE organization
    ADD CONSTRAINT fk_owner_id FOREIGN KEY (owner_id)
        REFERENCES users (id);