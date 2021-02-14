CREATE TABLE "game_state"
(
    "id"         BIGSERIAL  NOT NULL,
    "game_id"    BIGINT     NOT NULL,
    "place"      SMALLINT   NOT NULL,
    "playerId"   VARCHAR(1) NOT NULL,
    "created_at" TIMESTAMP  NOT NULL,
    CONSTRAINT "game_state_pk" PRIMARY KEY ("id")
) WITH (OIDS = FALSE);

ALTER TABLE "game_state"
    ADD CONSTRAINT "fk__game_state_game_id__game_id__on_delete_cascade"
        FOREIGN KEY ("game_id")
            REFERENCES "game" ("id") ON DELETE CASCADE;
