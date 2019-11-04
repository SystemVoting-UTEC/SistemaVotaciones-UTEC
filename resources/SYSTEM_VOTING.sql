CREATE DATABASE SYSTEM_VOTING;
USE SYSTEM_VOTING;


CREATE TABLE IF NOT EXISTS DEPARTAMENTO(
	DEP_ID INT NOT NULL AUTO_INCREMENT,
	DEP_NOMBRE VARCHAR(200),
	CONSTRAINT DEPARTAMENTO_PK PRIMARY KEY(DEP_ID)
 ); 
 
 CREATE TABLE IF NOT EXISTS MUNICIPIO(
	MUN_ID INT NOT NULL AUTO_INCREMENT,
	MUN_NOMBRE VARCHAR(200),
	MUN_DEP_ID INT NOT NULL,
	CONSTRAINT MUNICIPIO_PK PRIMARY KEY(MUN_ID),
	CONSTRAINT MUNICIPIO_DEPARTAMENTO_FK FOREIGN KEY (MUN_DEP_ID) REFERENCES DEPARTAMENTO (DEP_ID)
	
 ); 
 
CREATE TABLE IF NOT EXISTS ESTADO_FAMILIAR(
	EST_ID INT NOT NULL AUTO_INCREMENT,
	EST_ESTADO VARCHAR(200),
	CONSTRAINT ESTADO_FAMILIAR_PK PRIMARY KEY(EST_ID)
 );

CREATE TABLE IF NOT EXISTS GENERO(
	GEN_ID INT NOT NULL AUTO_INCREMENT,
	GEN_GENERO VARCHAR(1),
	CONSTRAINT GENERO_PK PRIMARY KEY(GEN_ID)
 );

 CREATE TABLE IF NOT EXISTS PARTIDO(
	PAR_ID INT NOT NULL AUTO_INCREMENT,
	PAR_NOMBRE VARCHAR(1),
	CONSTRAINT PARTIDO_PK PRIMARY KEY(PAR_ID)
 );
 
CREATE TABLE IF NOT EXISTS TIPO_USUARIO(
	TUS_ID INT NOT NULL AUTO_INCREMENT,
	TUS_TIPO VARCHAR(100),
	CONSTRAINT TIPO_USUARIO_PK PRIMARY KEY(TUS_ID)
 );
 
 CREATE TABLE IF NOT EXISTS PERSONA(
	PER_DUI VARCHAR(10) NOT NULL,
	PER_P_NOMBRE VARCHAR(200),
	PER_S_NOMBRE VARCHAR(200),
	PER_T_NOMBRE VARCHAR(200),
	PER_P_APELLIDO VARCHAR(200),
	PER_S_APELLIDO VARCHAR(200),
	PER_FECHA_NAC DATE,
	PER_EDAD DATE,
	PER_GEN_ID INT NOT NULL,
	PER_DEP_ID INT NOT NULL,
	PER_EST_ID INT NOT NULL,
	PER_MADRE VARCHAR(200),
	PER_PADRE VARCHAR(200),
	CONSTRAINT PERSONA_PK PRIMARY KEY (PER_DUI),
	CONSTRAINT PERSONA_GENERO_FK FOREIGN KEY (PER_GEN_ID) REFERENCES GENERO (GEN_ID),
	CONSTRAINT PERSONA_DEPARTAMENTO_FK FOREIGN KEY (PER_DEP_ID) REFERENCES DEPARTAMENTO (DEP_ID),
	CONSTRAINT PERSONA_ESTADO_FK FOREIGN KEY (PER_EST_ID) REFERENCES ESTADO_FAMILIAR (EST_ID)
 );
 
CREATE TABLE IF NOT EXISTS VOTANTE(
	VOT_PER_DUI VARCHAR(10) NOT NULL,
	VOT_FECHA_VENCE DATE,
	VOT_FECHA_EXP DATE,
	CONSTRAINT VOTANTE_PERSONA_FK FOREIGN KEY (VOT_PER_DUI) REFERENCES PERSONA (PER_DUI)
 );
 
 CREATE TABLE IF NOT EXISTS TIPO_CANDIDATO(
	TCA_ID INT NOT NULL AUTO_INCREMENT,
	TCA_TIPO VARCHAR(100),
	CONSTRAINT TIPO_CANDIDATO_PK PRIMARY KEY(TCA_ID)
 );
 
 CREATE TABLE IF NOT EXISTS CANDIDATO(
	CAN_ID INT NOT NULL AUTO_INCREMENT,
	CAN_PER_DUI VARCHAR(10) NOT NULL,
	CAN_PAR_ID INT NOT NULL,
	CAN_DEP_ID INT NOT NULL,
	CAN_TCA_ID INT NOT NULL,
	CONSTRAINT CANDIDATO_PK PRIMARY KEY (CAN_ID),
	CONSTRAINT VOTANTE_CANDIDATO_FK FOREIGN KEY (CAN_PER_DUI) REFERENCES PERSONA (PER_DUI),
	CONSTRAINT VOTANTE_PARTIDO_FK FOREIGN KEY (CAN_PAR_ID) REFERENCES PARTIDO (PAR_ID),
	CONSTRAINT CANDIDATO_DEPARTAMENTO_FK FOREIGN KEY (CAN_DEP_ID) REFERENCES DEPARTAMENTO (DEP_ID),
	CONSTRAINT CANDIDATO_TIPO_FK FOREIGN KEY (CAN_TCA_ID) REFERENCES TIPO_CANDIDATO (TCA_ID)
 );
 
 CREATE TABLE IF NOT EXISTS USUARIO(
	US_PER_DUI VARCHAR(10) NOT NULL,
	US_PASSWORD VARCHAR(300) NOT NULL,
	US_TUS_ID INT NOT NULL,
	CONSTRAINT USUARIO_PERSONA_FK FOREIGN KEY (US_PER_DUI) REFERENCES PERSONA (PER_DUI),
	CONSTRAINT USUARIO_TIPO_FK FOREIGN KEY (US_TUS_ID) REFERENCES TIPO_USUARIO (TUS_ID)
 );
 
 CREATE TABLE IF NOT EXISTS SUFRAGIO(
	SUF_ID INT NOT NULL AUTO_INCREMENT,
	SUF_PER_DUI VARCHAR(10) NOT NULL,
	SUF_CAN_ID INT,
	SUF_SUFRAGIO INT NOT NULL,
	CONSTRAINT SUFRAGIO_PK PRIMARY KEY (SUF_ID),
	CONSTRAINT SUFRAGIO_PERSONA_FK FOREIGN KEY (SUF_PER_DUI) REFERENCES VOTANTE (VOT_PER_DUI),
	CONSTRAINT SUFRAGIO_CANDIDATO_FK FOREIGN KEY (SUF_CAN_ID) REFERENCES CANDIDATO (CAN_ID)
 );
 
 CREATE TABLE OPTION_MENU(
	OPT_ID INTEGER NOT NULL AUTO_INCREMENT,
	OPT_NOMBRE	VARCHAR(150) NOT NULL,
	OPT_URL		VARCHAR(400) NOT NULL,
	CONSTRAINT OPTIONMENU_PK PRIMARY KEY(OPT_ID)
);

CREATE TABLE OPTION_TIPO_USUATIO (
	OPT_ID INTEGER NOT NULL,
	TUS_ID INTEGER NOT NULL
	CONSTRAINT OPTION_OPT_FK FOREIGN KEY (OPT_ID) REFERENCES OPTION_MENU(OPT_ID),
	CONSTRAINT OPTION_TUS_FK FOREIGN KEY (TUS_ID) REFERENCES TIPO_USUARIO(TUS_ID)
);

CREATE TABLE ELECCION(
	ELC_ID INT AUTO_INCREMNET NOT NULL,
	ELC_DESCRIPCION VARCHAR(500),
	ELC_FECHA_INICIO DATE NOT NULL,
	ELC_FECHA_FIN DATE NOT NULL,
	ELC_ESTADO INT NOT NULL,
	CONSTRAINTS ELECCION_PK PRIMARY KEY(ELC_ID)
);

CREATE TABLE CANDIDATO_ELECCION(
	CEL_ID INT AUTO_INCREMNET NOT NULL,
	ELC_ID INT NOT NULL,
	CAN_ID INT NOT NULL,
	CONSTRAINTS CANDIDATO_ELECCION_PK PRIMARY KEY (CEL_ID),
	CONSTRAINTS CANDIDATO_ELECCION_ELC_FK FOREIGN KEY (ELC_ID) REFERENCES ELECCION (ELC_ID),
	CONSTRAINTS CANDIDATO_ELECCION_CAN_FK FOREIGN KEY (CAN_ID) REFERENCES CANDIDATO (CAN_ID)
);