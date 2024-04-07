CREATE TABLE [User] (
    [user_id]      INTEGER PRIMARY KEY AUTOINCREMENT,
    [username]     TEXT    NOT NULL,
    [password]     TEXT    NOT NULL,
    [email]        TEXT    NULL,
    [phone]        TEXT    NULL,
    [city]         TEXT    NULL,
    [device_id]    TEXT    NULL,
    [date_created] TEXT    NULL
);
CREATE TABLE sqlite_sequence(name,seq);
CREATE TABLE [Zone] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL
);
CREATE TABLE [State] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL
);
CREATE TABLE [District] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL,

    [state_id] INTEGER NULL,

    FOREIGN KEY([state_id]) REFERENCES [State]([id])
);
CREATE TABLE [City] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL,

    [state_id]    INTEGER NULL,
    [district_id] INTEGER NULL,

    FOREIGN KEY([state_id])    REFERENCES [State]([id]),
    FOREIGN KEY([district_id]) REFERENCES [District]([id])
);
CREATE TABLE [CollegeType] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [type] TEXT    NOT NULL
);
CREATE TABLE [Branch] (
    [id]      INTEGER PRIMARY KEY AUTOINCREMENT,
    [code]    INTEGER NOT NULL,
    [name]    TEXT    NOT NULL,
    [acronym] TEXT    NOT NULL
);
CREATE TABLE [University] (
    [id]          INTEGER PRIMARY KEY AUTOINCREMENT,
    [name]        TEXT    NOT NULL,
    [acronym]     TEXT    NOT NULL,
    [type]        TEXT    NULL,
    [phone]       TEXT    NULL,
    [address]     TEXT    NULL,
    [website]     TEXT    NULL,
    [email]       TEXT    NULL,
    [established] TEXT    NULL,

    [city_id]     INTEGER NULL,
    [district_id] INTEGER NULL,
    [state_id]    INTEGER NULL,

    FOREIGN KEY([city_id])     REFERENCES [City]([id]),
    FOREIGN KEY([district_id]) REFERENCES [District]([id]),
    FOREIGN KEY([state_id])    REFERENCES [State]([id])
);
CREATE TABLE [College] (
    [id]             INTEGER PRIMARY KEY AUTOINCREMENT,
    [name]           TEXT    NOT NULL,
    [code]           TEXT    NULL,
    [acronym]        TEXT    NULL,
    [phone]          TEXT    NULL,
    [email]          TEXT    NULL,
    [website]        TEXT    NULL,
    [address]        TEXT    NULL,
    [fees]           TEXT    NULL,
    [hostel]         TEXT    NULL,
    [established]    TEXT    NULL,
    [admission_code] INTEGER NULL,

    [city_id]     INTEGER NULL,
    [district_id] INTEGER NULL,
    [state_id]    INTEGER NULL,
    [zone_id]     INTEGER NULL,

    [university_id]   INTEGER NULL,
    [college_type_id] INTEGER NULL,

    FOREIGN KEY([city_id])     REFERENCES [City]([id]),
    FOREIGN KEY([district_id]) REFERENCES [District]([id]),
    FOREIGN KEY([state_id])    REFERENCES [State]([id]),
    FOREIGN KEY([zone_id])     REFERENCES [Zone]([id]),

    FOREIGN KEY([university_id])   REFERENCES [University]([id]),
    FOREIGN KEY([college_type_id]) REFERENCES [CollegeType]([id])
);
CREATE TABLE [BankBranch] (
    [id]       INTEGER PRIMARY KEY AUTOINCREMENT,
    [name]     TEXT    NOT NULL,
    [city]     TEXT    NOT NULL,
    [address]  TEXT    NULL
);
CREATE TABLE [HelpCenter] (
    [id]       INTEGER PRIMARY KEY AUTOINCREMENT,
    [name]     TEXT    NOT NULL,
    [city]     TEXT    NOT NULL,
    [address]  TEXT    NULL
);
CREATE TABLE [CB_College_Branch] (
    [id]         INTEGER PRIMARY KEY AUTOINCREMENT,
    [college_id] INTEGER NULL,
    [branch_id]  INTEGER NULL,

    FOREIGN KEY([college_id]) REFERENCES [College]([id]),
    FOREIGN KEY([branch_id])  REFERENCES [Branch]([id])
);
CREATE INDEX CB_College_Branch_idx_college_by_branch ON CB_College_Branch(branch_id);
CREATE INDEX CB_College_Branch_idx_branch_by_college ON CB_College_Branch(college_id);
