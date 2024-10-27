document.getElementById('loginForm').addEventListener('submit', function(event) {
    const email = document.getElementsByName('username')[0].value;
    const password = document.getElementsByName('password')[0].value;

    if (email === '' || password === '') {
        event.preventDefault();
        Swal.fire({
            title: "Error",
            text: "Por favor, completa todos los campos.",
            icon: "error"
        });
    }
});
