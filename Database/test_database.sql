--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-08-24 22:13:29

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
-- TOC entry 206 (class 1259 OID 20510)
-- Name: bookings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookings (
    id bigint NOT NULL,
    booked_date date,
    booked_time time without time zone,
    booking_id character varying(30) NOT NULL,
    guest_number integer NOT NULL,
    status boolean NOT NULL,
    table_id bigint
);


ALTER TABLE public.bookings OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 20508)
-- Name: bookings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bookings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bookings_id_seq OWNER TO postgres;

--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 205
-- Name: bookings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;


--
-- TOC entry 200 (class 1259 OID 20472)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(20),
    role_id character varying(30) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 20475)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 201
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 208 (class 1259 OID 20518)
-- Name: tables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tables (
    id bigint NOT NULL,
    seat integer NOT NULL,
    status boolean NOT NULL
);


ALTER TABLE public.tables OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 20516)
-- Name: tables_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tables_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tables_id_seq OWNER TO postgres;

--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 207
-- Name: tables_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tables_id_seq OWNED BY public.tables.id;


--
-- TOC entry 202 (class 1259 OID 20477)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 20480)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(120) NOT NULL,
    encrypted_password character varying(255) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    user_id character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 20486)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2875 (class 2604 OID 20513)
-- Name: bookings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);


--
-- TOC entry 2873 (class 2604 OID 20488)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 2876 (class 2604 OID 20521)
-- Name: tables id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tables ALTER COLUMN id SET DEFAULT nextval('public.tables_id_seq'::regclass);


--
-- TOC entry 2874 (class 2604 OID 20489)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3028 (class 0 OID 20510)
-- Dependencies: 206
-- Data for Name: bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bookings (id, booked_date, booked_time, booking_id, guest_number, status, table_id) FROM stdin;
\.


--
-- TOC entry 3022 (class 0 OID 20472)
-- Dependencies: 200
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, name, role_id) FROM stdin;
1	ROLE_USER	Bv8jl8kT4Pg00a2xLNSn6hOqnOb7Qb
2	ROLE_ADMIN	EPNGkY069YhZpOI7OnuzbdYieAymiH
3	ROLE_MODERATOR	1LYdVrl6LkOOmFnKPEq8hAqnxfOUxM
11	ROLE_STAFF	Or2epiGKnWeOqSQTYVQ4oKskfRI6MO
\.


--
-- TOC entry 3030 (class 0 OID 20518)
-- Dependencies: 208
-- Data for Name: tables; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tables (id, seat, status) FROM stdin;
\.


--
-- TOC entry 3024 (class 0 OID 20477)
-- Dependencies: 202
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_roles (user_id, role_id) FROM stdin;
2	2
3	1
5	1
13	1
14	11
\.


--
-- TOC entry 3025 (class 0 OID 20480)
-- Dependencies: 203
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, email, encrypted_password, first_name, last_name, user_id) FROM stdin;
2	lucifer@gmail.com	$2a$10$GwofgX7pvHYelocrh5EPueQ5Pb5Kz6cGM4kib4bZ55wFIgjyGxos2	Ren	Lucifer	DTfxy1trsrn2NTvw4gfCgQtXpdLFba
3	ken123@gmail.com	$2a$10$b0V13mwvGTtMqQtr4DR4UuDqOaJ2zRlBYnuYWfUoglrmbziI6kBSC	Ken	Kaneki	S7ovM5jGVsHp1WakRXRZ8xqHcB7vOO
5	john@gmail.com	$2a$10$dTxgMAftUPi4.sZkPTPuJ.yAmkzRya4dTfgvOzkNZQnzwlvGYsX8u	John	Smith	bQUJifDEWzoZfu15j3hIWJDQAzJoM5
9	jon@gmail.com	$2a$10$W3OHgAAaRC9lMxr1GrD0ueAbK7BeWW7VUKcIwlrOYVcvz/Ut1Lyoq	John	Smith	AyUAKm7JJOAJFY5TA6MddWp5XuXA3J
13	testerB@gmail.com	$2a$10$l4qVzlvndkQJcLH7xZVBfOflzDmvI89Z7iPvVAzN4inZwyP/vCLUm	Bee	Tester	pQ9bZItq9U3MUZIvreYiPRWaUA7GrD
14	testerC@gmail.com	$2a$10$3eFiUrnNszPp46Oun8fPpOczWMNobVOfawjkyPU1vkbbhggNNXIsS	C	Tester	6fp3d1X5YziJPY7RAGGom3BwWyDoAJ
\.


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 205
-- Name: bookings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bookings_id_seq', 1, false);


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 201
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 11, true);


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 207
-- Name: tables_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tables_id_seq', 1, false);


--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 204
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 14, true);


--
-- TOC entry 2886 (class 2606 OID 20515)
-- Name: bookings bookings_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);


--
-- TOC entry 2878 (class 2606 OID 20491)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2888 (class 2606 OID 20523)
-- Name: tables tables_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tables
    ADD CONSTRAINT tables_pkey PRIMARY KEY (id);


--
-- TOC entry 2882 (class 2606 OID 20493)
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 2880 (class 2606 OID 20495)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 2884 (class 2606 OID 20497)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2891 (class 2606 OID 20524)
-- Name: bookings fk4uj6guqq3uqggk3nj3h302089; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fk4uj6guqq3uqggk3nj3h302089 FOREIGN KEY (table_id) REFERENCES public.tables(id);


--
-- TOC entry 2889 (class 2606 OID 20498)
-- Name: user_roles fkh8ciramu9cc9q3qcqiv4ue8a6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 2890 (class 2606 OID 20503)
-- Name: user_roles fkhfh9dx7w3ubf1co1vdev94g3f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2021-08-24 22:13:29

--
-- PostgreSQL database dump complete
--

