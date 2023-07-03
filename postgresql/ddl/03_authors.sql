CREATE TABLE IF NOT EXISTS app.authors
(
    uuid uuid NOT NULL,
    name text NOT NULL,
    CONSTRAINT authors_pkey PRIMARY KEY (uuid),
    CONSTRAINT authors_name_key UNIQUE (name)
)