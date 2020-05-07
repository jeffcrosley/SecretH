-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER jeffcrosley WITH PASSWORD 'greatwall';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO jeffcrosley;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO jeffcrosley;

CREATE USER dbuser WITH PASSWORD 'greatwall';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO dbuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO dbuser;
