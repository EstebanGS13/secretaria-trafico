use db_secretaria_trafico;


insert into marcas 
values(null,'Chevrolet');


insert into marcas 
values(null,'Audi');

insert into marcas 
values(null,'Nissan');

insert into marcas 
values(null,'Mazda');

insert into marcas 
values(null,'Ford');

insert into marcas 
values(null,'Land Rover');


#insertar ciudades
#
#
#

insert into ciudades
values(null,'Pereira');

insert into ciudades
values(null,'Cali');

insert into ciudades
values(null,'Bogota');



#####insertar concesionarios
#
#
#

insert into concesionarios
values (null,'Casa Lopez','Av. 30 de Agosto #2651 ','(6) 3335656',1,1);


insert into concesionarios
values (null,'Autonorte','Av. Santander #2631 ','(7) 3325556',2,2);


insert into concesionarios
values (null,'Casa Lopez','Av. Norte #51 ','(12) 3305656',3,3);



##### insertar tipos_ de personas##
###

insert into tipos_personas
values (null,'Peaton');

insert into tipos_personas
values (null,'Pasajero');

insert into tipos_personas
values (null,'Conductor');


#### insertar personas###
###3
#####
###

insert into personas
values (null,'Ana Maria','Uribe Cardona','1945-12-05','mz 22 cs 232 villa prado','+57 3245678909',1,2 );

insert into personas
values (null,'Alejandro','Zuluaga Rico','1989-10-05','mz 39 cs 212 Porvenir','+57 3205678909',2,2 );

insert into personas
values (null,'Carlos Mario','Uribe Cardona','1945-12-05','mz 2 cs 233 ','+57 3244588909',3,3 );

insert into personas
values (null,'julian','Hincapie Andrade','1945-12-05','mz 22 cs 24 ','+57 3244588909',3,3 );

insert into personas
values (null,'Jorge','Sanchez Moreno','1945-12-05','mz 32 cs 23 ','+57 3244588909',3,3 );


###tipo_vehiculos
####
####
###

insert into tipos_vehiculos
values (null,'Automovil');


insert into tipos_vehiculos
values (null,'Autobus');

insert into tipos_vehiculos
values (null,'Microbus');




#####modelos
###
###
###

insert into modelos
values (null,'Mazda 3',2018,4);

insert into modelos
values (null, 'Ford 4',2014,5);

insert into modelos
values (null, 'Range Rover',2019,6);


### autos ####
####
####
###

insert into autos 
values('DMB063','Azul','19 pasajeros, más conductor',1,2,2,3);


insert into autos 
values('BJT456','Azul','4 pasajeros, más conductor',2,1,1,4);


insert into autos 
values('KLY457','Negro','2 pasajeros, más conductor',3,1,1,5);


#####





#####insertar agentes####
######
#######
insert into agentes
values(null, 'Willian','Cardona');

insert into agentes
values(null, 'Wilson','Leon ');

insert into agentes
values(null, 'Catalina ','Bedoya Buitrago');



##### insrtar infracciones

#####

####
insert into infracciones
values('A09','Adelantar entre dos (2) vehículos automotores que estén en sus respectivos
carriles.',1);

insert into infracciones
values('A10','Conducir por la vía férrea o por zonas de protección y seguridad',3);

insert into infracciones
values('A11','Transitar por zonas restringidas o por vías de alta velocidad como autopistas y
arterias, en este caso el vehículo no automotor será inmovilizado.',2);




##### insertar multas #####
#####
####

insert into multas 
values (null,'2019-07-01','mz 32 4333 -456', 1, 'A11',1,1,'KLY457');

insert into multas 
values (null,'2019-07-01','mz 32 4333 -456', 2, 'A10',1,1,'DMB063');

insert into multas 
values (null,'2019-06-01','mz 32 4333 -456', 3, 'A09',2,2,'BJT456');




