use db_secretaria_trafico;

SELECT * FROM personas;


select nombre_persona, apellidos_persona from personas where id_ciudad= 2;


select id_persona from multas where
 (select codigo_infraccion from multas limit 1) =
 (select codigo_infraccion from infracciones where importe_infraccion = 1 limit 1);





select codigo_infraccion from  infracciones where importe_infraccion = 1;  




select matricula from autos where id_concesionario = 1  and id_tipo_vehiculo = 2;


select *from autos where capacidad_auto like '%4 pasajeros%'; 


update infracciones set importe_infraccion=3 where codigo_infraccion= 'A11';


delete from multas where id_multa = 1;



SELECT *
FROM personas
INNER JOIN autos
ON personas.id_persona=1;



SELECT * FROM multas ORDER BY fecha_infraccion DESC;








