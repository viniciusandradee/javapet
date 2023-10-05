CREATE TABLE "TB_PF"
(
    "ID_PESSOA"     NUMBER(19, 0),
    "NM_PESSOA"     VARCHAR2(255 BYTE),
    "DT_NASCIMENTO" DATE,
    "TP_PESSOA"     VARCHAR2(50 BYTE),
    "NR_CPF"        VARCHAR2(50 BYTE)
    -- , CONSTRAINT TB_PF_PK PRIMARY KEY (ID_PESSOA)
);

CREATE TABLE "TB_PJ"
(
    "ID_PESSOA"     NUMBER(19, 0),
    "NM_PESSOA"     VARCHAR2(255 BYTE),
    "DT_NASCIMENTO" DATE,
    "TP_PESSOA"     VARCHAR2(50 BYTE),
    "NR_CNPJ"       VARCHAR2(50 BYTE)
    -- , CONSTRAINT TB_PJ_PK PRIMARY KEY       (ID_PESSOA)
);

CREATE TABLE TB_ANIMAL
(
    ID_ANIMAL NUMBER(19, 0),
    NM_ANIMAL VARCHAR2(255 BYTE),
    RACA      VARCHAR2(255 BYTE),
    DS_ANIMAL VARCHAR2(4000 BYTE),
    TP_ANIMAL VARCHAR2(255 BYTE),
    DONO      NUMBER(19, 0)
    -- , CONSTRAINT TB_ANIMAL_PK PRIMARY KEY (ID_ANIMAL)
);

CREATE TABLE TB_SERVICO
(
    ID_SERVICO    NUMBER(19),
    TP_SERVICO    VARCHAR2(50),
    DS_SERVICO    VARCHAR2(4000),
    DT_REALIZACAO DATE,
    ANIMAL        NUMBER(19)
    -- , CONSTRAINT TB_SERVICO_PK PRIMARY KEY (ID_SERVICO)
);


create sequence SQ_PESSOAS;

create sequence SQ_ANIMAIS;

create sequence SQ_SERVICOS;

create or replace trigger TG_SQ_SERVICOS
    before insert or update
    on TB_SERVICO
    for each row
begin
    if inserting and :new.ID_SERVICO is null then
        :new.ID_SERVICO := SQ_SERVICOS.nextval;
    end if;
end;


create or replace trigger TG_SQ_ANIMAIS
    before insert or update
    on TB_ANIMAL
    for each row
begin
    if inserting and :new.ID_ANIMAL is null then
        :new.ID_ANIMAL := SQ_ANIMAIS.nextval;
    end if;
end;


create or replace trigger TG_SQ_PF
    before insert or update
    on TB_PF
    for each row
begin
    if inserting and :new.ID_PESSOA is null then
        :new.ID_PESSOA := SQ_PESSOAS.nextval;
    end if;
end;

create or replace trigger TG_SQ_PJ
    before insert or update
    on TB_PJ
    for each row
begin
    if inserting and :new.ID_PESSOA is null then
        :new.ID_PESSOA := SQ_PESSOAS.nextval;
    end if;
end;