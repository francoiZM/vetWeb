/* insertar todos los datos de tabla de usuarios private Long id;
    private String rut;
        private String nombre;
        private String apellido;
        private String email;
        private String password;
        private String rol;*/

-- crear insert para roles
        INSERT INTO roles (nombre, descripcion) VALUES ('ROLE_ADMIN', 'Administrador del sistema');
        INSERT INTO roles (nombre, descripcion) VALUES ('ROLE_TUTOR', 'Tutor de mascotas');
        INSERT INTO roles (nombre, descripcion) VALUES ('ROLE_VETERINARIO', 'Veterinario del sistema');

-- insertar datos de tabla de usuarios
    --contraseña 1  --> password -->  $2a$10$JTtCtBaFrKZPzzdhIadJOeQdxH6CSAtrszbqgn6v831Kc4VmXOV4C
    
        INSERT INTO usuarios (rut, nombre, apellido, email, password, activo) VALUES ('12345678-9', 'franco', 'zuniga', 'prueba@prueba.com', '$2a$10$JTtCtBaFrKZPzzdhIadJOeQdxH6CSAtrszbqgn6v831Kc4VmXOV4C', true);
        INSERT INTO usuarios (rut, nombre, apellido, email, password, activo) VALUES ('98765432-1', 'Monkey', 'D. luffy', 'onepiece@pirate.com', '$2a$10$JTtCtBaFrKZPzzdhIadJOeQdxH6CSAtrszbqgn6v831Kc4VmXOV4C', true);
        INSERT INTO usuarios (rut, nombre, apellido, email, password, activo) VALUES ('11223344-5', 'Paula', 'Torres', 'paulavet@vet.com', '$2a$10$JTtCtBaFrKZPzzdhIadJOeQdxH6CSAtrszbqgn6v831Kc4VmXOV4C', true);


    -- asignaro roles a usuarios
        INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (1, 1);
        INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (2, 2);
        INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (3, 3);

        /*insertar datos de tabla de mascotas*/
        /*ahora incluye el campo peso y fecha_nacimiento*/

        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Max', 'Perro', 'Labrador', 30.5, '2019-01-15', 1);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Luna', 'Gato', 'Siames', 3.8, '2021-03-20', 2);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Rocky', 'Perro', 'Bulldog', 22.0, '2020-05-10', 3);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Molly', 'Perro', 'Beagle', 10.2, '2022-08-25', 1);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Bella', 'Gato', 'Persa', 4.5, '2018-11-05', 2);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Charlie', 'Perro', 'Poodle', 6.1, '2023-02-14', 3);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Lucy', 'Gato', 'Maine Coon', 7.2, '2020-07-30', 1);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Daisy', 'Perro', 'Golden Retriever', 28.7, '2021-04-18', 2);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Cooper', 'Perro', 'Boxer', 25.0, '2022-09-12', 3);
        INSERT INTO mascotas (nombre, especie, raza, peso, fecha_nacimiento, usuario_id) VALUES ('Buddy', 'Perro', 'Chihuahua', 2.3, '2023-06-01', 1);
