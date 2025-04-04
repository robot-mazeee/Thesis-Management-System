--
-- PostgreSQL database dump
--

-- Dumped from database version 14.17
-- Dumped by pg_dump version 14.17 (Ubuntu 14.17-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: defences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.defences (
    grade bigint,
    id bigint NOT NULL,
    date character varying(255),
    workflow_status character varying(255) NOT NULL,
    CONSTRAINT defences_workflow_status_check CHECK (((workflow_status)::text = ANY ((ARRAY['CREATED'::character varying, 'DEFENSE_SCHEDULED'::character varying, 'IN_REVISION'::character varying, 'SUBMITTED_TO_FENIX'::character varying])::text[])))
);


ALTER TABLE public.defences OWNER TO postgres;

--
-- Name: defences_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.defences_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.defences_seq OWNER TO postgres;

--
-- Name: people; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.people (
    id bigint NOT NULL,
    workflow_teachers bigint,
    email character varying(255) NOT NULL,
    ist_id character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    CONSTRAINT people_type_check CHECK (((type)::text = ANY ((ARRAY['COORDINATOR'::character varying, 'STAFF'::character varying, 'STUDENT'::character varying, 'TEACHER'::character varying, 'SC'::character varying])::text[])))
);


ALTER TABLE public.people OWNER TO postgres;

--
-- Name: people_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.people_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.people_seq OWNER TO postgres;

--
-- Name: workflows; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workflows (
    id bigint NOT NULL,
    juri_president bigint,
    student bigint,
    workflow_status character varying(255) NOT NULL,
    CONSTRAINT workflows_workflow_status_check CHECK (((workflow_status)::text = ANY ((ARRAY['JURI_PROPOSAL_SUBMITTED'::character varying, 'APPROVED_BY_SC'::character varying, 'JURI_PRESIDENT_ATTRIBUTED'::character varying, 'DOCUMENT_SIGNED'::character varying, 'SUBMITTED_TO_FENIX'::character varying])::text[])))
);


ALTER TABLE public.workflows OWNER TO postgres;

--
-- Name: workflows_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.workflows_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workflows_seq OWNER TO postgres;

--
-- Data for Name: defences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.defences (grade, id, date, workflow_status) FROM stdin;
\.


--
-- Data for Name: people; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.people (id, workflow_teachers, email, ist_id, name, type) FROM stdin;
1	\N	maria@gmail.com	ist193793	Maria	STUDENT
2	\N	susana@gmail.com	ist83983	Susana	SC
3	\N	joana@gmail.com	ist119375	Joana	TEACHER
4	\N	miguel@gmail.com	ist110279	Miguel	STAFF
5	\N	jorge@gmail.com	ist104659	Jorge	COORDINATOR
\.


--
-- Data for Name: workflows; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workflows (id, juri_president, student, workflow_status) FROM stdin;
\.


--
-- Name: defences_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.defences_seq', 1, false);


--
-- Name: people_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.people_seq', 51, true);


--
-- Name: workflows_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workflows_seq', 1, false);


--
-- Name: defences defences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.defences
    ADD CONSTRAINT defences_pkey PRIMARY KEY (id);


--
-- Name: people people_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.people
    ADD CONSTRAINT people_email_key UNIQUE (email);


--
-- Name: people people_ist_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.people
    ADD CONSTRAINT people_ist_id_key UNIQUE (ist_id);


--
-- Name: people people_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);


--
-- Name: workflows workflows_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workflows
    ADD CONSTRAINT workflows_pkey PRIMARY KEY (id);


--
-- Name: workflows fk21oimpg7h59lp4ygc47dm1tpn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workflows
    ADD CONSTRAINT fk21oimpg7h59lp4ygc47dm1tpn FOREIGN KEY (student) REFERENCES public.people(id);


--
-- Name: people fkeb1nhasgcg6kmglfqemqg30v6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.people
    ADD CONSTRAINT fkeb1nhasgcg6kmglfqemqg30v6 FOREIGN KEY (workflow_teachers) REFERENCES public.workflows(id);


--
-- Name: workflows fkl24yrmn2gbq9i6tw2tnmxb8j0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workflows
    ADD CONSTRAINT fkl24yrmn2gbq9i6tw2tnmxb8j0 FOREIGN KEY (juri_president) REFERENCES public.people(id);


--
-- PostgreSQL database dump complete
--

