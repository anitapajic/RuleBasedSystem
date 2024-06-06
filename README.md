# Sistem za dijagnozu bakterijskih infekcija i odabir terapije

## Članovi tima:
- Anita Pajić - SV24/2020
- Luka Đorđević - SV14/2020

## Kratak opis projekta
Lekar beleži opažene simptome pacijenta u elektronski sistem. Tokom procesa
dijagnostike, lekar ima mogućnost da odabere nekoliko različitih opcija:
- Da pokrene rezoner koji će mu izbaciti najverovatniju bolest
- Da lično uspostavi dijagnozu
- Nakon pronađene bolesti, lekar može pokrenuti rezoner koji će mu izbaciti
pogodnu terapiju

Takođe, aplikacija pruža mogućnost monitoringa pacijenata.

## Ovaj sistem se sastoji iz tri glavne komponente:

1. **MySQL baza podataka**
2. **Backend aplikacija** koja se sastoji iz tri dela:
    1. **Model** - Maven aplikacija koja služi kao dependency za Kjar i Service aplikacije.
    2. **Kjar** - Maven aplikacija koja sadrži Drools pravila i koristi Model kao dependency.
    3. **Service** - Glavna Spring Boot aplikacija koja se pokreće kao server na portu 8080 i koristi Model i Kjar kao dependency.
3. **Frontend aplikacija** izrađena u React-u.

## Komponente i kako se pokreću

### 1. MySQL baza podataka

MySQL baza podataka `rbsdb` sa korisničkim imenom `root` i lozinkom `root123`.

#### Pokretanje MySQL baze
1. Instalirati MySQL server.
2. Kreirati bazu podataka sa sledećom komandom:
    ```sql
    CREATE DATABASE rbsdb;
    ```
3. Kreirati korisnika i dodeliti privilegije:
    ```sql
    CREATE USER 'root'@'localhost' IDENTIFIED BY 'root123';
    GRANT ALL PRIVILEGES ON rbsdb.* TO 'root'@'localhost';
    ```

### 2. Backend aplikacija

Backend aplikacija se sastoji iz tri dela:

#### 2.1 Model

Model je Maven aplikacija koja definiše entitete i služi kao dependency za Kjar i Service aplikacije.

##### Pokretanje Model aplikacije
1. Navigirati do direktorijuma `model`.
2. Izvršiti sledeću komandu:
    ```bash
    mvn clean install
    ```

#### 2.2 Kjar

Kjar je Maven aplikacija koja sadrži Drools pravila i koristi Model kao dependency.

##### Pokretanje Kjar aplikacije
1. Navigirati do direktorijuma `kjar`.
2. Izvršiti sledeću komandu:
    ```bash
    mvn clean install
    ```

#### 2.3 Service

Service je glavna Spring Boot aplikacija koja se pokreće kao server na portu 8080 i koristi Model i Kjar kao dependency.

##### Pokretanje Service aplikacije
1. Navigirati do direktorijuma `service`.
2. Izvršiti sledeću komandu:
    ```bash
    mvn spring-boot:run
    ```

### 3. Frontend aplikacija

Frontend aplikacija je izrađena u Reactu i koristi Vite za razvojno okruženje.

##### Pokretanje Frontend aplikacije
1. Navigirati do direktorijuma `frontend`.
2. Instalirati Mobiscroll CLI:
    ```bash
    npm install -g @mobiscroll/cli
    ```
3. Konfigurisati Mobiscroll
    ```bash
    mobiscroll config react
    ```
4. Registrovati se na mobiscroll i uneti odgovarajuće kredencijale
5. Instalirati potrebne dependency-e:
    ```bash
    npm install
    ```
6. Pokrenuti aplikaciju:
    ```bash
    npm run dev
    ```

Nakon što su sve komponente pokrenute, sistem će biti dostupan na http://localhost:8080 za backend i http://localhost:5137 za frontend aplikaciju.

