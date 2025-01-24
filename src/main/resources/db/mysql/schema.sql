CREATE TABLE IF NOT EXISTS vets (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS specialties (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS vet_specialties (
  vet_id INT(4) UNSIGNED NOT NULL,
  specialty_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (vet_id) REFERENCES vets(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  UNIQUE (vet_id,specialty_id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS types (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS owners (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pets (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  birth_date DATE,
  type_id INT(4) UNSIGNED NOT NULL,
  owner_id INT(4) UNSIGNED,
  INDEX(name),
  FOREIGN KEY (owner_id) REFERENCES owners(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS visits (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pet_id INT(4) UNSIGNED,
  visit_date DATETIME,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS collar
(
  collar_message VARCHAR(255),
  serial_id      INT(4) UNSIGNED NOT NULL,
  pet_id         INT(4) UNSIGNED NOT NULL,
  CONSTRAINT pk_collar PRIMARY KEY (serial_id, pet_id),
  CONSTRAINT uk_serial UNIQUE (serial_id)
);

ALTER TABLE collar
  ADD CONSTRAINT FK_COLLAR_ON_PET FOREIGN KEY (pet_id) REFERENCES pets (id);

CREATE TABLE airtag
(
  tag_id           BINARY(16)   NOT NULL,
  `description`    VARCHAR(255) NOT NULL,
  collar_serial_id INT(4) UNSIGNED       NULL,
  collar_pet_id    INT(4) UNSIGNED       NULL,
  CONSTRAINT pk_airtag PRIMARY KEY (tag_id)
);

ALTER TABLE airtag
  ADD CONSTRAINT uc_airtag_collar_pet UNIQUE (collar_pet_id);

ALTER TABLE airtag
  ADD CONSTRAINT FK_AIRTAG_ON_COSECOPEID FOREIGN KEY (collar_serial_id, collar_pet_id) REFERENCES collar (serial_id, pet_id);
