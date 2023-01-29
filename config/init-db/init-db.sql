SELECT 'CREATE DATABASE leanpay WITH OWNER = postgres ENCODING "UTF8" TABLESPACE = pg_default CONNECTION LIMIT = -1'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'leanpay') \gexec
