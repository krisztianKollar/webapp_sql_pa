/* ***** DROP TABLES ***** */
DROP TABLE IF EXISTS insurance_bonds;
DROP TABLE IF EXISTS insurance_services;
DROP TABLE IF EXISTS insurance_companies ;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cars;


/* ***** CREATE TABLES ***** */

/*** USERS ***/

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    salary INTEGER NOT NULL,
	    CONSTRAINT email_not_empty CHECK (email <> ''),
	    CONSTRAINT positive_balance CHECK (salary >= 0)
);

/*** CARS ***/

CREATE TABLE cars (
    car_id SERIAL PRIMARY KEY,
    license_plate TEXT UNIQUE NOT NULL,
    manufacturer TEXT NOT NULL,
    model_name TEXT NOT NULL,
	    CONSTRAINT plate_not_empty CHECK (license_plate <> '')
);

/*** INSURANCE_COMPANIES ***/

CREATE TABLE insurance_companies (
    ic_id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
	    CONSTRAINT name_not_empty CHECK (name <> '')
);

/*** INSURANCE_SERVICES ***/

CREATE TABLE insurance_services (
    is_id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    min_sal_to_purch INTEGER,
    length INTEGER,
    ic_id INTEGER,
        FOREIGN KEY (ic_id) REFERENCES insurance_companies(ic_id),
        CONSTRAINT salary_not_negative CHECK (min_sal_to_purch >= 0)
);

/*** INSURANCE_BONDS ***/

CREATE TABLE insurance_bonds (
    ib_id SERIAL PRIMARY KEY,
    issued_date INTEGER,
    user_id INTEGER,
    car_id INTEGER,
    is_id INTEGER,
        FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
        FOREIGN KEY (car_id) REFERENCES cars(car_id) ON DELETE RESTRICT,
        FOREIGN KEY (is_id) REFERENCES insurance_services(is_id),
        UNIQUE (user_id, car_id, is_id)
);


/* ***** INSERTS & UPDATES ***** */

/*** ADD NEW USER  ***/
INSERT INTO users (email, salary) VALUES
    ('firstsomeone@pa.com', 5000), --1
    ('secondsomeone@pa.com', 3500), --2
    ('thirdsomeone@pa.com', 2500), --3
    ('fourthsomeone@pa.com', 2400) --4
;

/*** ADD NEW CAR  ***/
INSERT INTO cars (license_plate, manufacturer, model_name ) VALUES
    ('ABC-123', 'Citroen', 'Xsara'), --1
    ('DEF-456', 'Citroen', 'C5'), --2
    ('GHI-789', 'Dacia', 'Logen'), --3
    ('JKL-123', 'Seat', 'Cordoba'), --4
    ('MNO-456', 'Opel', 'Astra C') --5
;

/*** ADD NEW INSURANCE_COMPANY  ***/
BEGIN;
INSERT INTO insurance_companies (name) VALUES
    ('First Insurance Company'); --1
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('First Service At FIC', 3200, 1, 1); --1
COMMIT;

BEGIN;
INSERT INTO insurance_companies (name) VALUES
    ('Second Insurance Company'); --2
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('First Service At SIC', 4200, 3, 2); --2
COMMIT;

BEGIN;
INSERT INTO insurance_companies (name) VALUES
    ('Third Insurance Company'); --3
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('First Service At ThIC', 2200, 1, 3); --3
COMMIT;

/*** ADD NEW INSURANCE_SERVICE  ***/
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('Second Service at FIC', 4500, 3, 1); --4
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('Third Service at FIC', 1500, 1, 1); --5
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('Second Service at SIC', 2500, 1, 2); --6
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('Third Service at SIC', 4500, 3, 2); --7
INSERT INTO insurance_services (name, min_sal_to_purch, length, ic_id) VALUES
    ('Fourth Service at SIC', 7500, 6, 2); --8

/*** INSURE CAR ***/
INSERT INTO insurance_bonds (issued_date, user_id, car_id, is_id) VALUES
    (2014, 1, 1, 7), --1
    (2018, 3, 3, 6), --2
    (2012, 2, 5, 5), --3
    (2018, 4, 1, 5), --4
    (2015, 2, 2, 6);


/* ***** SELECTS ***** */

/*** LIST USERS ***/
SELECT email FROM users;

/*** USER DETAILS ***/
SELECT email, salary FROM users WHERE user_id = 2;

/*** LIST USERS WITH NO SALARY ***/
SELECT email FROM users WHERE salary = 0;

/*** LIST CARS ***/
SELECT manufacturer, model_name FROM cars;

/*** LIST INSURED CARS ***/
SELECT c.manufacturer, c.model_name FROM cars AS c
JOIN insurance_bonds AS ib ON c.car_id = ib.car_id
WHERE c.car_id IN (SELECT car_id FROM insurance_bonds);

/*** LIST UNINSURED CARS ***/
SELECT c.manufacturer, c.model_name FROM cars AS c
JOIN insurance_bonds AS ib ON c.car_id = ib.car_id
WHERE c.car_id NOT IN (SELECT car_id FROM insurance_bonds);

/*** LIST INSURANCE COMPANIES ***/
SELECT name FROM insurance_companies;

/*** LIST INSURANCE SERVICES ***/
SELECT name FROM insurance_services;

/*** LIST INSURANCE COMPANY SERVICES ***/
SELECT ic.name, ise.name, ise.min_sal_to_purch, ise.length FROM insurance_services AS ise
JOIN insurance_companies AS ic ON ic.ic_id = ise.ic_id
WHERE ic.name LIKE '%d Ins%';

/*** LIST INVALIDE INSURANCE BONDS ***/
SELECT ib.ib_id, ib.issued_date, ib.user_id, ib.car_id, ib.is_id FROM insurance_bonds AS ib
JOIN insurance_services AS ise ON ib.is_id = ise.is_id
WHERE ib.issued_date + ise.length < 2018;

/*** LIST VALIDE INSURANCE BONDS ***/
SELECT ib.ib_id, ib.issued_date, ib.user_id, ib.car_id, ib.is_id FROM insurance_bonds AS ib
JOIN insurance_services AS ise ON ib.is_id = ise.is_id
WHERE ib.issued_date + ise.length >= 2018;

/*** LIST NUMBER OF INSURANCE BONDS ISSUED BY COMPANIES ***/
SELECT ic.name AS company, COUNT(ib.ib_id) AS number_of_issued_bonds FROM insurance_companies AS ic
JOIN insurance_services AS ise ON ic.ic_id = ise.ic_id
JOIN insurance_bonds AS ib ON ib.is_id = ise.is_id
GROUP BY ic.name;


/* ***** DELETES ***** */

/*** DELETE EXISTING USER ***/
DELETE FROM users CASCADE WHERE user_id = 1;

/*** DELETE EXISTING CAR ***/
/*DELETE FROM cars WHERE car_id = 5; -- I can not find the right solution 
to use the restrict. All of the other delete stories will be the same, so they are missed too.*/
