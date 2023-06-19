CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;
	
CREATE TABLE IF NOT EXISTS app.genres
(
    genre text NOT NULL,
    CONSTRAINT genres_pkey PRIMARY KEY (genre)
);

CREATE TABLE IF NOT EXISTS app.authors
(
    uuid uuid NOT NULL,
    name text NOT NULL,
    CONSTRAINT authors_pkey PRIMARY KEY (uuid),
    CONSTRAINT authors_name_key UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS app.readers
(
    uuid uuid NOT NULL,
    name text NOT NULL,
    dt_birth date NOT NULL,
    address text COLLATE pg_catalog."default" NOT NULL,
    dt_registration timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    CONSTRAINT readers_pkey PRIMARY KEY (uuid),
    CONSTRAINT readers_name_dt_birth_address_key UNIQUE (name, dt_birth, address)
);

CREATE TABLE IF NOT EXISTS app.books
(
    uuid uuid NOT NULL,
    title text NOT NULL,
    author_uuid uuid NOT NULL,
    genre text NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    publication_year integer NOT NULL,
    reader_uuid uuid,
    CONSTRAINT books_pkey PRIMARY KEY (uuid),
    CONSTRAINT books_author_uuid_fkey FOREIGN KEY (author_uuid)
        REFERENCES app.authors (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT books_genre_fkey FOREIGN KEY (genre)
        REFERENCES app.genres (genre) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT books_reader_uuid_fkey FOREIGN KEY (reader_uuid)
        REFERENCES app.readers (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)



