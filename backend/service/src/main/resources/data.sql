
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

INSERT INTO medicines (name) VALUES ('Pen-Vee K');
INSERT INTO medicines (name) VALUES ('Erythrocin');
INSERT INTO medicines (name) VALUES ('E-Mycin');

INSERT INTO medicines (name) VALUES ('Zithromax');
INSERT INTO medicines (name) VALUES ('Levaquin');
INSERT INTO medicines (name) VALUES ('Amoxil');
INSERT INTO medicines (name) VALUES ('Vibramycin');

INSERT INTO medicines (name) VALUES ('Nydrazid');
INSERT INTO medicines (name) VALUES ('Rifadin');
INSERT INTO medicines (name) VALUES ('Tebrazid');
INSERT INTO medicines (name) VALUES ('Myambutol');

INSERT INTO medicines (name) VALUES ('Cipro');
INSERT INTO medicines (name) VALUES ('Rocephin');

INSERT INTO medicines (name) VALUES ('Deltasone');
INSERT INTO medicines (name) VALUES ('Decadron');
INSERT INTO medicines (name) VALUES ('Medrol');
INSERT INTO medicines (name) VALUES ('Rocephin');
INSERT INTO medicines (name) VALUES ('Claforan');

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

-- INGREDIENTS --
INSERT INTO ingredients (name) VALUES ('Penicilin V');
INSERT INTO ingredients (name) VALUES ('Laktoza');
INSERT INTO ingredients (name) VALUES ('Povidon');

INSERT INTO ingredients (name) VALUES ('Eritromicin');

INSERT INTO ingredients (name) VALUES ('Mikroceluloza');
INSERT INTO ingredients (name) VALUES ('Hipromeloza');

INSERT INTO ingredients (name) VALUES ('Azitromicin');
INSERT INTO ingredients (name) VALUES ('Kukuruzni skrob');
INSERT INTO ingredients (name) VALUES ('Titanijum dioksid');

INSERT INTO ingredients (name) VALUES ('Levofloxacin');
INSERT INTO ingredients (name) VALUES ('Krospovidon');
INSERT INTO ingredients (name) VALUES ('Hidroksipropilmetilceluloza');

INSERT INTO ingredients (name) VALUES ('Amoksicilin');
INSERT INTO ingredients (name) VALUES ('Magnezijum stearat');

INSERT INTO ingredients (name) VALUES ('Doksiciklin');

INSERT INTO ingredients (name) VALUES ('Rifampicin');

INSERT INTO ingredients (name) VALUES ('Pirazinamid');

INSERT INTO ingredients (name) VALUES ('Etambutol');

INSERT INTO ingredients (name) VALUES ('Ciprofloksacin');

INSERT INTO ingredients (name) VALUES ('Ceftriakson');
INSERT INTO ingredients (name) VALUES ('Natrijum hidroksid');
INSERT INTO ingredients (name) VALUES ('Limunska kiselina');

INSERT INTO ingredients (name) VALUES ('Prednizon');
INSERT INTO ingredients (name) VALUES ('Kalcijum stearat');

INSERT INTO ingredients (name) VALUES ('Deksametazon');
INSERT INTO ingredients (name) VALUES ('Skrob');

INSERT INTO ingredients (name) VALUES ('Metilprednizolon');

INSERT INTO ingredients (name) VALUES ('Ceftriakson');

INSERT INTO ingredients (name) VALUES ('Cefotaksim');

INSERT INTO ingredients (name) VALUES ('Isoniazid');



-- MEDICINE <-> INGREDIENT --
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (1, 1);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (1, 2);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (1, 3);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (2, 4);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (2, 2);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (2, 3);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (3, 4);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (3, 5);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (3, 6);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (4, 7);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (4, 8);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (4, 9);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (5, 10);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (5, 11);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (5, 12);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (6, 13);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (6, 14);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (6, 9);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (7, 15);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (7, 2);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (7, 14);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (8, 30);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (8, 2);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (9, 16);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (9, 8);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (9, 14);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (10, 17);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (10, 5);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (10, 14);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (11, 18);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (11, 5);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (11, 3);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (12, 19);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (12, 5);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (13, 20);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (13, 21);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (13, 22);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (14, 23);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (14, 24);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (14, 2);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (15, 25);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (15, 26);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (16, 27);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (16, 3);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (17, 28);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (17, 22);

INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (18, 29);
INSERT INTO medicine_ingredient (medicine_id, ingredient_id) VALUES (18, 22);













































