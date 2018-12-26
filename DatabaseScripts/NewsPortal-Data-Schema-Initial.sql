CREATE SEQUENCE Roles_ID_Seq            START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Users_ID_Seq            START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Tags_ID_Seq             START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Categories_ID_Seq       START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Videos_ID_Seq           START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Pictures_ID_Seq         START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Reports_ID_Seq          START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Comments_ID_Seq         START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE Posts_ID_Seq            START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE PostsTags_ID_Seq        START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE PostsCategories_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;

CREATE TABLE Roles (
    ID                      integer         PRIMARY KEY,
    Name                    varchar(30)     NOT NULL
);

CREATE TABLE Users (
    ID                      integer         PRIMARY KEY,
    Username                varchar(20)     NOT NULL,
    Password                varchar(100)    NOT NULL,
    Mail                    varchar(50)     NOT NULL,
    RoleID                  integer         NOT NULL,
    CONSTRAINT fk_Users_RoleID FOREIGN KEY (RoleID) REFERENCES Roles(ID)
);

CREATE TABLE Tags (
    ID                      integer         PRIMARY KEY,
    Name                    varchar(50)     NOT NULL
);

CREATE TABLE Categories (
    ID                      integer         PRIMARY KEY,
    Name                    varchar(50)     NOT NULL
);

CREATE TABLE Videos (
    ID                      integer         PRIMARY KEY,
    Link                    varchar(100)    NOT NULL
);

CREATE TABLE Pictures (
    ID                      integer         PRIMARY KEY,
    Value                   BLOB            NOT NULL,
    PostID                  integer         NOT NULL
);

CREATE TABLE Posts (
    ID                      integer         PRIMARY KEY,
    AuthorID                integer         NOT NULL,
    Title                   varchar(100)    NOT NULL,
    Subtitle                varchar(75)     NOT NULL,
    Text                    CLOB            NOT NULL,
    CommentsAllowed         number(1)         NOT NULL,
    HeadPictureID           integer         NOT NULL,
    VideoID                 integer         NOT NULL,
    PublishedAt             DATE            NOT NULL,
    EditedAt                DATE            NULL,
    CONSTRAINT  fk_Posts_AuthorID      FOREIGN KEY (AuthorID) REFERENCES Users(ID),
    CONSTRAINT  fk_Posts_HeadPictureID FOREIGN KEY (HeadPictureID) REFERENCES Pictures(ID),
    CONSTRAINT  fk_Posts_VideoID       FOREIGN KEY (VideoID) REFERENCES Videos(ID)
);

ALTER TABLE Pictures
    ADD CONSTRAINT fk_Pictures_PostID FOREIGN KEY (PostID) REFERENCES Posts(ID);

CREATE TABLE Comments (
    ID                      integer         PRIMARY KEY,
    AuthorID                integer         NOT NULL,
    PostID                  integer         NOT NULL,
    Text                    varchar(255)    NOT NULL,
    ReplyTo                 integer         NULL,
    PublishedAT             DATE            NOT NULL,
    CONSTRAINT  fk_Comments_AuthorID FOREIGN KEY (AuthorID) REFERENCES Users(ID),
    CONSTRAINT  fk_Comments_PostID   FOREIGN KEY (PostID)   REFERENCES Posts(ID),
    CONSTRAINT  fk_Comments_replyTo  FOREIGN KEY (ReplyTo)  REFERENCES Comments(ID)
);

CREATE TABLE Reports (
    ID                      integer         PRIMARY KEY,
    CommentID               integer         NOT NULL,
    Reason                  varchar(50)     NOT NULL,
    CONSTRAINT fk_Reports_CommentID FOREIGN KEY (CommentID) REFERENCES Comments(ID)
);

CREATE TABLE PostsTags (
    ID                      integer         PRIMARY KEY,
    PostID                  integer         NOT NULL,
    TagID                   integer         NOT NULL,
    CONSTRAINT fk_PostsTags_PostID  FOREIGN KEY (PostID)  REFERENCES Posts(ID),
    CONSTRAINT fk_PostsTags_TagID   FOREIGN KEY (TagID)   REFERENCES Tags(ID)
);

CREATE TABLE PostsCategories (
    ID                      integer         PRIMARY KEY,
    PostID                  integer         NOT NULL,
    CategoryID              integer         NOT NULL,
    CONSTRAINT fk_PostsCategories_PostID  FOREIGN KEY (PostID) REFERENCES Posts(ID),
    CONSTRAINT fk_PostsCategories_TagID   FOREIGN KEY (TagID)  REFERENCES Tags(ID)
);

CREATE OR REPLACE TRIGGER Roles_ID_Trigger
BEFORE INSERT ON Roles
FOR EACH ROW

BEGIN
  SELECT Roles_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Users_ID_Trigger
BEFORE INSERT ON Users
FOR EACH ROW

BEGIN
  SELECT Users_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Tags_ID_Trigger
BEFORE INSERT ON Tags
FOR EACH ROW

BEGIN
  SELECT Tags_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Categories_ID_Trigger
BEFORE INSERT ON Categories
FOR EACH ROW

BEGIN
  SELECT Categories_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Videos_ID_Trigger
BEFORE INSERT ON Videos
FOR EACH ROW

BEGIN
  SELECT Videos_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Pictures_ID_Trigger
BEFORE INSERT ON Pictures
FOR EACH ROW

BEGIN
  SELECT Pictures_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Posts_ID_Trigger
BEFORE INSERT ON Posts
FOR EACH ROW

BEGIN
  SELECT Posts_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Comments_ID_Trigger
BEFORE INSERT ON Comments
FOR EACH ROW

BEGIN
  SELECT Comments_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER Reports_ID_Trigger
BEFORE INSERT ON Reportsa
FOR EACH ROW

BEGIN
  SELECT Reports_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER PostsTags_ID_Trigger
BEFORE INSERT ON PostsTags
FOR EACH ROW

BEGIN
  SELECT PostsTags_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER PostsCategories_ID_Trigger
BEFORE INSERT ON PostsCategories
FOR EACH ROW

BEGIN
  SELECT PostsCategories_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;