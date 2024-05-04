INSERT INTO users (name, surname, email, password)
VALUES (
           'Anita',
           'Pajic',
           'anita@mail.com',
           '$2a$12$ibzwOuR6B9h3cW1bJsnd2e0wXoZMwqA9SgYcc92l/XmdzgH8iahMS'
       );
INSERT INTO users (name, surname, email, password)
VALUES (
           'Luka',
           'Djordjevic',
           'luka@mail.com',
           '$2a$12$X36/EMUKBMS1b2PZSLWOwO1M41KGfIQHXNaA0XLFcvTOCxoNTDaku'
       );
INSERT INTO users (name, surname, email, password)
VALUES (
           'Pera',
           'Peric',
           'pera@mail.com',
           '$2a$12$rLqh7uMSTGserN/.Blk6ION.bqGVUVV.M127zL6r26spSnaip7qFm'
       );


INSERT INTO ROLE (name) VALUES ('ROLE_PATIENT');
INSERT INTO ROLE (name) VALUES ('ROLE_DOCTOR');

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 1);