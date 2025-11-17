/* insertar todos los datos de tabla de usuarios private Long id;
    private String rut;
        private String nombre;
        private String apellido;
        private String email;
        private String password;
        private String rol;*/

        INSERT INTO usuarios (rut, nombre, apellido, email, password, rol) VALUES ('12345678-9', 'Juan', 'Pérez', 'juan.perez@example.com', 'password123', 'admin');
        INSERT INTO usuarios (rut, nombre, apellido, email, password, rol) VALUES ('98765432-1', 'María', 'Gómez', 'maria.gomez@example.com', 'password456', 'user');
        INSERT INTO usuarios (rut, nombre, apellido, email, password, rol) VALUES ('11223344-5', 'Carlos', 'López', 'carlos.lopez@example.com', 'password789', 'user');

        /*insertar datos de tabla de mascotas*/
        /*ahora incluye el campo peso y fecha_nacimiento*/

        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Max', 'Perro', 'Labrador', 30.5, '2019-01-15', 1);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Luna', 'Gato', 'Siamés', 3.8, '2021-03-20', 2);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Rocky', 'Perro', 'Bulldog', 22.0, '2020-05-10', 3);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Molly', 'Perro', 'Beagle', 10.2, '2022-08-25', 1);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Bella', 'Gato', 'Persa', 4.5, '2018-11-05', 2);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Charlie', 'Perro', 'Poodle', 6.1, '2023-02-14', 3);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Lucy', 'Gato', 'Maine Coon', 7.2, '2020-07-30', 1);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Daisy', 'Perro', 'Golden Retriever', 28.7, '2021-04-18', 2);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Cooper', 'Perro', 'Boxer', 25.0, '2022-09-12', 3);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Buddy', 'Perro', 'Chihuahua', 2.3, '2023-06-01', 1);
