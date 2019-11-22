create database MantenimientoCliente;

create table cliente
(
id int primary key,
ruc char(11),
razonSocial char(40),
direccion char(40),
telefono char(10)
);

select * from cliente;

/* =================================================================*/

create procedure insertarCliente(
in _id int,
in _ruc char(11),
in _razonSocial char(40),
in _direccion char(40),
in _telefono char(10)
)
insert into Cliente(id, ruc, razonSocial, direccion, telefono)
values (_id, _ruc, _razonSocial, _direccion, _telefono);

/* =================================================================*/

create procedure actualizarCliente(
in _id int,
in _ruc char(11),
in _razonSocial char(40),
in _direccion char(40),
in _telefono char(10)
)update Cliente set ruc = _ruc,
razonSocial = _razonSocial,
direccion = _direccion,
telefono =  _telefono 
where id = _id;