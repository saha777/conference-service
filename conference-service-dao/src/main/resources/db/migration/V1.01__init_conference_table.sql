CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS conference
(
    id                  UUID              NOT NULL PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    name                VARCHAR           NOT NULL UNIQUE,
    subject             VARCHAR           NOT NULL,
    participant_count   INT            NOT NULL CHECK ( participant_count > 100 )
);

CREATE TABLE IF NOT EXISTS conference_date
(
    id            UUID NOT NULL PRIMARY KEY DEFAULT public.uuid_generate_v4(),
    date          DATE NOT NULL UNIQUE,
    conference_id UUID NOT NULL REFERENCES conference (id)
);
