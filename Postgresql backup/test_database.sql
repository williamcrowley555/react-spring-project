PGDMP                         y            test_database    13.1    13.1 '    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    20471    test_database    DATABASE     q   CREATE DATABASE test_database WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE test_database;
                postgres    false            ?            1259    20510    bookings    TABLE     ?   CREATE TABLE public.bookings (
    id bigint NOT NULL,
    booked_date date,
    booked_time time without time zone,
    booking_id character varying(30) NOT NULL,
    guest_number integer NOT NULL,
    status boolean NOT NULL,
    table_id bigint
);
    DROP TABLE public.bookings;
       public         heap    postgres    false            ?            1259    20508    bookings_id_seq    SEQUENCE     x   CREATE SEQUENCE public.bookings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.bookings_id_seq;
       public          postgres    false    206            ?           0    0    bookings_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.bookings_id_seq OWNED BY public.bookings.id;
          public          postgres    false    205            ?            1259    20472    roles    TABLE     ?   CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(20),
    role_id character varying(30) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false            ?            1259    20475    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    200            ?           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    201            ?            1259    20518    tables    TABLE     o   CREATE TABLE public.tables (
    id bigint NOT NULL,
    seat integer NOT NULL,
    status boolean NOT NULL
);
    DROP TABLE public.tables;
       public         heap    postgres    false            ?            1259    20516    tables_id_seq    SEQUENCE     v   CREATE SEQUENCE public.tables_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.tables_id_seq;
       public          postgres    false    208            ?           0    0    tables_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.tables_id_seq OWNED BY public.tables.id;
          public          postgres    false    207            ?            1259    20477 
   user_roles    TABLE     ]   CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.user_roles;
       public         heap    postgres    false            ?            1259    20480    users    TABLE     #  CREATE TABLE public.users (
    id bigint NOT NULL,
    email character varying(120) NOT NULL,
    encrypted_password character varying(255) NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    user_id character varying(255) NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            ?            1259    20486    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    203            ?           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    204            ;           2604    20513    bookings id    DEFAULT     j   ALTER TABLE ONLY public.bookings ALTER COLUMN id SET DEFAULT nextval('public.bookings_id_seq'::regclass);
 :   ALTER TABLE public.bookings ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    205    206            9           2604    20488    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200            <           2604    20521 	   tables id    DEFAULT     f   ALTER TABLE ONLY public.tables ALTER COLUMN id SET DEFAULT nextval('public.tables_id_seq'::regclass);
 8   ALTER TABLE public.tables ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    207    208    208            :           2604    20489    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    203            ?          0    20510    bookings 
   TABLE DATA           l   COPY public.bookings (id, booked_date, booked_time, booking_id, guest_number, status, table_id) FROM stdin;
    public          postgres    false    206   ?)       ?          0    20472    roles 
   TABLE DATA           2   COPY public.roles (id, name, role_id) FROM stdin;
    public          postgres    false    200   ?)       ?          0    20518    tables 
   TABLE DATA           2   COPY public.tables (id, seat, status) FROM stdin;
    public          postgres    false    208   ?*       ?          0    20477 
   user_roles 
   TABLE DATA           6   COPY public.user_roles (user_id, role_id) FROM stdin;
    public          postgres    false    202   ?*       ?          0    20480    users 
   TABLE DATA           ^   COPY public.users (id, email, encrypted_password, first_name, last_name, user_id) FROM stdin;
    public          postgres    false    203   +       ?           0    0    bookings_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.bookings_id_seq', 1, false);
          public          postgres    false    205            ?           0    0    roles_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.roles_id_seq', 11, true);
          public          postgres    false    201            ?           0    0    tables_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.tables_id_seq', 1, false);
          public          postgres    false    207            ?           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 14, true);
          public          postgres    false    204            F           2606    20515    bookings bookings_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_pkey;
       public            postgres    false    206            >           2606    20491    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    200            H           2606    20523    tables tables_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.tables
    ADD CONSTRAINT tables_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.tables DROP CONSTRAINT tables_pkey;
       public            postgres    false    208            B           2606    20493 !   users uk6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    203            @           2606    20495    user_roles user_roles_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);
 D   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_roles_pkey;
       public            postgres    false    202    202            D           2606    20497    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    203            K           2606    20524 $   bookings fk4uj6guqq3uqggk3nj3h302089    FK CONSTRAINT     ?   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fk4uj6guqq3uqggk3nj3h302089 FOREIGN KEY (table_id) REFERENCES public.tables(id);
 N   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fk4uj6guqq3uqggk3nj3h302089;
       public          postgres    false    206    2888    208            I           2606    20498 &   user_roles fkh8ciramu9cc9q3qcqiv4ue8a6    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id);
 P   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6;
       public          postgres    false    202    2878    200            J           2606    20503 &   user_roles fkhfh9dx7w3ubf1co1vdev94g3f    FK CONSTRAINT     ?   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);
 P   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f;
       public          postgres    false    2884    203    202            ?      x?????? ? ?      ?   ?   x?%??
?0 ???a?].?&?|???"Q?ԥFa=}ޟC??]??q??؏??2#?k?M_?X(??IAa??WJ?ȏKb??????y????l+??ޮ6?q?G$?e>?f??
6???R?q?0!ku]?^???S?H3???3xu5???e?1?Z?8      ?      x?????? ? ?      ?       x?3?4?2?4?2bC?Є?А+F??? 3??      ?     x?m?ɲ?@ @?u?om??4?DPd???a?Fd?????X?Re~???kPMi?Q?=???Z?m>??????-??#?Um?߄/sB?`&nRE??"??H惚???k`??'??????7??Cf>????.;???@?vͽ?ɧ?r?Lg?????ͻ??0bmM?J
?)????U_'?P7?t݃Ӌ>?*p[?w???ǥ????8??H????5?n?<s??5~5D?阓?Z`]R{????91hy????Q?!?N@{???.?H,W+????6?+ܹ??k?????{??a}?8???~W?ϞUz?sB09??}?sO?:W?z)??ܑ=/??_..<բ?P;??7z???L?4??????һ]??V??JKK?G?y6pE嚨?]$&? ??E͋????n$???+	:k?D??ع??F*?QX???P|}0,??޿?:n?\jv?Ƙ?-6;#??~i???|/?eI?$?<?\u???_y?;.c!??f????9i????c?0?O=??     