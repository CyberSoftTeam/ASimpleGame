CREATE TABLE "primary_character" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name" TEXT,
"desc" TEXT,
"status" TEXT,
"image_path" TEXT,
"score" INTEGER,
"updateTime" INTEGER,
"updateTimestamp" DATETIME DEFAULT CURRENT_TIMESTAMP
)END

CREATE TABLE "secondary_character" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name" TEXT,
"desc" TEXT,
"status" TEXT,
"image_path" TEXT,
"cumulative_score" INTEGER,
"updateTime" INTEGER,
"updateTimestamp" DATETIME DEFAULT CURRENT_TIMESTAMP
)END

CREATE TABLE "tool" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name" TEXT,
"desc" TEXT,
"status" TEXT,
"image_path" TEXT,
"price" INTEGER,
"updateTime" INTEGER,
"updateTimestamp" DATETIME DEFAULT CURRENT_TIMESTAMP
)END