package ru.chupakabr.hibernate.type

import java.sql.Types

/**
 * Created by myltik on 07/03/2014.
 */
class PostgreSQLTypes {

    public static final int ARRAY_INTEGER = Types.ARRAY+100
    public static final int ARRAY_TEXT = Types.ARRAY+101
    public static final int ARRAY_LONG = Types.ARRAY+102
    public static final int ARRAY_DOUBLE = Types.ARRAY+103

    static public class Names {

        // Common Postgres types
        public static final String BIT = "bool"
        public static final String BIGINT = "int8"
        public static final String SMALLINT = "int2"
        public static final String TINYINT = "int2"
        public static final String INTEGER = "int4"
        public static final String NUMERIC = 'numeric($p, $s)'

        public static final String FLOAT = "float4"
        public static final String DOUBLE = "float8"

        public static final String CHAR = "char(1)"
        public static final String VARCHAR = 'varchar($l)'
        public static final String LONGVARCHAR = "text"
        public static final String CLOB = "text"

        public static final String DATE = "date"
        public static final String TIME = "time"
        public static final String TIMESTAMP = "timestamp"

        public static final String BINARY = "bytea"
        public static final String VARBINARY = "bytea"
        public static final String LONGVARBINARY = "bytea"
        public static final String BLOB = "oid"

        public static final String OTHER = "uuid"

        // Custom array types
        public static final String BIGINT_ARRAY = '_' + BIGINT
        public static final String TINYINT_ARRAY = '_' + TINYINT
        public static final String SMALLINT_ARRAY = '_' + SMALLINT
        public static final String INTEGER_ARRAY = '_' + INTEGER

        public static final String FLOAT_ARRAY = '_' + FLOAT
        public static final String DOUBLE_ARRAY = '_' + DOUBLE

        public static final String VARCHAR_ARRAY = '_' + VARCHAR
        public static final String CLOB_ARRAY = '_' + CLOB
        public static final String TEXT_ARRAY = CLOB_ARRAY
    }
}
