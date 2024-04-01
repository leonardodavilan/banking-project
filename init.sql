USE master;
GO

IF NOT EXISTS (
    SELECT [name]
    FROM sys.databases
    WHERE [name] = ''
)
BEGIN
    CREATE DATABASE dbprueba1;
END
GO

USE dbprueba1;
GO

-- Tabla Persona
CREATE TABLE persona (
                         persona_id INT PRIMARY KEY IDENTITY,
                         nombre NVARCHAR(100),
                         genero NVARCHAR(10),
                         edad INT,
                         identificacion NVARCHAR(20),
                         direccion NVARCHAR(200),
                         telefono NVARCHAR(20)
);
GO

-- Tabla Cliente
CREATE TABLE cliente (
                         cliente_id INT PRIMARY KEY IDENTITY,
                         contrasena NVARCHAR(50),
                         estado BIT,
                         persona_id INT FOREIGN KEY REFERENCES persona(persona_id) -- Relación con Persona
);
GO

-- Tabla Cuenta
CREATE TABLE cuenta (
                        numero_cuenta INT PRIMARY KEY,
                        tipo_cuenta NVARCHAR(50),
                        saldo_inicial DECIMAL(18, 2),
                        estado BIT,
                        cliente_id INT FOREIGN KEY REFERENCES cliente(cliente_id) -- Relación con Cliente
);
GO

-- Tabla Movimiento
CREATE TABLE movimiento (
                            movimiento_id INT PRIMARY KEY IDENTITY,
                            fecha DATE,
                            tipo_movimiento NVARCHAR(50),
                            valor DECIMAL(18, 2),
                            saldo DECIMAL(18, 2),
                            numero_cuenta INT FOREIGN KEY REFERENCES cuenta(numero_cuenta) -- Relación con Cuenta
);
GO


-- Insertar datos en la tabla Persona
INSERT INTO persona (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Jose Lema', 'M',25,'76876542','Otavalo sn y principal', '98254785'),
       ('Marianela Montalvo','F',26,'86876542','Amazonas y NNUU', '97548965'),
       ('Juan Osorio', 'M',27,'96876542','13 junio y Equinoccial', '98874587');

-- Insertar datos en la tabla Cliente
INSERT INTO cliente (contrasena, estado, persona_id)
VALUES ('1234', 1, 1), -- José Lema
       ('5678', 1, 2), -- Marianela Montalvo
       ('1245', 1, 3); -- Juan Osorio

-- Insertar datos en la tabla Cuenta
INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES (478758, 'Ahorros', 2000, 1, 1), -- Cuenta de José Lema
       (225487, 'Corriente', 100, 1, 2), -- Cuenta de Marianela Montalvo
       (495878, 'Ahorros', 0, 1, 3), -- Cuenta de Juan Osorio
       (496825, 'Ahorros', 540, 1, 2); -- Cuenta de Marianela Montalvo

-- Insertar nueva cuenta para José Lema
INSERT INTO cuenta (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id)
VALUES (585545, 'Corriente', 1000, 1, 1); -- Cuenta de José Lema

-- Actualizar saldos y registrar movimientos
-- Retiro de 575 de la cuenta de José Lema
UPDATE cuenta SET saldo_inicial = saldo_inicial - 575 WHERE numero_cuenta = 478758;
INSERT INTO Movimiento (Fecha, tipo_movimiento, valor, saldo, numero_cuenta)
VALUES (GETDATE(), 'Retiro', -575, (SELECT saldo_inicial FROM Cuenta WHERE numero_cuenta = 478758), 478758);

-- Depósito de 600 en la cuenta de Marianela Montalvo
UPDATE cuenta SET saldo_inicial = saldo_inicial + 600 WHERE numero_cuenta = 225487;
INSERT INTO Movimiento (Fecha, tipo_movimiento, valor, saldo, numero_cuenta)
VALUES (GETDATE(), 'Deposito', 600, (SELECT saldo_inicial FROM Cuenta WHERE numero_cuenta = 225487), 225487);

-- Depósito de 150 en la cuenta de Juan Osorio
UPDATE cuenta SET saldo_inicial = saldo_inicial + 150 WHERE numero_cuenta = 495878;
INSERT INTO Movimiento (Fecha, tipo_movimiento, valor, saldo, numero_cuenta)
VALUES (GETDATE(), 'Deposito', 150, (SELECT saldo_inicial FROM Cuenta WHERE numero_cuenta = 495878), 495878);

-- Retiro de 540 de la cuenta de Marianela Montalvo
UPDATE cuenta SET saldo_inicial = saldo_inicial - 540 WHERE numero_cuenta = 496825;
INSERT INTO Movimiento (Fecha, tipo_movimiento, valor, saldo, numero_cuenta)
VALUES (GETDATE(), 'Retiro', -540, (SELECT saldo_inicial FROM Cuenta WHERE numero_cuenta = 496825), 496825);
