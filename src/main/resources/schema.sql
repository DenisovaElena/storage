DROP TABLE IF EXISTS box CASCADE;
CREATE TABLE box(
    id INTEGER NOT NULL,
    contained_in INTEGER
);
ALTER TABLE box ADD CONSTRAINT constraint_box_pk PRIMARY KEY(id);

DROP TABLE IF EXISTS item CASCADE;
CREATE TABLE item(
    id INTEGER NOT NULL,
    contained_in INTEGER,
    color VARCHAR(100)
);
ALTER TABLE item ADD CONSTRAINT constraint_item_pk PRIMARY KEY(id);
ALTER TABLE item ADD CONSTRAINT constraint_item_box_id FOREIGN KEY(contained_in) REFERENCES box(id);