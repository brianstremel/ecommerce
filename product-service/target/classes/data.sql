INSERT INTO category (id, name) VALUES (1, 'shoes');
INSERT INTO category (id, name) VALUES (2, 'books');
INSERT INTO category (id, name) VALUES (3, 'electronics');

INSERT INTO product (id, name, description, stock,price,status, create_at,category_id)
VALUES (1, 'VANS', 'A pair of shoes', 8, 150.50, 'CREATED','2020-12-10',1);

INSERT INTO product (id, name, description, stock,price,status, create_at,category_id)
VALUES (2, 'NIKE', 'A pair of shoes', 8, 100.50, 'CREATED','2020-12-10',1);

INSERT INTO product (id, name, description, stock,price,status, create_at,category_id)
VALUES (3, 'TV', 'A TV', 18, 500, 'CREATED','2020-12-10',3);
