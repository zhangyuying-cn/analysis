--abase数据库
CREATE SCHEMA DB_ANALYSIS
    AUTHORIZATION sa;
	

set search_path to DB_ANALYSIS;
commit;
drop table if exists T_RECORD;
create table T_RECORD
(
    Id                    varchar(50)                  NOT NULL,    -- 主键
    Recorddata            text                         NULL,        -- 记录数据
    Updatetime            timestamp(3)                 NULL,        -- 时间
constraint PK_T_RECORD primary key( ID )
);


set search_path to DB_ANALYSIS;
commit;
drop table if exists T_METADATA;
create table T_METADATA
(
    Id                    varchar(50)                  NOT NULL,    -- 主键
    Metadata              text                         NULL,        -- 元数据
    Updatetime            timestamp(3)                 NULL,        -- 时间
	CurrentUse            numeric(3)                   NULL,        -- 是否为当前使用的元数据
constraint PK_T_METADATA primary key( ID )
);