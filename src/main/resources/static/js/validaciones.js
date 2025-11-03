document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registroForm');
    const inputs = form.querySelectorAll('input');
    
    // Función central para validar un campo específico
    function validateField(input) {
        // Obtenemos el elemento <span> del mensaje de error
        const errorElement = document.getElementById(`error-${input.id}`);
        // Removemos clases previas
        input.classList.remove('input-error', 'input-success');
        errorElement.textContent = '';

        if (input.validity.valid) {
            // Si el campo es válido según HTML5
            input.classList.add('input-success');
            return true;
        } else {
            // Si el campo NO es válido
            input.classList.add('input-error');
            
            // Lógica para mostrar mensajes personalizados
            if (input.validity.valueMissing) {
                errorElement.textContent = 'Este campo es obligatorio.';
            } else if (input.validity.typeMismatch && input.type === 'email') {
                errorElement.textContent = 'El formato del email no es válido.';
            } else if (input.validity.tooShort) {
                errorElement.textContent = `Debe tener al menos ${input.minLength} caracteres.`;
            } else {
                errorElement.textContent = 'El valor ingresado no es válido.';
            }
            
            return false;
        }
    }

    // Función de validación especial para la confirmación de contraseña
    function validatePasswordMatch() {
        const password = document.getElementById('password');
        const confirmPassword = document.getElementById('confirm-password');
        const errorElement = document.getElementById('error-confirm-password');

        // Primero validamos si el campo de confirmación es válido por sí mismo (e.g., no vacío)
        let isConfirmValid = validateField(confirmPassword);

        // Luego verificamos la coincidencia
        if (isConfirmValid && password.value !== confirmPassword.value) {
            confirmPassword.classList.add('input-error');
            confirmPassword.classList.remove('input-success');
            errorElement.textContent = 'Las contraseñas no coinciden.';
            return false;
        }
        
        // Si el campo es válido y coinciden, o si ya fue marcado como error por la validación inicial,
        // la función validateField ya maneja el estado de éxito o error.
        return isConfirmValid && password.value === confirmPassword.value;
    }

    // 1. Validar al perder el foco (blur)
    inputs.forEach(input => {
        input.addEventListener('blur', () => {
            if (input.id === 'confirm-password') {
                validatePasswordMatch();
            } else {
                validateField(input);
            }
        });
    });

    // 2. Validar al intentar enviar el formulario
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Detenemos el envío inicial
        
        let formIsValid = true;
        
        // Ejecutar validación en todos los campos
        inputs.forEach(input => {
            // Para la confirmación de contraseña usamos la función especial
            if (input.id === 'confirm-password') {
                if (!validatePasswordMatch()) {
                    formIsValid = false;
                }
            } else if (!validateField(input)) {
                // Para el resto de campos
                formIsValid = false;
            }
        });

        // Si todos los campos son válidos, enviamos el formulario
        if (formIsValid) {
            this.submit(); // Envía el formulario al endpoint de Spring Boot
        } else {
            // Opcional: mostrar un mensaje general si el formulario falla
            document.getElementById('general-error').textContent = 'Por favor, corrige los errores en el formulario.';
        }
    });
});