CREATE TABLE IF NOT EXISTS app.readers
(
    uuid uuid NOT NULL,
    name text NOT NULL,
    dt_birth date NOT NULL,
    address text NOT NULL,
    dt_registration timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    CONSTRAINT readers_pkey PRIMARY KEY (uuid),
    CONSTRAINT readers_name_dt_birth_address_key UNIQUE (name, dt_birth, address)
)