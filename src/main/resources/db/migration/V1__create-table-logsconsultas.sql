create table logs_consultas(

    iid bigint not null auto_increment,
    cconteudo longtext not null,
    devento datetime not null,

    primary key(iid)

);