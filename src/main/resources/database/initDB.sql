CREATE TABLE IF NOT EXISTS public.requested_number(
  id BIGINT PRIMARY KEY,
  number INT,
  latency BIGINT,
  is_request_Success boolean NOT NULL
);