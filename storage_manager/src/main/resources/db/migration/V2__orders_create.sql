create table if not exists orders
(
    id         text primary key,
    client     text    not null,
    item_count integer not null default 0,
    cell_id    bigint  not null references cells,
    dsc        text
)