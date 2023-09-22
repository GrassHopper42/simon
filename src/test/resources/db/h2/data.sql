INSERT INTO category (id, parent_category_id, name) VALUES (1, null, 'test');
INSERT INTO product
(category_id, price, deleted_at, id, code, description, name, standard)
VALUES (1, 1000, null, 1, 'A0000001', 'test product', 'test', '');