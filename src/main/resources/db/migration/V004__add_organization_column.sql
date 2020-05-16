ALTER TABLE users ADD COLUMN organization_id bigint;

ALTER TABLE users
    ADD CONSTRAINT fk_organization_id FOREIGN KEY (organization_id)
        REFERENCES organization (id);