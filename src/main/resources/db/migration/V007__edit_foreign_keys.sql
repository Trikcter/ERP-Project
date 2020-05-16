ALTER TABLE organization
    ADD COLUMN address_id bigint NOT NULL;

ALTER TABLE organization
    ADD CONSTRAINT fk_address_id FOREIGN KEY (address_id)
        REFERENCES address (id);

ALTER TABLE organization
    DROP CONSTRAINT fk_balance_id;

ALTER TABLE balance
    DROP CONSTRAINT fk_organization_id;

ALTER TABLE organization
    ADD CONSTRAINT fk_balance_id FOREIGN KEY (balance_id)
        REFERENCES balance (id) ON DELETE CASCADE;
