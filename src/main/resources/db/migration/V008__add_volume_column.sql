ALTER TABLE warehouse
    ADD COLUMN volume bigint NOT NULL;

ALTER TABLE role
    ADD COLUMN description varchar(255);

UPDATE role SET description = 'Администратор системы' WHERE id = 1;
UPDATE role SET description = 'Пользователь системы' WHERE id = 2;
UPDATE role SET description = 'Генеральный директор' WHERE id = 3;
UPDATE role SET description = 'Финансовый директор' WHERE id = 5;
UPDATE role SET description = 'Работник' WHERE id = 6;
UPDATE role SET description = 'Работник кадрового отдела' WHERE id = 7;
UPDATE role SET description = 'Продавец' WHERE id = 8;
UPDATE role SET description = 'Кладовщик' WHERE id = 9;

ALTER TABLE role
    ALTER COLUMN description SET NOT NULL;