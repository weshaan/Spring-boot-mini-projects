INSERT INTO categories (name, description, created_at, updated_at)
VALUES ('Electronics', 'Electronic items', NOW(), NOW());

INSERT INTO products (name, sku, price, category_id, created_at, updated_at)
VALUES ('Laptop', 'SKU-LAP-001', 75000, 1, NOW(), NOW());

INSERT INTO inventory (product_id, quantity, reorder_level, created_at, updated_at)
VALUES (1, 10, 10, NOW(), NOW());
