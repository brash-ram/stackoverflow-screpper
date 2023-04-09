CREATE TABLE "links"(
    "id" BIGINT NOT NULL,
    "url" VARCHAR(255) NOT NULL,
    "chat" BIGINT NOT NULL
);
ALTER TABLE
    "links" ADD PRIMARY KEY("id");
CREATE TABLE "chats"(
    "id" BIGINT NOT NULL,
    "chat_id" BIGINT NOT NULL
);
ALTER TABLE
    "chats" ADD PRIMARY KEY("id");
ALTER TABLE
    "links" ADD CONSTRAINT "links_chat_foreign" FOREIGN KEY("chat") REFERENCES "chats"("id");