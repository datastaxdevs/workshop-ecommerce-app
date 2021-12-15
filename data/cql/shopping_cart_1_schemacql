
CREATE TABLE items_by_id (
    id text PRIMARY KEY,
    description text,
    name text,
    price decimal
);

CREATE TABLE carts_by_user (
    user_id 	text,
    cart_name 	text,
    cart_id 	uuid,
    user_email 	text static,
    cart_is_active boolean,
    PRIMARY KEY (user_id, cart_name, cart_id)
) WITH CLUSTERING ORDER BY (cart_name ASC, cart_id ASC);

CREATE TABLE items_by_cart (
    cart_id 			uuid,
    timestamp 			timestamp,
    item_id 			text,
    cart_subtotal   	decimal static,
    item_description 	text,
    item_name 			text,
    item_price 			decimal,
    quantity 			int,
    PRIMARY KEY (cart_id, timestamp, item_id)
) WITH CLUSTERING ORDER BY (timestamp DESC, item_id ASC);


