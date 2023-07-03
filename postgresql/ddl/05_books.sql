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