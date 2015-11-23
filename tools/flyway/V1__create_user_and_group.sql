CREATE TABLE user_entity (
    user_id VARCHAR(8) NOT NULL,
    password VARCHAR (128) NOT NULL,
    last_name VARCHAR (32) ,
    first_name VARCHAR (32) ,
    gender CHAR (1) ,
    deleted_flg SMALLINT NOT NULL DEFAULT 0,
    version INTEGER NOT NULL DEFAULT 0,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHAR(8) NOT NULL DEFAULT 'system',
    updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by CHAR(8) DEFAULT 'system',
    PRIMARY KEY (user_id)
);

CREATE TABLE group_entity (
    group_id char(8) NOT NULL,
    group_name VARCHAR (64) ,
    deleted_flg SMALLINT NOT NULL DEFAULT 0,
    version INTEGER NOT NULL DEFAULT 0,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHAR(8) NOT NULL DEFAULT 'system',
    updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by CHAR(8) DEFAULT 'system',
    PRIMARY KEY (group_id)
);

CREATE TABLE user_group_relation (
    user_id VARCHAR (8) ,
    group_id char (8) ,
    deleted_flg SMALLINT NOT NULL DEFAULT 0,
    version INTEGER NOT NULL DEFAULT 0,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by CHAR(8) NOT NULL DEFAULT 'system',
    updated TIMESTAMP NOT NULL ,
    updated_by CHAR(8) DEFAULT 'system',
    FOREIGN KEY (user_id) REFERENCES user_entity (user_id) ,
    FOREIGN KEY (group_id) REFERENCES group_entity (group_id) ,
    PRIMARY KEY (user_id, group_id)
);

CALL SYSCS_UTIL.SYSCS_IMPORT_DATA (null, 'USER_ENTITY', null, null, 'tools/flyway/user_entity.csv', ',', '"', 'UTF-8', 0);  
CALL SYSCS_UTIL.SYSCS_IMPORT_DATA (null, 'GROUP_ENTITY', null, null, 'tools/flyway/group_entity.csv', ',', '"', 'UTF-8', 0);  
CALL SYSCS_UTIL.SYSCS_IMPORT_DATA (null, 'USER_GROUP_RELATION', null, null, 'tools/flyway/user_group_relation.csv', ',', '"', 'UTF-8', 0);  
