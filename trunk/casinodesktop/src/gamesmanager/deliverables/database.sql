-- Table: clientes

-- DROP TABLE clientes;

CREATE TABLE clientes
(
  direccionid integer NOT NULL,
  credito numeric(15,2) NOT NULL,
  nombres character varying(100) NOT NULL,
  appaterno character varying(100) NOT NULL,
  apmaterno character varying(100),
  sexo character(1) NOT NULL,
  fechanac date NOT NULL,
  foto bytea NOT NULL,
  fechareg date NOT NULL,
  telcasa character varying(100),
  telcel character varying(100) NOT NULL,
  clienteid character varying(100) NOT NULL,
  CONSTRAINT clientes_pkey PRIMARY KEY (clienteid),
  CONSTRAINT clientes_direccionid_fkey FOREIGN KEY (direccionid)
      REFERENCES direcciones (direccionid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE clientes OWNER TO casindesktopapp;

-- Table: clientesexternos

-- DROP TABLE clientesexternos;

CREATE TABLE clientesexternos
(
  casinoid integer NOT NULL,
  clienteid character varying(100) NOT NULL,
  CONSTRAINT clientesexternos_pkey PRIMARY KEY (casinoid, clienteid)
)
WITH (OIDS=FALSE);
ALTER TABLE clientesexternos OWNER TO casindesktopapp;

-- Table: direcciones

-- DROP TABLE direcciones;

CREATE TABLE direcciones
(
  direccionid integer NOT NULL,
  callenum character varying(200) NOT NULL,
  numint character varying(100),
  colonia character varying(200) NOT NULL,
  municipio character varying(200) NOT NULL,
  codigopostal character varying(10) NOT NULL,
  estado character varying(200) NOT NULL,
  pais character varying(200) NOT NULL,
  CONSTRAINT pkdirecciones PRIMARY KEY (direccionid)
)
WITH (OIDS=FALSE);
ALTER TABLE direcciones OWNER TO casindesktopapp;

-- Table: empleados

-- DROP TABLE empleados;

CREATE TABLE empleados
(
  direccionid integer NOT NULL,
  nombres character varying(100) NOT NULL,
  appaterno character varying(100) NOT NULL,
  apmaterno character varying(100),
  sexo character(1) NOT NULL,
  fechanac date NOT NULL,
  foto bytea NOT NULL,
  usuarioid integer NOT NULL,
  tipoempleadoid integer NOT NULL,
  telcasa character varying(100),
  telcel character varying(100) NOT NULL,
  empleadoid character varying(100) NOT NULL,
  CONSTRAINT empleados_pkey PRIMARY KEY (empleadoid),
  CONSTRAINT empleados_direccionid_fkey FOREIGN KEY (direccionid)
      REFERENCES direcciones (direccionid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT empleados_tipoempleadoid_fkey FOREIGN KEY (tipoempleadoid)
      REFERENCES tipoempleados (tipoempleadoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT empleados_usuarioid_fkey FOREIGN KEY (usuarioid)
      REFERENCES usuarios (usuarioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE empleados OWNER TO casindesktopapp;

-- Table: mesas

-- DROP TABLE mesas;

CREATE TABLE mesas
(
  mesaid integer NOT NULL,
  tipojuegoid integer NOT NULL,
  CONSTRAINT mesas_pkey PRIMARY KEY (mesaid)
)
WITH (OIDS=FALSE);
ALTER TABLE mesas OWNER TO casindesktopapp;

-- Table: sesiones

-- DROP TABLE sesiones;

CREATE TABLE sesiones
(
  sesionid integer NOT NULL,
  mesaid integer NOT NULL,
  fecha date NOT NULL,
  horaapertura time with time zone NOT NULL,
  horacierre time with time zone,
  fichasinicio integer NOT NULL,
  fichasfin integer,
  empleadoid character varying(100) NOT NULL,
  CONSTRAINT sesiones_pkey PRIMARY KEY (sesionid),
  CONSTRAINT sesiones_empleadoid_fkey FOREIGN KEY (empleadoid)
      REFERENCES empleados (empleadoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT sesiones_mesaid_fkey FOREIGN KEY (mesaid)
      REFERENCES mesas (mesaid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE sesiones OWNER TO casindesktopapp;

-- Table: tipoempleados

-- DROP TABLE tipoempleados;

CREATE TABLE tipoempleados
(
  tipoempleadoid integer NOT NULL,
  tipo character(100) NOT NULL,
  CONSTRAINT tipoempleados_pkey PRIMARY KEY (tipoempleadoid)
)
WITH (OIDS=FALSE);
ALTER TABLE tipoempleados OWNER TO casindesktopapp;

-- Table: tiposjuego

-- DROP TABLE tiposjuego;

CREATE TABLE tiposjuego
(
  tipojuegoid integer NOT NULL,
  tipo character varying(100) NOT NULL,
  CONSTRAINT tiposjuego_pkey PRIMARY KEY (tipojuegoid)
)
WITH (OIDS=FALSE);
ALTER TABLE tiposjuego OWNER TO casindesktopapp;

-- Table: usuarios

-- DROP TABLE usuarios;

CREATE TABLE usuarios
(
  usuarioid integer NOT NULL,
  usuario character varying(100) NOT NULL,
  "password" character varying(100) NOT NULL,
  nivel integer NOT NULL,
  CONSTRAINT usuarios_pkey PRIMARY KEY (usuarioid)
)
WITH (OIDS=FALSE);
ALTER TABLE usuarios OWNER TO casindesktopapp;
