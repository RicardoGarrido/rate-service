SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET default_tablespace = '';
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER SCHEMA public OWNER TO username;

COMMENT ON SCHEMA public IS 'standard public schema';
SET default_table_access_method = heap;

-- ** TABLE AND SEQUENCE CREATION **
CREATE SEQUENCE public.rate_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE TABLE public.rate (
    id bigint NOT NULL DEFAULT nextval('public.rate_id_seq'),
    brand_id  bigint NOT NULL,
    product_id bigint NOT NULL,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    currency_code character varying(255),
    price bigint NOT NULL
);