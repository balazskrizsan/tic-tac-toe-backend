CREATE TABLE "game"
(
    "id"          BIGSERIAL  NOT NULL,
    "starer_user" VARCHAR(1) NOT NULL,
    "created_at"  TIMESTAMP  NOT NULL,
    CONSTRAINT "game_pk" PRIMARY KEY ("id")
) WITH (OIDS = FALSE);
