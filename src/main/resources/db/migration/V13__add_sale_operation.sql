ALTER TABLE warehouse_type_operation ALTER COLUMN organization_id DROP NOT NULL;
INSERT INTO warehouse_type_operation(id, name, description, is_deleted) VALUES (1, 'Продажа', 'Продажа товара', false);