CREATE DATABASE db_secretaria_trafico;

USE db_secretaria_trafico;

CREATE TABLE marcas (
   id_marca INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_marca VARCHAR(255) NOT NULL,
   PRIMARY KEY (id_marca)
 );

CREATE TABLE ciudades (
   id_ciudad INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_ciudad VARCHAR(255) NOT NULL,
   PRIMARY KEY (id_ciudad)
 );

CREATE TABLE concesionarios (
   id_concesionario INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_concesionario VARCHAR(255) NOT NULL,
   direccion_concesionario VARCHAR(255) NOT NULL,
   telefono_concesionario VARCHAR(255) NOT NULL,
   id_marca INT UNSIGNED NOT NULL,
   id_ciudad INT UNSIGNED NOT NULL,
   PRIMARY KEY (id_concesionario)
 );

CREATE TABLE tipos_personas (
   id_tipo_persona INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_tipo_persona VARCHAR(255) NOT NULL,
   PRIMARY KEY (id_tipo_persona)
 );

CREATE TABLE personas (
   id_persona INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_persona VARCHAR(255) NOT NULL,
   apellidos_persona VARCHAR(255) NOT NULL,
   fecha_nacimiento_persona DATE NOT NULL,
   direccion_persona VARCHAR(255) NOT NULL,
   telefono_persona VARCHAR(255),
   id_tipo_persona INT UNSIGNED NOT NULL,
   id_ciudad INT UNSIGNED NOT NULL,
   PRIMARY KEY (id_persona)
 );

CREATE TABLE tipos_vehiculos (
   id_tipo_vehiculo INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_tipo_vehiculo VARCHAR(255) NOT NULL,
   PRIMARY KEY (id_tipo_vehiculo)
 );

CREATE TABLE modelos (
   id_modelo INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_modelo VARCHAR(255) NOT NULL,
   anio_modelo YEAR(4) NOT NULL,
   id_marca INT UNSIGNED NOT NULL,
   PRIMARY KEY (id_modelo)
 );

CREATE TABLE autos (
   matricula CHAR(6) NOT NULL,
   color_auto VARCHAR(255),
   capacidad_auto VARCHAR(255),
   id_concesionario INT UNSIGNED NOT NULL,
   id_tipo_vehiculo INT UNSIGNED NOT NULL,
   id_modelo INT UNSIGNED NOT NULL,
   id_persona INT UNSIGNED NOT NULL,
   PRIMARY KEY (matricula)
 );

CREATE TABLE agentes (
   id_agente INT UNSIGNED NOT NULL AUTO_INCREMENT,
   nombre_agente VARCHAR(255) NOT NULL,
   apellidos_agente VARCHAR(255) NOT NULL,
   PRIMARY KEY (id_agente)
 );

CREATE TABLE infracciones (
   codigo_infraccion CHAR(3) NOT NULL,
   descripcion_infraccion VARCHAR(255) NOT NULL,
   importe_infraccion INT UNSIGNED NOT NULL,
   PRIMARY KEY (codigo_infraccion)
 );

CREATE TABLE multas (
   id_multa INT UNSIGNED NOT NULL AUTO_INCREMENT,
   fecha_infraccion DATE NOT NULL,
   direccion_infraccion VARCHAR(255) NOT NULL,
   id_ciudad INT UNSIGNED NOT NULL,
   codigo_infraccion CHAR(3) NOT NULL,
   id_persona INT UNSIGNED NOT NULL,
   id_agente INT UNSIGNED NOT NULL,
   matricula CHAR(6),
   PRIMARY KEY (id_multa)
 );

ALTER TABLE concesionarios
	ADD CONSTRAINT FK_concesionarios_marcas
		FOREIGN KEY (id_marca)
		REFERENCES marcas (id_marca),
	ADD CONSTRAINT FK_concesionarios_ciudades
		FOREIGN KEY (id_ciudad)
		REFERENCES ciudades (id_ciudad);

ALTER TABLE modelos
	ADD CONSTRAINT FK_modelos_marcas
	FOREIGN KEY (id_marca)
	REFERENCES marcas (id_marca);

ALTER TABLE personas
	ADD CONSTRAINT FK_personas_tipos_personas
		FOREIGN KEY (id_tipo_persona)
		REFERENCES tipos_personas (id_tipo_persona),
	ADD CONSTRAINT FK_personas_ciudades
		FOREIGN KEY (id_ciudad)
		REFERENCES ciudades (id_ciudad);

ALTER TABLE autos
	ADD CONSTRAINT FK_autos_concesionarios
		FOREIGN KEY (id_concesionario)
		REFERENCES concesionarios (id_concesionario),
	ADD CONSTRAINT FK_autos_modelos
		FOREIGN KEY (id_modelo)
		REFERENCES modelos (id_modelo),
	ADD CONSTRAINT FK_autos_tipos_vehiculos
		FOREIGN KEY (id_tipo_vehiculo)
		REFERENCES tipos_vehiculos (id_tipo_vehiculo),
	ADD CONSTRAINT FK_autos_personas
		FOREIGN KEY (id_persona)
		REFERENCES personas (id_persona);

ALTER TABLE multas
	ADD CONSTRAINT FK_multas_ciudades
		FOREIGN KEY (id_ciudad)
		REFERENCES ciudades (id_ciudad),
	ADD CONSTRAINT FK_multas_infracciones
		FOREIGN KEY (codigo_infraccion)
		REFERENCES infracciones (codigo_infraccion),
	ADD CONSTRAINT FK_multas_personas
		FOREIGN KEY (id_persona)
		REFERENCES personas (id_persona),
	ADD CONSTRAINT FK_multas_agentes
		FOREIGN KEY (id_agente)
		REFERENCES agentes (id_agente),
	ADD CONSTRAINT FK_multas_autos
		FOREIGN KEY (matricula)
		REFERENCES autos (matricula);
