create table if not exists orders
(
    id          text primary key,
    client      text    not null,
    items_count integer not null default 0,
    cell_id     bigint references cells,
    dsc         text
)