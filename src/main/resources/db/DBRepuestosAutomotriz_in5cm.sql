Drop database if exists DBRepuestosAutomotriz_in5cm;
create database DBRepuestosAutomotriz_in5cm;
use DBRepuestosAutomotriz_in5cm;

create table Proveedores(
id_proveedor int auto_increment not null,
 nombre_proveedor varchar(60) not null,
 telefono_proveedor int not null,
 direccion varchar(100) not null,
 email_proveedor varchar(100) not null,
 primary key PK_id_proveedor(id_proveedor)
);

create table Empleados(
id_empleado int auto_increment not null,
 nombre_empleado varchar(60) not null,
 apellido_empleado varchar(60) not null,
 puesto_empleado varchar(20) null,
 email_empleado varchar(100) not null,
 primary key PK_id_empleado(id_empleado)
);

create table Repuestos(
id_repuesto int auto_increment not null,
 nombre_repuesto varchar(60) not null,
 categoria_repuesto varchar(60) not null,
 precio_compra double not null,
 precio_venta double not null,
 id_proveedor int not null,
 primary key PK_id_repuesto(id_repuesto),
 constraint FK_repuesto_proveedor foreign key (id_proveedor)
references proveedores(id_proveedor) on delete cascade
);

create table Ventas(
id_venta int auto_increment not null,
 fecha_venta date not null,
 cantidad int not null,
 total double not null,
id_empleado int not null,
 id_repuesto int not null,
 primary key PK_id_venta(id_venta),
 constraint FK_ventas_empleado foreign key (id_empleado)
references Empleados(id_empleado) on delete cascade,
 constraint FK_ventas_repuestos foreign key (id_repuesto)
references Repuestos(id_repuesto) on delete cascade
);


-- procedimientos para crear
-- PROVEEDORES
Delimiter $$
Create Procedure sp_crearP(in e_nombre_proveedor varchar(60),in e_telefono_proveedor int, in e_direccion varchar(100),in e_email_proveedor varchar(100))
	Begin
    insert into Proveedores(nombre_proveedor,telefono_proveedor , direccion , email_proveedor) values(e_nombre_proveedor,e_telefono_proveedor, e_direccion , e_email_proveedor );
	select last_insert_id() as id_proveedor;
    end $$
Delimiter ;

-- EMPLEADOS
Delimiter $$
Create Procedure sp_crearE(in e_nombre_empleado varchar(60),in e_apellido_empleado varchar(60), in e_puesto_empleado varchar(20),in e_email_empleado varchar(100))
	Begin
    insert into Empleados(nombre_empleado,apellido_empleado,puesto_empleado,email_empleado) values(e_nombre_empleado,e_apellido_empleado,e_puesto_empleado,e_email_empleado);
	select last_insert_id() as id_empleado;
    end $$
Delimiter ;

-- Repuestos
Delimiter $$
Create Procedure sp_crearR(in e_nombre_repuesto varchar(60),in e_categoria_repuesto varchar(60), in e_precio_compra double,  in e_precio_venta double, in e_id_proveedor int)
	Begin
    insert into Repuestos(nombre_repuesto,categoria_repuesto,precio_compra,precio_venta,id_proveedor) values(e_nombre_repuesto,e_categoria_repuesto,e_precio_compra,e_precio_venta,e_id_proveedor );
	select last_insert_id() as id_repuesto;
    end $$
Delimiter ;

Delimiter $$
Create Procedure sp_crearV(in e_fecha_venta date,in e_cantidad int , in e_total double, in e_id_empleado int, in e_id_repuesto int)
	Begin
    insert into Ventas(fecha_venta,cantidad , total,id_empleado,id_repuesto) values(e_fecha_venta,e_cantidad , e_total,e_id_empleado,e_id_repuesto);
	select last_insert_id() as id_venta;
    end $$
Delimiter ;

-- Eliminar
Delimiter $$
create procedure sp_EliminarP(in e_id_proveedor int)
	begin
    delete from Proveedores 
    where id_proveedor = e_id_proveedor;
    End $$
Delimiter ;

Delimiter $$
create procedure sp_EliminarE(in e_id_empleado int)
	begin
    delete from Empleados 
    where id_empleado = e_id_empleado;
    End $$
Delimiter ;

Delimiter $$
create procedure sp_EliminarR(in e_id_repuesto int)
	begin
    delete from Repuestos 
    where id_repuesto = e_id_repuesto;
    End $$
Delimiter ;

Delimiter $$
create procedure sp_EliminarV(in e_id_venta int)
	begin
    delete from Ventas 
    where id_venta = e_id_venta;
    End $$
Delimiter ;

-- actualizar
Delimiter $$
Create Procedure sp_EditarP(
	in e_id_proveedor int,
    in e_nombre_proveedor varchar(60),
    in e_telefono_proveedor int,
    in e_direccion varchar(100),
    in e_email_proveedor varchar(100)
    )
    
    begin
    Update Proveedores
    set 
		nombre_proveedor = e_nombre_proveedor,
        telefono_proveedor = e_telefono_proveedor,
        direccion = e_direccion,
        email_proveedor = e_email_proveedor
        where id_proveedor = e_id_proveedor;
    end $$
    
Delimiter ;

Delimiter $$
Create Procedure sp_EditarE(
	in e_id_empleado int,
    in e_nombre_empleado varchar(60),
    in e_apellido_empleado varchar(60),
    in e_puesto_empleado varchar(20),
    in e_email_empleado varchar(100)
    )
    
    begin
    Update Empleados
    set 
		nombre_empleado = e_nombre_empleado,
        apellido_empleado = e_apellido_empleado,
        puesto_empleado = e_puesto_empleado,
        email_empleado = e_email_empleado
        where id_empleado  = e_id_empleado;
    end $$
    
Delimiter ;

Delimiter $$
Create Procedure sp_EditarR(
	in e_id_repuesto int,
    in e_nombre_repuesto varchar(60),
    in e_categoria_repuesto varchar(60),
    in e_precio_compra double,
    in e_precio_venta double,
    in e_id_proveedor int
    )
    
    begin
    Update Repuestos
    set 
		nombre_repuesto = e_nombre_repuesto,
        categoria_repuesto = e_categoria_repuesto,
        precio_compra = e_precio_compra,
        precio_venta = e_precio_venta,
        id_proveedor = e_id_proveedor
        where id_repuesto  = e_id_repuesto;
    end $$
    
Delimiter ;

Delimiter $$
Create Procedure sp_EditarV(
	in e_id_venta int,
    in e_fecha_venta date,
    in e_cantidad int,
    in e_total double,
    in e_id_empleado int,
    in e_id_repuesto int
    )
    
    begin
    Update Ventas
    set 
		fecha_venta = e_fecha_venta,
        cantidad  = e_cantidad ,
        total = e_total,
        id_empleado = e_id_empleado,
        id_id_repuesto = e_id_repuesto
        where id_venta  = e_id_venta;
    end $$
    
Delimiter ;

-- listar
Delimiter $$
create procedure sp_readAllProveedores()
	begin
    select * from Proveedores order by id_proveedor;
    end $$
Delimiter ;

Delimiter $$
create procedure sp_readAllEmpleados()
	begin
    select * from Empleados order by id_empleado;
    end $$
Delimiter ;

Delimiter $$
create procedure sp_readAllRepuestos()
	begin
    select * from Repuestos order by id_repuesto;
    end $$
Delimiter ;

Delimiter $$
create procedure sp_readAllVentas()
	begin
    select * from Ventas order by id_venta;
    end $$
Delimiter ;

Call sp_crearP('AutoPartes Lopez', 55512345, 'Zona 1, Ciudad de Guatemala', 'contacto@autoparteslopez.com');
Call sp_crearP('Repuestos El Motor', 55523456, 'Zona 5, Ciudad de Guatemala', 'ventas@elmotor.com');
Call sp_crearP('Distribuidora Ramirez', 55534567, 'Zona 7, Ciudad de Guatemala', 'ramirez@distribuidora.com');
Call sp_crearP('Importadora Gomez', 55545678, 'Zona 9, Ciudad de Guatemala', 'gomez@importadora.com');
Call sp_crearP('AutoServicios Perez', 55556789, 'Zona 11, Ciudad de Guatemala', 'perez@autoservicios.com');
Call sp_crearP('Repuestos Centro', 55567890, 'Zona 12, Ciudad de Guatemala', 'centro@repuestos.com');
Call sp_crearP('Motor y Más', 55578901, 'Zona 14, Ciudad de Guatemala', 'motorymas@gmail.com');
Call sp_crearP('Partes Express', 55589012, 'Zona 15, Ciudad de Guatemala', 'partesexpress@gmail.com');
Call sp_crearP('Autopartes del Sur', 55590123, 'Villa Nueva', 'sur@autopartes.com');
Call sp_crearP('Repuestos Nacionales', 55501234, 'Mixco', 'nacionales@repuestos.com');

Call sp_crearE('Carlos', 'Martinez', 'Vendedor', 'carlos.martinez@empresa.com');
Call sp_crearE('Ana', 'Lopez', 'Cajera', 'ana.lopez@empresa.com');
Call sp_crearE('Luis', 'Hernandez', 'Vendedor', 'luis.hernandez@empresa.com');
Call sp_crearE('Maria', 'Gomez', 'Administradora', 'maria.gomez@empresa.com');
Call sp_crearE('Jorge', 'Perez', 'Vendedor', 'jorge.perez@empresa.com');
Call sp_crearE('Sofia', 'Ramirez', 'Cajera', 'sofia.ramirez@empresa.com');
Call sp_crearE('Pedro', 'Castillo', 'Supervisor', 'pedro.castillo@empresa.com');
Call sp_crearE('Lucia', 'Morales', 'Vendedor', 'lucia.morales@empresa.com');
Call sp_crearE('Diego', 'Alvarez', 'Bodega', 'diego.alvarez@empresa.com');
Call sp_crearE('Elena', 'Cruz', 'Contadora', 'elena.cruz@empresa.com');

Call sp_crearR('Filtro de aceite', 'Motor', 50.00, 75.00, 1);
Call sp_crearR('Bujía', 'Encendido', 20.00, 35.00, 2);
Call sp_crearR('Pastillas de freno', 'Frenos', 120.00, 180.00, 3);
Call sp_crearR('Batería 12V', 'Eléctrico', 450.00, 600.00, 4);
Call sp_crearR('Amortiguador', 'Suspensión', 300.00, 420.00, 5);
Call sp_crearR('Radiador', 'Enfriamiento', 800.00, 1050.00, 6);
Call sp_crearR('Aceite 20W50', 'Lubricantes', 90.00, 130.00, 7);
Call sp_crearR('Correa de tiempo', 'Motor', 150.00, 220.00, 8);
Call sp_crearR('Alternador', 'Eléctrico', 900.00, 1200.00, 9);
Call sp_crearR('Disco de freno', 'Frenos', 200.00, 280.00, 10);

Call sp_crearV('2026-01-10', 2, 150.00, 1, 1);
Call sp_crearV('2026-01-11', 1, 35.00, 2, 2);
Call sp_crearV('2026-01-12', 3, 540.00, 3, 3);
Call sp_crearV('2026-01-13', 1, 600.00, 4, 4);
Call sp_crearV('2026-01-14', 2, 840.00, 5, 5);
Call sp_crearV('2026-01-15', 1, 1050.00, 6, 6);
Call sp_crearV('2026-01-16', 4, 520.00, 7, 7);
Call sp_crearV('2026-01-17', 1, 220.00, 8, 8);
Call sp_crearV('2026-01-18', 1, 1200.00, 9, 9);
Call sp_crearV('2026-01-19', 2, 560.00, 10, 10);