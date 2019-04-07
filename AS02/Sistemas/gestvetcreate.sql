CREATE DATABASE "GestVet"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
CREATE SCHEMA public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO postgres;

GRANT ALL ON SCHEMA public TO PUBLIC;

CREATE TABLE public."ANIMAL"
(
    id integer NOT NULL DEFAULT nextval('"ANIMAL_id_seq"'::regclass),
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    raca character varying(25) COLLATE pg_catalog."default" NOT NULL,
    idade integer NOT NULL,
    tipo character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "ANIMAL_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ANIMAL"
    OWNER to postgres;
	
CREATE TABLE public."MEMBRO"
(
    id integer NOT NULL DEFAULT nextval('"MEMBRO_id_seq"'::regclass),
    "user" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(20) COLLATE pg_catalog."default" NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    crmv integer,
    CONSTRAINT "MEMBRO_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."MEMBRO"
    OWNER to postgres;
COMMENT ON TABLE public."MEMBRO"
    IS 'Membros do sistema';
	
CREATE TABLE public."MEDICAMENTO"
(
    id integer NOT NULL DEFAULT nextval('"MEDICAMENTO_id_seq"'::regclass),
    nome character varying(255) COLLATE pg_catalog."default" NOT NULL,
    preco double precision NOT NULL,
    dosagem character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT "MEDICAMENTO_pkey" PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."MEDICAMENTO"
    OWNER to postgres;
	
CREATE TABLE public."CONSULTA"
(
    veterinario integer NOT NULL,
    animal integer NOT NULL,
    data_marcada date NOT NULL,
    registro text COLLATE pg_catalog."default",
    realizado boolean NOT NULL DEFAULT false,
    retorno boolean NOT NULL DEFAULT false,
    CONSTRAINT "CONSULTA_pkey" PRIMARY KEY (veterinario, animal, data_marcada),
    CONSTRAINT id_animal FOREIGN KEY (animal)
        REFERENCES public."ANIMAL" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_veterinario FOREIGN KEY (veterinario)
        REFERENCES public."MEMBRO" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."CONSULTA"
    OWNER to postgres;
	
CREATE TABLE public."ESTOQUE"
(
    id_medicamento integer NOT NULL,
    quantidade integer NOT NULL,
    CONSTRAINT "ESTOQUE_pkey" PRIMARY KEY (id_medicamento),
    CONSTRAINT id_medicamento FOREIGN KEY (id_medicamento)
        REFERENCES public."MEDICAMENTO" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."ESTOQUE"
    OWNER to postgres;
	
CREATE TABLE public."PROCEDIMENTO"
(
    veterinario integer NOT NULL,
    animal integer NOT NULL,
    data_marcada date NOT NULL,
    registro text COLLATE pg_catalog."default",
    realizado boolean NOT NULL DEFAULT false,
    retorno boolean NOT NULL DEFAULT false,
    status character varying(35) COLLATE pg_catalog."default",
    CONSTRAINT "PROCEDIMENTO_pkey" PRIMARY KEY (veterinario, animal, data_marcada),
    CONSTRAINT id_animal FOREIGN KEY (animal)
        REFERENCES public."ANIMAL" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_veterinario FOREIGN KEY (veterinario)
        REFERENCES public."MEMBRO" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."PROCEDIMENTO"
    OWNER to postgres;
	
CREATE TABLE public."VENDA"
(
    id integer NOT NULL DEFAULT nextval('"VENDA_id_seq"'::regclass),
    medicamento integer NOT NULL,
    animal integer NOT NULL,
    vendedor integer NOT NULL,
    quantidade integer NOT NULL,
    data date NOT NULL,
    CONSTRAINT "VENDA_pkey" PRIMARY KEY (id, medicamento, animal, vendedor),
    CONSTRAINT id_animal FOREIGN KEY (animal)
        REFERENCES public."ANIMAL" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_medicamento FOREIGN KEY (medicamento)
        REFERENCES public."MEDICAMENTO" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_vendedor FOREIGN KEY (vendedor)
        REFERENCES public."MEMBRO" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."VENDA"
    OWNER to postgres;