--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animal; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE animal (
    animal_id integer NOT NULL,
    breed character varying(255),
    description character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    image_url character varying(255) NOT NULL,
    animal_type_type_id integer
);


ALTER TABLE animal OWNER TO rush;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: rush
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO rush;

--
-- Name: note; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE note (
    note_id integer NOT NULL,
    note_content character varying(255) NOT NULL,
    created date NOT NULL,
    animal_id integer
);


ALTER TABLE note OWNER TO rush;

--
-- Name: type; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE type (
    type_id integer NOT NULL,
    type_name character varying(255) NOT NULL
);


ALTER TABLE type OWNER TO rush;

--
-- Name: users; Type: TABLE; Schema: public; Owner: rush
--

CREATE TABLE users (
    id integer NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255)
);


ALTER TABLE users OWNER TO rush;

--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY animal (animal_id, breed, description, animal_name, image_url, animal_type_type_id) FROM stdin;
91		Fast.	George	images/X.jpg	1
94		Meow.	Andrea	images/Cat.png	42
85		Fiesty	Corinne	images/Dog.jpg	44
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: rush
--

SELECT pg_catalog.setval('hibernate_sequence', 124, true);


--
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY note (note_id, note_content, created, animal_id) FROM stdin;
118	Nice.	2016-09-30	94
88	Entered care.	2016-09-23	85
89	Blah!	2016-09-26	85
90	Blah blah!	2016-09-26	85
92	Admitted.	2016-09-26	91
93	Released.	2016-09-26	91
95	Blah.	2016-09-26	94
96	Blah blah.	2016-09-26	94
97	And more blah.	2016-09-26	94
98	Flat cat.	2016-09-26	94
\.


--
-- Data for Name: type; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY type (type_id, type_name) FROM stdin;
42	Cat
44	Dog
1	Horse
2	Snake
3	Frog
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: rush
--

COPY users (id, email, name, password) FROM stdin;
101	jebush@gmail.com	Jimmy	sha1:64000:18:MPFFSBfd896nAvqJXf8uDygu1WMLLVFk:t/adX9FGM8HtdTgvqbtC2IUa
120	arnagel@gmail.com	Andrew	sha1:64000:18:kOqBeNTl8/9xzlwG+La/Zoli1NSGoB0l:FLFG7EB1Lki4G55MFPHAP1g+
\.


--
-- Name: animal_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (animal_id);


--
-- Name: note_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY note
    ADD CONSTRAINT note_pkey PRIMARY KEY (note_id);


--
-- Name: type_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY type
    ADD CONSTRAINT type_pkey PRIMARY KEY (type_id);


--
-- Name: uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: uk_f1qndtxdjk0ose1st2rhv1vhx; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY type
    ADD CONSTRAINT uk_f1qndtxdjk0ose1st2rhv1vhx UNIQUE (type_name);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: fk6nqbung7m47m5ubx4y7ll3jor; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT fk6nqbung7m47m5ubx4y7ll3jor FOREIGN KEY (animal_type_type_id) REFERENCES type(type_id);


--
-- Name: fkaro2ok22aqw0go52grtagv2fd; Type: FK CONSTRAINT; Schema: public; Owner: rush
--

ALTER TABLE ONLY note
    ADD CONSTRAINT fkaro2ok22aqw0go52grtagv2fd FOREIGN KEY (animal_id) REFERENCES animal(animal_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

