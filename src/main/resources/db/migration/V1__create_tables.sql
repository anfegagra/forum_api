CREATE TABLE FORUMDB.USER(
    USER_ID NUMBER(20,0) NOT NULL,
    USERNAME VARCHAR(50),
    PASSWORD VARCHAR(255),
    ROLE VARCHAR(20),
    PRIMARY KEY (USER_ID)
);

CREATE TABLE FORUMDB.ALBUM(
    ALBUM_ID NUMBER(20,0) NOT NULL,
    TITLE VARCHAR(255),
    USER_ID NUMBER(20,0) NOT NULL,
    PRIMARY KEY (ALBUM_ID),
    CONSTRAINT USER_ID_ALBUM_FK
        FOREIGN KEY (USER_ID)
            REFERENCES FORUMDB.USER (USER_ID)
);

CREATE TABLE FORUMDB.PERMISSION(
    PERMISSION_ID NUMBER(20,0) NOT NULL,
    TYPE VARCHAR(20) NOT NULL,
    PRIMARY KEY (PERMISSION_ID)
);

CREATE TABLE FORUMDB.SHARED_ALBUM(
    ALBUM_ID NUMBER(20,0) NOT NULL,
    PERMISSION_ID NUMBER(20,0) NOT NULL,
    USER_ID NUMBER(20,0),
    PRIMARY KEY(ALBUM_ID, PERMISSION_ID),
    CONSTRAINT ALBUM_ID_FK
            FOREIGN KEY (ALBUM_ID)
                REFERENCES FORUMDB.ALBUM (ALBUM_ID),
    CONSTRAINT PERMISSION_ID_FK
        FOREIGN KEY (PERMISSION_ID)
            REFERENCES FORUMDB.PERMISSION (PERMISSION_ID)
);
