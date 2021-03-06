--
-- TOC entry 166 (class 1259 OID 17677)
-- Dependencies: 6
-- Name: parametros; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--
DROP TABLE IF EXISTS parametros;
CREATE TABLE parametros (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    valor text NOT NULL,
    version integer
);


ALTER TABLE public.parametros OWNER TO postgres;

--
-- TOC entry 165 (class 1259 OID 17675)
-- Dependencies: 166 6
-- Name: parametros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE parametros_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.parametros_id_seq OWNER TO postgres;

--
-- TOC entry 1988 (class 0 OID 0)
-- Dependencies: 165
-- Name: parametros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE parametros_id_seq OWNED BY parametros.id;

--
-- TOC entry 166 (class 1259 OID 17677)
-- Dependencies: 6
-- Name: parametros; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

DROP TABLE IF EXISTS periodos;
CREATE TABLE periodos (
    id bigint NOT NULL,
    tipo character varying NOT NULL,
    nombre character varying NOT NULL
);


ALTER TABLE public.periodos OWNER TO postgres;

--
-- TOC entry 165 (class 1259 OID 17675)
-- Dependencies: 166 6
-- Name: parametros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--
DROP SEQUENCE IF EXISTS periodos_id_seq;
CREATE SEQUENCE periodos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.periodos_id_seq OWNER TO postgres;

--
-- TOC entry 1988 (class 0 OID 0)
-- Dependencies: 165
-- Name: parametros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE periodos_id_seq OWNED BY periodos.id;

