CREATE TABLE public.certificate_bike_goods
(
  certificate_id integer NOT NULL,
  bike_goods_id integer NOT NULL,
  CONSTRAINT certificate_bike_goods_pkey PRIMARY KEY (certificate_id, bike_goods_id),
  CONSTRAINT certificate_bike_goods_certificate_id_fkey FOREIGN KEY (certificate_id)
      REFERENCES public.certificate (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT certificate_bike_goods_bike_goods_id_fkey FOREIGN KEY (bike_goods_id)
      REFERENCES public.bike_goods (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)