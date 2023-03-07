CREATE DATABASE diploma;

CREATE TABLE public."lego" (
filter text,
transform text,
deduplication text
);

CREATE TABLE public."deduplication" (
value text,
timestamp bigint
);

INSERT INTO lego(filter, transform, deduplication) VALUES ('((4 | 7) & (2 | 3))', 'name country phone', 'phone');
