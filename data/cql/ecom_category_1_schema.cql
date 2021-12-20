# category table
CREATE TABLE category (
    parent_id UUID,
    category_id UUID,
    name TEXT,
    image TEXT,
    products LIST<TEXT>,
PRIMARY KEY (parent_id,category_id));

# price table
CREATE TABLE price (
    product_id TEXT,
    store_id TEXT,
    value DECIMAL,
PRIMARY KEY(product_id,store_id));

# product table
CREATE TABLE product (
    product_id TEXT,
    product_group TEXT,
    name TEXT,
    brand TEXT,
    model_number TEXT,
    short_desc TEXT,
    long_desc TEXT,
    specifications MAP<TEXT,TEXT>,
    linked_documents MAP<TEXT,TEXT>,
    images SET<TEXT>,
PRIMARY KEY(product_id));

# featured product table
CREATE TABLE featured_product_groups (
    feature_id INT,
    category_id UUID,
    name TEXT,
    image TEXT,
    parent_id UUID,
    price DECIMAL,
PRIMARY KEY (feature_id,category_id));
