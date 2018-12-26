/*Insert Roles*/
INSERT ALL
  INTO Roles (NAME) VALUES ('User')
  INTO Roles (NAME) VALUES ('Administrator')
  INTO Roles (NAME) VALUES ('Journalist')
  INTO Roles (NAME) VALUES ('Supervisor')
SELECT * FROM dual;

/*Insert Tags*/
INSERT ALL
  INTO Tags (NAME) VALUES ('BiH')
  INTO Tags (NAME) VALUES ('Weather')
  INTO Tags (NAME) VALUES ('Brexit')
  INTO Tags (NAME) VALUES ('Cats')
  INTO Tags (NAME) VALUES ('Dogs')
  INTO Tags (NAME) VALUES ('Train')
  INTO Tags (NAME) VALUES ('Refugees')
  INTO Tags (NAME) VALUES ('Immigrants')
  INTO Tags (NAME) VALUES ('Elections')
  INTO Tags (NAME) VALUES ('Donald Trump')
  INTO Tags (NAME) VALUES ('AFD')
  INTO Tags (NAME) VALUES ('Angela Merkel')
  INTO Tags (NAME) VALUES ('Beer')
SELECT * FROM dual;

/*Insert PostTags*/
INSERT ALL
  INTO PostsTags (PostID, TagID) VALUES (7,2)
  INTO PostsTags (PostID, TagID) VALUES (6,4)
  INTO PostsTags (PostID, TagID) VALUES (6,5)
SELECT * FROM DUAL;

/*Insert postsCategories*/
INSERT ALL
  INTO PostsCategories(PostID, CategoryID) VALUES (5,1)
  INTO PostsCategories(PostID, CategoryID) VALUES (6,2)
  INTO PostsCategories(PostID, CategoryID) VALUES (7,2)
SELECT * FROM DUAL;

INSERT ALL
  INTO COMMENTS(AuthorID, PostID, Text, ReplyTo, PublishedAt) VALUES (13, 6, 'Humans are the disease of our planet!!', null, CURRENT_TIMESTAMP)
  INTO COMMENTS(AuthorID, PostID, Text, ReplyTo, PublishedAt) VALUES (14, 5, 'Grat job by the Tottenham staff.', null, CURRENT_TIMESTAMP)
  INTO COMMENTS(AuthorID, PostID, Text, ReplyTo, PublishedAt) VALUES (15, 5, 'Grat job by the staff? The lad is a luagh.', 2, CURRENT_TIMESTAMP)

/*Insert Users*/
INSERT  ALL
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('mmesihovic1', 'mmesihovic1', 'mmesihovic1@etf.unsa.ba', 7)
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('ddevic1', 'ddevic1', 'ddevic1@etf.unsa.ba', 7)
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('shrenovica1', 'shrenovica1', 'shrenovica1@etf.unsa.ba', 8)
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('tvidovic1', 'tvidovic1', 'tvidovic1@etf.unsa.ba', 9)
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('aalic6', 'aalic1', 'aalic1@etf.unsa.ba', 6)
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('aomerbegovic2', 'aomerbegovic2', 'aomerbegovic2@etf.unsa.ba', 6)
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('atucak1', 'atucak1', 'atucak1@etf.unsa.ba', 6)
  INTO Users (USERNAME, PASSWORD, MAIL, ROLEID) VALUES ('nzilic1', 'nzilic1', 'nzilic1@etf.unsa.ba', 9)
SELECT * from dual;

/*Insert Categories*/
INSERT ALL
  INTO Categories (NAME) VALUES ('Sport')
  INTO Categories (NAME) VALUES ('News')
  INTO Categories (NAME) VALUES ('Business')
  INTO Categories (NAME) VALUES ('Lifestyle')
  INTO Categories (NAME) VALUES ('Science')
  INTO Categories (NAME) VALUES ('Cars')
SELECT * FROM dual;

INSERT ALL
  INTO Posts (AUTHORID, TITLE, SUBTITLE, TEXT, COMMENTSALLOWED, HEADPICTUREID, VIDEOID, PUBLISHEDAT, EDITEDAT)
      VALUES (11,
              'Dele Alli: Tottenham midfielder signs new contract until 2024',
              'Alli signs a new deal',
              'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
              1,
              null,
              1,
              TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss'),
              TO_DATE(sysdate, 'dd/mm/yyyy hh24:mi:ss') )
  INTO Posts (AUTHORID, TITLE, SUBTITLE, TEXT, COMMENTSALLOWED, HEADPICTUREID, VIDEOID, PUBLISHEDAT, EDITEDAT)
      VALUES (11,
              'Humans blamed for mass wildlife loss',
              'WWF report: Mass wildlife loss caused by human consumption',
              'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
              1,
              null,
              1,
              TO_DATE(sysdate+6, 'dd/mm/yyyy hh24:mi:ss'),
              TO_DATE(sysdate+6, 'dd/mm/yyyy hh24:mi:ss') )
  INTO Posts (AUTHORID, TITLE, SUBTITLE, TEXT, COMMENTSALLOWED, HEADPICTUREID, VIDEOID, PUBLISHEDAT, EDITEDAT)
      VALUES (11,
              'Deadly sorm hit Italy',
              'Venice under water as deadly storms hit Italy',
              'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
              1,
              null,
              1,
              TO_DATE(sysdate+16, 'dd/mm/yyyy hh24:mi:ss'),
              TO_DATE(sysdate+16, 'dd/mm/yyyy hh24:mi:ss') )
SELECT * FROM dual;

SELECT * FROM DUAL;


COMMIT;