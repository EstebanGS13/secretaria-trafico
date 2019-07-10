-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-07-2019 a las 17:49:42
-- Versión del servidor: 10.1.40-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_secretaria_trafico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agentes`
--

CREATE TABLE `agentes` (
  `id_agente` int(10) UNSIGNED NOT NULL,
  `nombre_agente` varchar(255) NOT NULL,
  `apellidos_agente` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `agentes`
--

INSERT INTO `agentes` (`id_agente`, `nombre_agente`, `apellidos_agente`) VALUES
(1, 'Willian', 'Cardona'),
(2, 'Wilson', 'Leon '),
(3, 'Catalina ', 'Bedoya Buitrago');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autos`
--

CREATE TABLE `autos` (
  `matricula` char(6) NOT NULL,
  `color_auto` varchar(255) DEFAULT NULL,
  `capacidad_auto` varchar(255) DEFAULT NULL,
  `id_concesionario` int(10) UNSIGNED NOT NULL,
  `id_tipo_vehiculo` int(10) UNSIGNED NOT NULL,
  `id_modelo` int(10) UNSIGNED NOT NULL,
  `id_persona` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `autos`
--

INSERT INTO `autos` (`matricula`, `color_auto`, `capacidad_auto`, `id_concesionario`, `id_tipo_vehiculo`, `id_modelo`, `id_persona`) VALUES
('BJT456', 'Azul', '4 pasajeros, más conductor', 2, 1, 1, 4),
('DMB063', 'Azul', '19 pasajeros, más conductor', 1, 2, 2, 3),
('KLY457', 'Negro', '2 pasajeros, más conductor', 3, 1, 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE `ciudades` (
  `id_ciudad` int(10) UNSIGNED NOT NULL,
  `nombre_ciudad` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`id_ciudad`, `nombre_ciudad`) VALUES
(1, 'Pereira'),
(2, 'Cali'),
(3, 'Bogota');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `concesionarios`
--

CREATE TABLE `concesionarios` (
  `id_concesionario` int(10) UNSIGNED NOT NULL,
  `nombre_concesionario` varchar(255) NOT NULL,
  `direccion_concesionario` varchar(255) NOT NULL,
  `telefono_concesionario` varchar(255) NOT NULL,
  `id_marca` int(10) UNSIGNED NOT NULL,
  `id_ciudad` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `concesionarios`
--

INSERT INTO `concesionarios` (`id_concesionario`, `nombre_concesionario`, `direccion_concesionario`, `telefono_concesionario`, `id_marca`, `id_ciudad`) VALUES
(1, 'Casa Lopez', 'Av. 30 de Agosto #2651 ', '(6) 3335656', 1, 1),
(2, 'Autonorte', 'Av. Santander #2631 ', '(7) 3325556', 2, 2),
(3, 'Casa Lopez', 'Av. Norte #51 ', '(12) 3305656', 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infracciones`
--

CREATE TABLE `infracciones` (
  `codigo_infraccion` char(3) NOT NULL,
  `descripcion_infraccion` varchar(255) NOT NULL,
  `importe_infraccion` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `infracciones`
--

INSERT INTO `infracciones` (`codigo_infraccion`, `descripcion_infraccion`, `importe_infraccion`) VALUES
('A09', 'Adelantar entre dos (2) vehículos automotores que estén en sus respectivos\r\ncarriles.', 1),
('A10', 'Conducir por la vía férrea o por zonas de protección y seguridad', 3),
('A11', 'Transitar por zonas restringidas o por vías de alta velocidad como autopistas y\r\narterias, en este caso el vehículo no automotor será inmovilizado.', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id_marca` int(10) UNSIGNED NOT NULL,
  `nombre_marca` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id_marca`, `nombre_marca`) VALUES
(1, 'Chevrolet'),
(2, 'Audi'),
(3, 'Nissan'),
(4, 'Mazda'),
(5, 'Ford'),
(6, 'Land Rover');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelos`
--

CREATE TABLE `modelos` (
  `id_modelo` int(10) UNSIGNED NOT NULL,
  `nombre_modelo` varchar(255) NOT NULL,
  `anio_modelo` year(4) NOT NULL,
  `id_marca` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `modelos`
--

INSERT INTO `modelos` (`id_modelo`, `nombre_modelo`, `anio_modelo`, `id_marca`) VALUES
(1, 'Mazda 3', 2018, 4),
(2, 'Ford 4', 2014, 5),
(3, 'Range Rover', 2019, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multas`
--

CREATE TABLE `multas` (
  `id_multa` int(10) UNSIGNED NOT NULL,
  `fecha_infraccion` date NOT NULL,
  `direccion_infraccion` varchar(255) NOT NULL,
  `id_ciudad` int(10) UNSIGNED NOT NULL,
  `codigo_infraccion` char(3) NOT NULL,
  `id_persona` int(10) UNSIGNED NOT NULL,
  `id_agente` int(10) UNSIGNED NOT NULL,
  `matricula` char(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `multas`
--

INSERT INTO `multas` (`id_multa`, `fecha_infraccion`, `direccion_infraccion`, `id_ciudad`, `codigo_infraccion`, `id_persona`, `id_agente`, `matricula`) VALUES
(1, '2019-07-01', 'mz 32 4333 -456', 1, 'A11', 1, 1, 'KLY457'),
(2, '2019-07-01', 'mz 32 4333 -456', 2, 'A10', 1, 1, 'DMB063'),
(3, '2019-06-01', 'mz 32 4333 -456', 3, 'A09', 2, 2, 'BJT456'),
(5, '2019-05-20', 'av 30 agosto', 1, 'A11', 2, 1, 'KLY457');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id_persona` int(10) UNSIGNED NOT NULL,
  `nombre_persona` varchar(255) NOT NULL,
  `apellidos_persona` varchar(255) NOT NULL,
  `fecha_nacimiento_persona` date NOT NULL,
  `direccion_persona` varchar(255) NOT NULL,
  `telefono_persona` varchar(255) DEFAULT NULL,
  `id_tipo_persona` int(10) UNSIGNED NOT NULL,
  `id_ciudad` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id_persona`, `nombre_persona`, `apellidos_persona`, `fecha_nacimiento_persona`, `direccion_persona`, `telefono_persona`, `id_tipo_persona`, `id_ciudad`) VALUES
(1, 'Ana Maria', 'Uribe Cardona', '1945-12-05', 'mz 22 cs 232 villa prado', '+57 3245678909', 1, 2),
(2, 'Alejandro', 'Zuluaga Rico', '1989-10-05', 'mz 39 cs 212 Porvenir', '+57 3205678909', 2, 2),
(3, 'Carlos Mario', 'Uribe Cardona', '1945-12-05', 'mz 2 cs 233 ', '+57 3244588909', 3, 3),
(4, 'julian', 'Hincapie Andrade', '1945-12-05', 'mz 22 cs 24 ', '+57 3244588909', 3, 3),
(5, 'Jorge', 'Sanchez Moreno', '1945-12-05', 'mz 32 cs 23 ', '+57 3244588909', 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_personas`
--

CREATE TABLE `tipos_personas` (
  `id_tipo_persona` int(10) UNSIGNED NOT NULL,
  `nombre_tipo_persona` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipos_personas`
--

INSERT INTO `tipos_personas` (`id_tipo_persona`, `nombre_tipo_persona`) VALUES
(1, 'Peaton'),
(2, 'Pasajero'),
(3, 'Conductor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_vehiculos`
--

CREATE TABLE `tipos_vehiculos` (
  `id_tipo_vehiculo` int(10) UNSIGNED NOT NULL,
  `nombre_tipo_vehiculo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipos_vehiculos`
--

INSERT INTO `tipos_vehiculos` (`id_tipo_vehiculo`, `nombre_tipo_vehiculo`) VALUES
(1, 'Automovil'),
(2, 'Autobus'),
(3, 'Microbus');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agentes`
--
ALTER TABLE `agentes`
  ADD PRIMARY KEY (`id_agente`);

--
-- Indices de la tabla `autos`
--
ALTER TABLE `autos`
  ADD PRIMARY KEY (`matricula`),
  ADD KEY `FK_autos_concesionarios` (`id_concesionario`),
  ADD KEY `FK_autos_modelos` (`id_modelo`),
  ADD KEY `FK_autos_tipos_vehiculos` (`id_tipo_vehiculo`),
  ADD KEY `FK_autos_personas` (`id_persona`);

--
-- Indices de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  ADD PRIMARY KEY (`id_ciudad`);

--
-- Indices de la tabla `concesionarios`
--
ALTER TABLE `concesionarios`
  ADD PRIMARY KEY (`id_concesionario`),
  ADD KEY `FK_concesionarios_marcas` (`id_marca`),
  ADD KEY `FK_concesionarios_ciudades` (`id_ciudad`);

--
-- Indices de la tabla `infracciones`
--
ALTER TABLE `infracciones`
  ADD PRIMARY KEY (`codigo_infraccion`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id_marca`);

--
-- Indices de la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD PRIMARY KEY (`id_modelo`),
  ADD KEY `FK_modelos_marcas` (`id_marca`);

--
-- Indices de la tabla `multas`
--
ALTER TABLE `multas`
  ADD PRIMARY KEY (`id_multa`),
  ADD KEY `FK_multas_ciudades` (`id_ciudad`),
  ADD KEY `FK_multas_infracciones` (`codigo_infraccion`),
  ADD KEY `FK_multas_personas` (`id_persona`),
  ADD KEY `FK_multas_agentes` (`id_agente`),
  ADD KEY `FK_multas_autos` (`matricula`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id_persona`),
  ADD KEY `FK_personas_tipos_personas` (`id_tipo_persona`),
  ADD KEY `FK_personas_ciudades` (`id_ciudad`);

--
-- Indices de la tabla `tipos_personas`
--
ALTER TABLE `tipos_personas`
  ADD PRIMARY KEY (`id_tipo_persona`);

--
-- Indices de la tabla `tipos_vehiculos`
--
ALTER TABLE `tipos_vehiculos`
  ADD PRIMARY KEY (`id_tipo_vehiculo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agentes`
--
ALTER TABLE `agentes`
  MODIFY `id_agente` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ciudades`
--
ALTER TABLE `ciudades`
  MODIFY `id_ciudad` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `concesionarios`
--
ALTER TABLE `concesionarios`
  MODIFY `id_concesionario` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id_marca` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `modelos`
--
ALTER TABLE `modelos`
  MODIFY `id_modelo` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `multas`
--
ALTER TABLE `multas`
  MODIFY `id_multa` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id_persona` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipos_personas`
--
ALTER TABLE `tipos_personas`
  MODIFY `id_tipo_persona` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipos_vehiculos`
--
ALTER TABLE `tipos_vehiculos`
  MODIFY `id_tipo_vehiculo` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `autos`
--
ALTER TABLE `autos`
  ADD CONSTRAINT `FK_autos_concesionarios` FOREIGN KEY (`id_concesionario`) REFERENCES `concesionarios` (`id_concesionario`),
  ADD CONSTRAINT `FK_autos_modelos` FOREIGN KEY (`id_modelo`) REFERENCES `modelos` (`id_modelo`),
  ADD CONSTRAINT `FK_autos_personas` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`),
  ADD CONSTRAINT `FK_autos_tipos_vehiculos` FOREIGN KEY (`id_tipo_vehiculo`) REFERENCES `tipos_vehiculos` (`id_tipo_vehiculo`);

--
-- Filtros para la tabla `concesionarios`
--
ALTER TABLE `concesionarios`
  ADD CONSTRAINT `FK_concesionarios_ciudades` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudades` (`id_ciudad`),
  ADD CONSTRAINT `FK_concesionarios_marcas` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id_marca`);

--
-- Filtros para la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD CONSTRAINT `FK_modelos_marcas` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id_marca`);

--
-- Filtros para la tabla `multas`
--
ALTER TABLE `multas`
  ADD CONSTRAINT `FK_multas_agentes` FOREIGN KEY (`id_agente`) REFERENCES `agentes` (`id_agente`),
  ADD CONSTRAINT `FK_multas_autos` FOREIGN KEY (`matricula`) REFERENCES `autos` (`matricula`),
  ADD CONSTRAINT `FK_multas_ciudades` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudades` (`id_ciudad`),
  ADD CONSTRAINT `FK_multas_infracciones` FOREIGN KEY (`codigo_infraccion`) REFERENCES `infracciones` (`codigo_infraccion`),
  ADD CONSTRAINT `FK_multas_personas` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`);

--
-- Filtros para la tabla `personas`
--
ALTER TABLE `personas`
  ADD CONSTRAINT `FK_personas_ciudades` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudades` (`id_ciudad`),
  ADD CONSTRAINT `FK_personas_tipos_personas` FOREIGN KEY (`id_tipo_persona`) REFERENCES `tipos_personas` (`id_tipo_persona`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
