
CREATE TABLE Users (
    ID                      integer         PRIMARY KEY,
    Username                varchar(20)     NOT NULL,
    Password                varchar(100)    NOT NULL,
    Mail                    varchar(50)     NOT NULL,
    RoleID                  integer         NOT NULL,
    CONSTRAINT fk_Users_RoleID FOREIGN KEY (RoleID) REFERENCES Roles(ID)
);

CREATE TABLE KorisniciMirza (
    ID                      integer         PRIMARY KEY,
    FirstName               varchar(20)     NOT NULL,
    LastName                varchar(30)     NOT NULL,
    Mail                    varchar(50)     NOT NULL
);

CREATE TABLE FajloviMirza (
    ID                      integer         PRIMARY KEY,
    Name                    varchar(100)    NOT NULL,
    Content                 BLOB            NOT NULL,
    KorisnikID              integer         NOT NULL,
    CONSTRAINT fk_FajloviMirza FOREIGN KEY (KorisnikID) REFERENCES KorisniciMirza(ID)
);

CREATE INDEX ix_FajloviMirza_Name ON FajloviMirza(Name);

CREATE OR REPLACE TRIGGER FajloviMirza_Name_Trigger
BEFORE INSERT ON FajloviMirza
FOR EACH ROW
DECLARE
  broj INTEGER;
BEGIN
  SELECT COUNT(*) INTO broj
      FROM FajloviMirza fm
      WHERE fm.ID = :new.KorisnikID;
  IF ( broj > 0 ) THEN
    RAISE_APPLICATION_ERROR(-20343, 'Navedeni fajl vec postoji!');
  END IF;
END;

INSERT INTO KorisniciMirza VALUES(1, 'Mirza', 'Mesihovic', 'mmesihovic1@etf.unsa.ba');

commit;

INSERT INTO FajloviMirza VALUES (1,'proba.txt', '0', 1);

commit;

select * from FajloviMirza;

CREATE TABLE KorisniciTin (
    ID                      integer         PRIMARY KEY,
    FirstName               varchar(20)     NOT NULL,
    LastName                varchar(30)     NOT NULL,
    Mail                    varchar(50)     NOT NULL
);

CREATE TABLE FajloviTin (
    ID                      integer         PRIMARY KEY,
    Name                    varchar(100)    NOT NULL,
    Content                 BLOB            NOT NULL,
    KorisnikID              integer         NOT NULL,
    CONSTRAINT fk_FajloviTin FOREIGN KEY (KorisnikID) REFERENCES KorisniciTin(ID)
);

CREATE INDEX ix_FajloviTin_Name ON FajloviTin(Name);

CREATE OR REPLACE TRIGGER FajloviTin_Name_Trigger
BEFORE INSERT ON FajloviTin
FOR EACH ROW
DECLARE
  broj INTEGER;
BEGIN
  SELECT COUNT(*) INTO broj
      FROM FajloviTin ft
      WHERE ft.ID = :new.KorisnikID;
  IF ( broj > 0 ) THEN
    RAISE_APPLICATION_ERROR(-20343, 'Navedeni fajl vec postoji!');
  END IF;
END;

CREATE TABLE KorisniciSanja (
    ID                      integer         PRIMARY KEY,
    FirstName               varchar(20)     NOT NULL,
    LastName                varchar(30)     NOT NULL,
    Mail                    varchar(50)     NOT NULL
);

CREATE TABLE FajloviSanja (
    ID                      integer         PRIMARY KEY,
    Name                    varchar(100)    NOT NULL,
    Content                 BLOB            NOT NULL,
    KorisnikID              integer         NOT NULL,
    CONSTRAINT fk_FajloviSanja FOREIGN KEY (KorisnikID) REFERENCES KorisniciSanja(ID)
);

CREATE INDEX ix_FajloviSanja_Name ON FajloviSanja(Name);

CREATE OR REPLACE TRIGGER FajloviSanja_Name_Trigger
BEFORE INSERT ON FajloviSanja
FOR EACH ROW
DECLARE
  broj INTEGER;
BEGIN
  SELECT COUNT(*) INTO broj
      FROM FajloviSanja fs
      WHERE fs.ID = :new.KorisnikID AND fs.Name = :new.Name;
  IF ( broj > 0 ) THEN
    RAISE_APPLICATION_ERROR(-20343, 'Navedeni fajl vec postoji!');
  END IF;
END;

CREATE TABLE KorisniciDelila (
    ID                      integer         PRIMARY KEY,
    FirstName               varchar(20)     NOT NULL,
    LastName                varchar(30)     NOT NULL,
    Mail                    varchar(50)     NOT NULL
);

CREATE TABLE FajloviDelila (
    ID                      integer         PRIMARY KEY,
    Name                    varchar(100)    NOT NULL,
    Content                 BLOB            NOT NULL,
    KorisnikID              integer         NOT NULL,
    CONSTRAINT fk_FajloviDelila FOREIGN KEY (KorisnikID) REFERENCES KorisniciDelila(ID)
);

CREATE INDEX ix_FajloviDelila_Name ON FajloviDelila(Name);

CREATE OR REPLACE TRIGGER FajloviDelila_Name_Trigger
BEFORE INSERT ON FajloviDelila
FOR EACH ROW
DECLARE
  broj INTEGER;
BEGIN
  SELECT COUNT(*) INTO broj
      FROM FajloviDelila fd
      WHERE fd.ID = :new.KorisnikID;
  IF ( broj > 0 ) THEN
    RAISE_APPLICATION_ERROR(-20343, 'Navedeni fajl vec postoji!');
  END IF;
END;


COMMIT;

select * from KorisniciSanja;
select * from fajloviSanja;

CREATE SEQUENCE KorisniciSanja_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE KorisniciDelila_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE KorisniciMirza_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE KorisniciTin_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE FajloviMirza_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE FajloviSanja_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE FajloviDelila_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;
CREATE SEQUENCE FajloviTin_ID_Seq  START WITH 1 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE NOCACHE NOCYCLE;

CREATE OR REPLACE TRIGGER KorisniciSanja_ID_Trigger
BEFORE INSERT ON KorisniciSanja
FOR EACH ROW

BEGIN
  SELECT KorisniciSanja_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER KorisniciDelila_ID_Trigger
BEFORE INSERT ON KorisniciDelila
FOR EACH ROW

BEGIN
  SELECT KorisniciDelila_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER KorisniciMirza_ID_Trigger
BEFORE INSERT ON KorisniciMirza
FOR EACH ROW

BEGIN
  SELECT KorisniciMirza_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER KorisniciTin_ID_Trigger
BEFORE INSERT ON KorisniciTin
FOR EACH ROW

BEGIN
  SELECT KorisniciTin_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER FajloviSanja_ID_Trigger
BEFORE INSERT ON FajloviSanja
FOR EACH ROW

BEGIN
  SELECT FajloviSanja_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER FajloviDelila_ID_Trigger
BEFORE INSERT ON FajloviDelila
FOR EACH ROW

BEGIN
  SELECT FajloviDelila_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

  CREATE OR REPLACE TRIGGER FajloviMirza_ID_Trigger
BEFORE INSERT ON FajloviMirza
FOR EACH ROW

BEGIN
  SELECT FajloviMirza_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;

CREATE OR REPLACE TRIGGER FajloviTin_ID_Trigger
BEFORE INSERT ON FajloviTin
FOR EACH ROW

BEGIN
  SELECT FajloviTin_ID_Seq.NEXTVAL
  INTO   :new.ID
  FROM   dual;
END;
