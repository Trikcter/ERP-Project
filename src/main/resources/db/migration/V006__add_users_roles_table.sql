CREATE TABLE user_roles(
    user_id         bigint NOT NULL,
    role_id         bigint NOT NULL
);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES users (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES role (id);