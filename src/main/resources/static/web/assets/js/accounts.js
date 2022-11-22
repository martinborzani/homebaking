const app = Vue.createApp({
    data() {


        return {

            clientes: null,
            url: "http://localhost:8080/api/clients/current",
            urlDolar: "https://api.estadisticasbcra.com/usd_of",
            cliente: [],
            nombreCliente: "",
            apellidoCliente: "",
            emailCliente: "",
            cuentasCliente: [],
            loan: [],
            cuentasClientesNotEnable: [],
            typeAccount: "",





        }
    },
    created() {
        this.loadData(this.url);
    },
    methods: {

        loadData(url) {
            axios.get(url)
                .then((response) => {
                    this.clientes = response.data;

                    console.log(this.clientes);

                    this.nombreCliente = this.clientes.firstName;

                    this.apellidoCliente = this.clientes.lastName;

                    this.emailCliente = this.clientes.email;

                    this.cuentasCliente = this.clientes.account;

                    this.loan = this.clientes.clientLoan;

                    this.cuentasClientesNotEnable = this.cuentasCliente.filter(account => account.enable == true);

                    console.log(this.cuentasClientesNotEnable);

                })
        },
        logoutClient() {
            axios.post('/api/logout').then(response => { window.location.href = '/web/index.html' })
        },
        createAccount() {



            axios.post('/api/clients/current/accounts', "typeAccount=" + this.typeAccount)
                .then(response => { window.location.href = '/web/accounts.html' })
                .catch(error => {

                    let problem = error.request.status;

                    if (problem == "403") {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error al crear una cuenta, ya tiene 3 cuentas creadas',

                        })

                    }

                })
        },
        deleteAccount(account) {
            console.log(account);


            Swal.fire({
                title: 'Estas seguro de eliminar?',
                text: "Perderas toda la informacion de la cuenta",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, Eliminar!'
            }).then((result) => {
                if (result.isConfirmed) {

                    axios.post('/api/clients/current/accounts/delete?numberAccount=' + account.number)
                        .then(() => {
                            window.location.href = '/web/accounts.html'
                        })


                }
            })
        },
    }
}).mount('#app');