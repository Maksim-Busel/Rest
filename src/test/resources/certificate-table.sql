CREATE TABLE public.certificate(id integer NOT NULL,name character varying(70) NOT NULL,description character varying(120) NOT NULL,price numeric NOT NULL,date_creation date NOT NULL,date_modification date,duration character(40) NOT NULL,lock integer NOT NULL DEFAULT 0,CONSTRAINT certificate_pkey PRIMARY KEY (id))