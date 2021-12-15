-- Populate table orders_by_user:
---------------------------------------------
INSERT INTO orders_by_user (user_id, order_timestamp, order_id, order_status, order_total)
VALUES ('joe','2020-11-17 22:20:43','113-3827060-8722206','pending',40.99);
INSERT INTO orders_by_user (user_id, order_timestamp, order_id, order_status, order_total)
VALUES ('joe','2020-10-22 10:00:28','111-0461064-1669732','delivered',63.99);
INSERT INTO orders_by_user (user_id, order_timestamp, order_id, order_status, order_total)
VALUES ('joe','2020-06-11 15:22:51','109-3421477-1773057','delivered',55.99);
INSERT INTO orders_by_user (user_id, order_timestamp, order_id, order_status, order_total)
VALUES ('joe','2019-12-01 08:37:55','104-8435452-3642550','canceled',34.99);
INSERT INTO orders_by_user (user_id, order_timestamp, order_id, order_status, order_total)
VALUES ('jen','2020-01-01 09:45:00','111-6227115-9100432','delivered',30.99);


-- Populate table orders_by_id:
---------------------------------------------
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('113-3827060-8722206','Almonds','n-0012','Almonds, 1lb',7.00,2);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('113-3827060-8722206','Hazelnuts','n-0005','Hazelnuts, 1lb',12.00,1);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('113-3827060-8722206','Pecans','n-0023','Pecans, 1lb',10.00,1);
INSERT INTO orders_by_id (order_id, order_status, order_timestamp, order_subtotal, order_shipping, order_tax, order_total, payment_summary, payment_details, billing_summary, billing_details, shipping_summary, shipping_details, delivery_id, delivery_details)
VALUES ('113-3827060-8722206','pending','2020-11-17 22:20:43',36.00,4.99,0.00,40.99,'Visa ending 1234',{'card number':'4321-0000-0000-1234','exp date':'10/24'},'111 Main St, Austin, TX',{'street':'101 Main St','city':'Austin','state':'TX'},'999 Ala Moana Blvd, Honolulu, HI',{'street':'999 Ala Moana Blvd','city':'Honolulu','state':'HI'},'fedex-2',{'service':'FedEx 2Day'});

INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('111-0461064-1669732','Almonds','n-0012','Almonds, 1lb',7.00,1);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('111-0461064-1669732','Pine nuts','n-0024','Pine nuts, 1lb',21.00,1);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('111-0461064-1669732','Pecans','n-0023','Pecans, 1lb',10.00,3);
INSERT INTO orders_by_id (order_id, order_status, order_timestamp, order_subtotal, order_shipping, order_tax, order_total, payment_summary, payment_details, billing_summary, billing_details, shipping_summary, shipping_details, delivery_id, delivery_details)
VALUES ('111-0461064-1669732','delivered','2020-10-22 10:00:28',58.00,5.99,0.00,63.99,'Visa ending 1234',{'card number':'4321-0000-0000-1234','exp date':'10/24'},'111 Main St, Austin, TX',{'street':'101 Main St','city':'Austin','state':'TX'},'111 Main St, Austin, TX',{'street':'101 Main St','city':'Austin','state':'TX'},'fedex-2',{'service':'FedEx 2Day'});

INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('109-3421477-1773057','Almonds','n-0012','Almonds, 1lb',7.00,5);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('109-3421477-1773057','Pistachios','n-0017','Pistachios, 1lb',14.00,1);
INSERT INTO orders_by_id (order_id, order_status, order_timestamp, order_subtotal, order_shipping, order_tax, order_total, payment_summary, payment_details, billing_summary, billing_details, shipping_summary, shipping_details, delivery_id, delivery_details)
VALUES ('109-3421477-1773057','delivered','2020-06-11 15:22:51',49.00,6.99,0.00,55.99,'Visa ending 1234',{'card number':'4321-0000-0000-1234','exp date':'10/24'},'111 Main St, Austin, TX',{'street':'101 Main St','city':'Austin','state':'TX'},'111 Main St, Austin, TX',{'street':'101 Main St','city':'Austin','state':'TX'},'fedex-2',{'service':'FedEx 2Day'});

INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('104-8435452-3642550','Pecans','n-0023','Pecans, 1lb',10.00,2);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('104-8435452-3642550','Cashews','n-0036','Cashews, 1lb',11.00,1);
INSERT INTO orders_by_id (order_id, order_status, order_timestamp, order_subtotal, order_shipping, order_tax, order_total, payment_summary, payment_details, billing_summary, billing_details, shipping_summary, shipping_details, delivery_id, delivery_details)
VALUES ('104-8435452-3642550','canceled','2019-12-01 08:37:55',31.00,3.99,0.00,34.99,'Visa ending 1234',{'card number':'4321-0000-0000-1234','exp date':'10/24'},'111 Main St, Austin, TX',{'street':'101 Main St','city':'Austin','state':'TX'},'111 Main St, Austin, TX',{'street':'101 Main St','city':'Austin','state':'TX'},'fedex-2',{'service':'FedEx 2Day'});

INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('111-6227115-9100432','Pecans','n-0023','Pecans, 1lb',10.00,1);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('111-6227115-9100432','Walnuts','n-0008','Walnuts, 1lb',10.00,1);
INSERT INTO orders_by_id (order_id, item_name, item_id, item_description, item_price, item_quantity)
VALUES ('111-6227115-9100432','Almonds','n-0012','Almonds, 1lb',7.00,1);
INSERT INTO orders_by_id (order_id, order_status, order_timestamp, order_subtotal, order_shipping, order_tax, order_total, payment_summary, payment_details, billing_summary, billing_details, shipping_summary, shipping_details, delivery_id, delivery_details)
VALUES ('111-6227115-9100432','delivered','2020-01-01 09:45:00',27.00,3.99,0.00,30.99,'Discover ending 5678',{'card number':'8765-0000-0000-5678','exp date':'07/26'},'222 Main Ave, New York, NY',{'street':'222 Main Ave','city':'New York','state':'NY'},'222 Main Ave, New York, NY',{'street':'222 Main Ave','city':'New York','state':'NY'},'fedex-2',{'service':'FedEx 2Day'});


-- Populate table orders_by_user_item:
---------------------------------------------
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0012','2020-11-17 22:20:43','113-3827060-8722206');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0005','2020-11-17 22:20:43','113-3827060-8722206');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0023','2020-11-17 22:20:43','113-3827060-8722206');

INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0012','2020-10-22 10:00:28','111-0461064-1669732');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0024','2020-10-22 10:00:28','111-0461064-1669732');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0023','2020-10-22 10:00:28','111-0461064-1669732');

INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0012','2020-06-11 15:22:51','109-3421477-1773057');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0017','2020-06-11 15:22:51','109-3421477-1773057');

INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0023','2019-12-01 08:37:55','104-8435452-3642550');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('joe','n-0036','2019-12-01 08:37:55','104-8435452-3642550');

INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('jen','n-0023','2020-01-01 09:45:00','111-6227115-9100432');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('jen','n-0008','2020-01-01 09:45:00','111-6227115-9100432');
INSERT INTO orders_by_user_item (user_id, item_id, order_timestamp, order_id)
VALUES ('jen','n-0012','2020-01-01 09:45:00','111-6227115-9100432');


-- Populate table order_status_history_by_id:
---------------------------------------------
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('113-3827060-8722206','2020-11-18 09:34:10','pending');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('113-3827060-8722206','2020-11-17 22:20:43','received');

INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-0461064-1669732','2020-10-25 17:39:00','delivered');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-0461064-1669732','2020-10-23 14:01:48','shipped');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-0461064-1669732','2020-10-23 06:56:31','pending');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-0461064-1669732','2020-10-22 10:00:28','received');


INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('109-3421477-1773057','2020-06-15 10:41:43','delivered');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('109-3421477-1773057','2020-06-13 08:02:35','shipped');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('109-3421477-1773057','2020-06-12 11:05:32','pending');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('109-3421477-1773057','2020-06-11 15:22:51','received');

INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('104-8435452-3642550','2019-12-01 08:42:12','canceled');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('104-8435452-3642550','2019-12-01 08:37:55','received');

INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-6227115-9100432','2020-01-05 08:12:22','delivered');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-6227115-9100432','2020-01-02 12:03:50','shipped');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-6227115-9100432','2020-01-02 07:04:24','pending');
INSERT INTO order_status_history_by_id (order_id, status_timestamp, order_status)
VALUES ('111-6227115-9100432','2020-01-01 09:45:00','received');

