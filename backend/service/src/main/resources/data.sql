
-- USERS --
INSERT INTO users (name, surname, email, password) VALUES (
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

-- PATIENTS --
INSERT INTO patients (id, birth_date, weight) VALUES (1, '2001-09-25 00:00:00.0', 52.0);
INSERT INTO patients (id, birth_date, weight) VALUES (3, '2000-05-21 00:00:00.0', 85.0);

-- SYMPTOMS --
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Bol u grlu', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Poteskoce pri gutanju', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Povisena temperatura', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Belicaste tacke na krajnicima', 'LEVEL_2');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Crvenilo grla', 'LEVEL_2');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Bolni limfni cvorovi', 'LEVEL_2');

INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Kasalj', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Zimica', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Bolovi u grudima', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Otezano disanje', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Krckanje u plucima', 'LEVEL_2');

INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Hronican kasalj sa krvavom pljuvackom', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Gubitak telesne tezine', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Nocno znojenje', 'LEVEL_1');

INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Dijareja', 'LEVEL_2');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Bolovi u stomaku', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Povracanje', 'LEVEL_1');

INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Jaka glavobolja', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Pojava osipa', 'LEVEL_1');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Ukocenost vrata', 'LEVEL_2');
INSERT INTO SYMPTOMS (name, symptom_level) VALUES ('Zbunjenost ili promene u ponasanju', 'LEVEL_2');

-- DISEASES --
INSERT INTO diseases (name, test_type) VALUES ('Streptokokna upala grla', 'RAPID_STREP_TEST');
INSERT INTO diseases (name, test_type) VALUES ('Bakterijska pneumonija', 'CHEST_X_RAY');
INSERT INTO diseases (name, test_type) VALUES ('Tuberkuloza', 'SPUTUM_TEST');
INSERT INTO diseases (name, test_type) VALUES ('Salmoneloza', 'STOOL_CULTURE');
INSERT INTO diseases (name, test_type) VALUES ('Meningitis', 'HEAD_MRI');

-- DISEASES <-> SYMPTOMS --
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (1, 1);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (1, 2);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (1, 3);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (1, 4);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (1, 5);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (1, 6);

INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (2, 3);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (2, 7);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (2, 8);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (2, 9);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (2, 10);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (2, 11);

INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (3, 3);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (3, 6);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (3, 11);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (3, 12);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (3, 13);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (3, 14);

INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (4, 3);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (4, 15);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (4, 16);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (4, 17);

INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (5, 3);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (5, 17);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (5, 18);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (5, 19);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (5, 20);
INSERT INTO disease_symptom (disease_id, symptom_id) VALUES (5, 21);


-- MEDICINES --
INSERT INTO medicines (name) VALUES ('Penicilin');
INSERT INTO medicines (name) VALUES ('Eritromicin');
INSERT INTO medicines (name) VALUES ('Brufen');

INSERT INTO medicines (name) VALUES ('Azitromicin');
INSERT INTO medicines (name) VALUES ('Levofloksacin');
INSERT INTO medicines (name) VALUES ('Amoksicilin');
INSERT INTO medicines (name) VALUES ('Doksiciklin');

INSERT INTO medicines (name) VALUES ('Izonijazid');
INSERT INTO medicines (name) VALUES ('Rifampicin');
INSERT INTO medicines (name) VALUES ('Pirazinamid');
INSERT INTO medicines (name) VALUES ('Etambutol');

INSERT INTO medicines (name) VALUES ('Ciprofloksacin');
INSERT INTO medicines (name) VALUES ('Trimetoprim-sulfametoksazol');

INSERT INTO medicines (name) VALUES ('Prednizon');
INSERT INTO medicines (name) VALUES ('Deksametazon');
INSERT INTO medicines (name) VALUES ('Metilprednizolon');
INSERT INTO medicines (name) VALUES ('Ceftriakson');
INSERT INTO medicines (name) VALUES ('Cefotaksin');

-- DISEASE <-> MEDICINE --
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (1, 1);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (1, 2);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (1, 3);

INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (2, 4);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (2, 5);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (2, 6);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (2, 7);

INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (3, 8);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (3, 9);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (3, 10);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (3, 11);

INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (4, 12);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (4, 13);

INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (5, 14);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (5, 15);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (5, 16);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (5, 17);
INSERT INTO disease_medicine (disease_id, medicine_id) VALUES (5, 18);


