const app = Vue.createApp({
    data() {
        return {
            email: "",
            password: "",
            firstName: "",
            lastName: "",
            emailRegistro: "",
            passwordRegistro: ""
        }
    },
    created() {

    },
    methods: {
        loginClient() {
            /* axios.post('/api/login', "email=" + this.email + "&password=" + this.password).then(() => window.location.href('/web/accounts.html')) */
            axios.post('/api/login', "email=" + this.email + "&password=" + this.password).then(response => { window.location.href = '/web/accounts.html' })
                .catch(error => {

                    let problem = error.request.status;

                    if (problem == "401") {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error al logear, verifique su email o contraseña!',
                        })

                    }

                })
        },
        logoutClient() {
            axios.post('/api/logout').then(response => { window.location.href = '/web/index.html' })
        },
        registerClient() {
            axios.post('/api/clients', "firstName=" + this.firstName + "&lastName=" + this.lastName + "&email=" + this.emailRegistro + "&password=" + this.passwordRegistro)
                .then(() => {


                    axios.post('/enviarCorreo', "contact=" + this.emailRegistro).then(() => {

                    });

                    Swal.fire({
                        title: 'Selecciona la fruta que se te mando por email',
                        input: 'select',
                        inputOptions: {
                            'Fruits': {
                                apples: 'Apples',
                                bananas: 'Bananas',
                                grapes: 'Grapes',
                                oranges: 'Oranges'
                            }
                        },
                        inputPlaceholder: 'Selecciona una fruta',
                        showCancelButton: true,
                        inputValidator: (value) => {
                            return new Promise((resolve) => {
                                if (value === 'oranges') {
                                    Swal.fire(
                                            'Te registraste con exito!',
                                            'Bienvenida a la familia DogeCoint Bank!',
                                            'success'
                                        )
                                        .then(() => {

                                            window.location.href = '/web/login.html'

                                        })
                                } else {
                                    resolve('Error');
                                    /* axios.post('/api/clients/delete', "email=" + this.emailRegistro)
                                        .then(() => {
                                            Swal.fire({
                                                icon: 'error',
                                                title: 'Oops...',
                                                text: 'Error en la seleccion, registrese de vuelta por favor',

                                            })
                                        })
                                        .then(() => {
                                            window.location.href = '/web/login.html'
                                        }) */
                                }
                            })
                        }
                    })


                })
                .catch(error => {

                    let problem = error.request.status;

                    if (problem == "403") {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error al registrarse, complete todos los campos o pruebe con email!',

                        })

                    }

                })
        }

    }
}).mount('#app');