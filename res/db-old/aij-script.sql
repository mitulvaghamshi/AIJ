PRAGMA foreign_keys=OFF;

BEGIN TRANSACTION;

--

ALTER TABLE HelpCenter RENAME TO HelpCenter1;
DROP TABLE User;

SELECT "SETUP COMPLETE";

--

CREATE TABLE IF NOT EXISTS [User] (
    [user_id]      INTEGER PRIMARY KEY AUTOINCREMENT,
    [username]     TEXT    NOT NULL,
    [password]     TEXT    NOT NULL,
    [email]        TEXT    NULL,
    [phone]        TEXT    NULL,
    [city]         TEXT    NULL,
    [device_id]    TEXT    NULL,
    [date_created] TEXT    NULL
);

INSERT INTO [User] ([user_id], [username], [password], [email], [phone], [city], [device_id], [date_created])
VALUES (1, "mitul", "123456", "mitu@email.com", "1234567890", "Hamilton", "you-not-gonna-get-this-sorry", "1969-12-31");

SELECT "USER COMPLETE";
--

-- CREATE TABLE [MST_Zone] ([ZoneID] INTEGER  NOT NULL PRIMARY KEY,[ZoneName] VARCHAR(200)  NULL,[Sequence] INTEGER  NULL);
CREATE TABLE IF NOT EXISTS [Zone] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL
);

INSERT INTO [Zone] ([id], [name])
SELECT [ZoneID], [ZoneName] FROM [MST_Zone];

SELECT "ZONE COMPLETE";
--

-- CREATE TABLE [LOC_State] ([StateID] INTEGER PRIMARY KEY AUTOINCREMENT, [StateName] VARCHAR (100) NOT NULL, [Sequence] NUMERIC (5, 2) NOT NULL);
CREATE TABLE IF NOT EXISTS [State] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL
);

INSERT INTO [State] ([id], [name])
SELECT [StateID], [StateName] FROM [LOC_State];

SELECT "STATE COMPLETE";
--

-- CREATE TABLE [LOC_District] ([DistrictID] INTEGER NOT NULL PRIMARY KEY, [StateID] INTEGER NOT NULL CONSTRAINT [StateID] REFERENCES [LOC_State]([StateID]), [DistrictName] VARCHAR(200), [Sequence] INTEGER);

CREATE TABLE IF NOT EXISTS [District] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL,

    [state_id] INTEGER NULL,

    FOREIGN KEY([state_id]) REFERENCES [State]([id])
);

INSERT INTO [District] ([id], [name], [state_id])
SELECT [DistrictID], [DistrictName], [StateID] FROM [LOC_District];

SELECT "DISTRICT COMPLETE";
--

-- CREATE TABLE [LOC_City] ([CityID] INTEGER PRIMARY KEY, [StateID] INT REFERENCES [LOC_State]([StateID]), [DistrictID] NUMBER CONSTRAINT [DistrictID] REFERENCES [LOC_District]([DistrictID]), [CityName] [VARCHAR ](100) NOT NULL, [Sequence] [NUMERIC ](5, 2) NOT NULL);

CREATE TABLE IF NOT EXISTS [City] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [name] TEXT    NOT NULL,

    [state_id]    INTEGER NULL,
    [district_id] INTEGER NULL,

    FOREIGN KEY([state_id])    REFERENCES [State]([id]),
    FOREIGN KEY([district_id]) REFERENCES [District]([id])
);

INSERT INTO [City] ([id], [name], [state_id], [district_id])
SELECT [CityID], [CityName], [StateID], [DistrictID] FROM [LOC_City];

SELECT "CITY COMPLETE";
--

-- CREATE TABLE [MST_CollegeType] ([CollegeTypeID] INT PRIMARY KEY, [CollegeTypeName] VARCHAR (100) NOT NULL, [Sequence] NUMERIC (5, 2));

CREATE TABLE IF NOT EXISTS [CollegeType] (
    [id]   INTEGER PRIMARY KEY AUTOINCREMENT,
    [type] TEXT    NOT NULL
);

INSERT INTO [CollegeType] ([id], [type])
SELECT [CollegeTypeID], [CollegeTypeName] FROM [MST_CollegeType];

SELECT "COLLEGE TYPE COMPLETE";
--

-- CREATE TABLE [BankBranch] ([BankBranchID] INTEGER PRIMARY KEY AUTOINCREMENT, [BankBranchName] VARCHAR (200) NOT NULL, [Address] VARCHAR (300), [Zone] VARCHAR (100), [District] VARCHAR (100), [City] VARCHAR (100), [Mobile] VARCHAR (100), [Phone] VARCHAR (100), [Sequence] NUMERIC (5, 2));

CREATE TABLE IF NOT EXISTS [Bank] (
    [id]       INTEGER PRIMARY KEY AUTOINCREMENT,
    [name]     TEXT    NOT NULL,
    [address]  TEXT    NULL
);

INSERT INTO [Bank] ([id], [name], [address])
SELECT [BankBranchID], [BankBranchName], [Address] FROM [BankBranch];

SELECT "BANK COMPLETE";
--

-- CREATE TABLE [HelpCenter1] ([HelpCenterID] INT PRIMARY KEY, [HelpCenterName] VARCHAR (200) NOT NULL, [Address] VARCHAR (300), [Zone] VARCHAR (200), [District] VARCHAR (100), [City] VARCHAR (100), [Mobile] VARCHAR (100), [Phone] VARCHAR (100), [Sequence] NUMERIC (5, 2));

CREATE TABLE IF NOT EXISTS [HelpCenter] (
    [id]       INTEGER PRIMARY KEY AUTOINCREMENT,
    [name]     TEXT    NOT NULL,
    [address]  TEXT    NULL
);

INSERT INTO [HelpCenter] ([id], [name], [address])
SELECT [HelpCenterID], [HelpCenterName], [Address] FROM [HelpCenter1];

SELECT "HELP CENTER COMPLETE";
--

-- CREATE TABLE [INS_Branch] ([BranchID] INT, [DegreeID] INT CONSTRAINT [DegreeID] REFERENCES [MST_Degree]([DegreeID]), [BranchName] [VARCHAR ] NOT NULL, [BranchShortName] [VARCHAR ] NOT NULL, [BranchProperName] VARCHAR(100), [BranchCode] INT NOT NULL, [BranchAdmissionCode] VARCHAR(50), [Sequence] [NUMERIC ], CONSTRAINT [sqlite_autoindex_INS_Branch_1] PRIMARY KEY ([BranchID]));

CREATE TABLE IF NOT EXISTS [Branch] (
    [id]      INTEGER PRIMARY KEY AUTOINCREMENT,
    [code]    INTEGER NOT NULL,
    [name]    TEXT    NOT NULL,
    [acronym] TEXT    NOT NULL
);

INSERT INTO [Branch] ([id], [code], [name], [acronym])
SELECT [BranchID], [BranchCode], [BranchName], [BranchShortName] FROM [INS_Branch];

SELECT "BRANCH COMPLETE";
--

-- CREATE TABLE [MST_University] ([UniversityID] INT, [UniversityName] VARCHAR NOT NULL, [UniversityShortName] VARCHAR(500), [UniversityProperName] VARCHAR(100), [UniversityType] VARCHAR(500), [CityID] INTEGER CONSTRAINT [CityID] REFERENCES [LOC_City]([CityID]), [StateID] INTEGER CONSTRAINT [StateID] REFERENCES [LOC_State]([StateID]), [DistrictID] INTEGER CONSTRAINT [DistrictID] REFERENCES [LOC_District]([DistrictID]), [Phone] VARCHAR(100), [Mobile] VARCHAR(100), [Address] VARCHAR(1000), [Website] VARCHAR(200), [Email] VARCHAR(200), [Ratings] INTEGER, [EstdYear] VARCHAR(100), [Sequence] NUMERIC);

CREATE TABLE IF NOT EXISTS [University] (
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

INSERT INTO [University] ([id], [name], [acronym], [type], [phone], [address], [website], [email], [established], [city_id], [district_id], [state_id])
SELECT [UniversityID], [UniversityName], [UniversityShortName], [UniversityType], [Phone], [Address], [Website], [Email], [EstdYear], [CityID], [DistrictID], [StateID] FROM [MST_University];

SELECT "UNIVERSITY COMPLETE";
--

-- CREATE TABLE [INS_College] ([CollegeID] INTEGER, [CollegeTypeID] INT CONSTRAINT [CollegeTypeID] REFERENCES [MST_CollegeType]([CollegeTypeID]), [UniversityID] INT CONSTRAINT [UniversityID] REFERENCES [MST_University]([UniversityID]), [CollegeName] VARCHAR, [CollegeShortName] VARCHAR, [CollegeCode] INT, [Email] VARCHAR, [Website] VARCHAR, [Address] VARCHAR, [Hostel] VARCHAR, [CityID] INT CONSTRAINT [CityID] REFERENCES [LOC_City]([CityID]), [DistrictID] INTEGER CONSTRAINT [DistrictID] REFERENCES [LOC_District]([DistrictID]), [StateID] INT CONSTRAINT [StateID] REFERENCES [LOC_State]([StateID]), [ZoneID] INTEGER CONSTRAINT [ZoneID] REFERENCES [MST_Zone]([ZoneID]), [Phone] VARCHAR, [Mobile] VARCHAR, [Fees] VARCHAR, [CollegeAdmissionCode] INT, [Sequence] NUMERIC, [EstdYear] VARCHAR(100), [CollegeUrlName] VARCHAR(1000));

CREATE TABLE IF NOT EXISTS [College] (
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

INSERT INTO [College] ([id], [code], [name], [acronym], [phone], [email], [website], [address], [fees], [hostel], [established], [admission_code], [city_id], [district_id], [state_id], [zone_id], [university_id], [college_type_id])
SELECT [CollegeID], [CollegeCode], [CollegeName], [CollegeShortName], [Phone], [Email], [Website], [Address], [Fees], [Hostel], [EstdYear], [CollegeAdmissionCode], [CityID], [DistrictID], [StateID], [ZoneID], [UniversityID], [CollegeTypeID] FROM [INS_College];

SELECT "COLLEGE COMPLETE";
--

-- CREATE TABLE [INS_Intake]([IntakeID] INT ,[CollegeID] INT ,[BranchID] INT ,[Shift] INTEGER ,[Intake] NUMERIC NOT NULL ,[Sequence] NUMERIC ,[Vacant] INTEGER ,[Filled] INTEGER ,[IsFilled] bit ,FOREIGN KEY([CollegeID]) REFERENCES [INS_College]([CollegeID]),FOREIGN KEY([BranchID]) REFERENCES [INS_Branch]([BranchID]));

CREATE TABLE IF NOT EXISTS [CB_College_Branch] (
    [id]         INTEGER PRIMARY KEY AUTOINCREMENT,
    [college_id] INTEGER NULL,
    [branch_id]  INTEGER NULL,

    FOREIGN KEY([college_id]) REFERENCES [College]([id]),
    FOREIGN KEY([branch_id])  REFERENCES [Branch]([id])
);

INSERT INTO [CB_College_Branch] ([id], [college_id], [branch_id])
SELECT [IntakeID], [CollegeID], [BranchID] FROM [INS_Intake];

SELECT "CB COMPLETE";
--

DROP TABLE INS_Cutoff;
DROP TABLE MST_StudentGroup;
DROP TABLE INS_Intake;
DROP TABLE MST_University;
DROP TABLE LOC_City;
DROP TABLE MST_Website;
DROP TABLE LOC_District;
DROP TABLE MST_Zone;
DROP TABLE LOC_State;
DROP TABLE Registration;
DROP TABLE MF_Branch;
DROP TABLE SQL_FKDisplayMember;
DROP TABLE MF_City;
DROP TABLE Splash;
DROP TABLE MF_College;
DROP TABLE MST_Aij;
DROP TABLE HelpCenter1;
DROP TABLE BankBranch;
DROP TABLE MST_CollegeType;
DROP TABLE INS_Branch;
DROP TABLE MST_Faqs;
DROP TABLE INS_College;
DROP TABLE MST_Merit;

SELECT "DROP COMPLETE";

--

COMMIT;
