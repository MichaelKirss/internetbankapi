PGDMP                      |            bankdb    16.1    16.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16503    bankdb    DATABASE     z   CREATE DATABASE bankdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE bankdb;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16848 
   operations    TABLE     	  CREATE TABLE public.operations (
    id integer NOT NULL,
    data_oper date,
    user_acc integer,
    user_acc_receiver integer,
    type_operation character varying,
    status_operation integer,
    sum_operation numeric(16,2),
    comment character varying
);
    DROP TABLE public.operations;
       public         heap    postgres    false    4            �            1259    16847    operations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.operations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.operations_id_seq;
       public          postgres    false    218    4            �           0    0    operations_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.operations_id_seq OWNED BY public.operations.id;
          public          postgres    false    217            �            1259    16841    user_account    TABLE     w   CREATE TABLE public.user_account (
    id integer NOT NULL,
    user_id integer NOT NULL,
    balance numeric(16,2)
);
     DROP TABLE public.user_account;
       public         heap    postgres    false    4            �            1259    16840    user_account_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.user_account_id_seq;
       public          postgres    false    4    216            �           0    0    user_account_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.user_account_id_seq OWNED BY public.user_account.id;
          public          postgres    false    215                        2604    16851    operations id    DEFAULT     n   ALTER TABLE ONLY public.operations ALTER COLUMN id SET DEFAULT nextval('public.operations_id_seq'::regclass);
 <   ALTER TABLE public.operations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217    218                       2604    16844    user_account id    DEFAULT     r   ALTER TABLE ONLY public.user_account ALTER COLUMN id SET DEFAULT nextval('public.user_account_id_seq'::regclass);
 >   ALTER TABLE public.user_account ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            �          0    16848 
   operations 
   TABLE DATA           �   COPY public.operations (id, data_oper, user_acc, user_acc_receiver, type_operation, status_operation, sum_operation, comment) FROM stdin;
    public          postgres    false    218   �       �          0    16841    user_account 
   TABLE DATA           <   COPY public.user_account (id, user_id, balance) FROM stdin;
    public          postgres    false    216   i       �           0    0    operations_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.operations_id_seq', 18, true);
          public          postgres    false    217            �           0    0    user_account_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.user_account_id_seq', 4, true);
          public          postgres    false    215            $           2606    16855    operations operations_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.operations
    ADD CONSTRAINT operations_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.operations DROP CONSTRAINT operations_pkey;
       public            postgres    false    218            "           2606    16846    user_account user_account_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.user_account
    ADD CONSTRAINT user_account_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.user_account DROP CONSTRAINT user_account_pkey;
       public            postgres    false    216            �   �   x���;�0��zs�}�AZ{o`�8�B��ۋ�B*���d�#�%cr�P���>U�!戠x�η��H�m�$�<ԗ�����=Q�h��~B�}�W屬��q���DNRG����I;�[
�	��i���q#�U�	k�L��R=Sr����H�4�q��R��Y��FG�s�s��ht%�]����&WJ= H�      �   6   x��� 0�w�R�K����Ӓ
�aD�C�����qR�_��� 	(     