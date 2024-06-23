DROP TABLE IF EXISTS public.motorbikes;

CREATE TABLE IF NOT EXISTS public.motorbikes
(
    motorbike_id bigint NOT NULL DEFAULT 'nextval('motorbikes_id_seq'::regclass)',
    cost bigint NOT NULL,
    model text COLLATE pg_catalog."default" NOT NULL,
    brand text COLLATE pg_catalog."default" NOT NULL,
    engine_type text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT motorbikes_pkey PRIMARY KEY (motorbike_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.motorbikes
    OWNER to imsrbp_dev;
-- Index: unique_model

-DROP INDEX IF EXISTS public.unique_model;

CREATE UNIQUE INDEX IF NOT EXISTS unique_model
    ON public.motorbikes USING btree
    (model COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default;