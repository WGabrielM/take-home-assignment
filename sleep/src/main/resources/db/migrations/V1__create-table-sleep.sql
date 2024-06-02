CREATE TABLE sleep {
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    timeBed TIMESTAMP NOT NULL,
    timeWake TIMESTAMP NOT NULL,
    status character varying(5) check (status in ('GOOD','OK', 'BAD' ) ) NOT NULL,
}