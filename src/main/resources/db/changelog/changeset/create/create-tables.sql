CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table wallet
(
    wallet_id      UUID    NOT NULL DEFAULT public.uuid_generate_v4(),
    balance NUMERIC NOT NULL DEFAULT 0,
    primary key (wallet_id)
);