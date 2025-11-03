document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registroForm');
    const inputs = form.querySelectorAll('input');
    
   
    function validateField(input) {
     
        const errorElement = document.getElementById(`error-${input.id}`);
     
        input.classList.remove('input-error', 'input-success');
        errorElement.textContent = '';

        if (input.validity.valid) {
         
            input.classList.add('input-success');
            return true;
        } else {
          
            input.classList.add('input-error');
            
         
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


    function validatePasswordMatch() {
        const password = document.getElementById('password');
        const confirmPassword = document.getElementById('confirm-password');
        const errorElement = document.getElementById('error-confirm-password');

    
        let isConfirmValid = validateField(confirmPassword);
        if (isConfirmValid && password.value !== confirmPassword.value) {
            confirmPassword.classList.add('input-error');
            confirmPassword.classList.remove('input-success');
            errorElement.textContent = 'Las contraseñas no coinciden.';
            return false;
        }
    
        return isConfirmValid && password.value === confirmPassword.value;
    }

    inputs.forEach(input => {
        input.addEventListener('blur', () => {
            if (input.id === 'confirm-password') {
                validatePasswordMatch();
            } else {
                validateField(input);
            }
        });
    });
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        
        let formIsValid = true;
        
        inputs.forEach(input => {

            if (input.id === 'confirm-password') {
                if (!validatePasswordMatch()) {
                    formIsValid = false;
                }
            } else if (!validateField(input)) {
    
            }
        });


        if (formIsValid) {
            this.submit(); 
        } else {

            document.getElementById('general-error').textContent = 'Por favor, corrige los errores en el formulario.';
        }
    });
});