create table if not exists order_parts
(
    id       bigserial primary key,
    barcode  text    not null,
    part_num integer not null default 0,
    orderId  text    not null references orders,
    dsc      text
)