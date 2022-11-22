const app = Vue.createApp({
    data() {
        return {

            amount: null,
            description: "",
            accountOrigin: "",
            accountDestiny: "",
            statusAccount: "",
            url: "http://localhost:8080/api/clients/current",
            cuentasCliente: [],
            balanceAccount: null,
            accountFound: null,
            nameAccoutFound: "",
            accountExist: [],

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

                    this.cuentasCliente = this.clientes.account;

                    this.accountExist = this.cuentasCliente.filter(accounts => accounts.enable == true);

                })
        },
        accountBalance() {

            this.accountFound = this.cuentasCliente.find(accounts => accounts.number == this.accountOrigin);

            if (this.accountOrigin != "") {

                this.balanceAccount = this.accountFound.balance;


            }

            return this.balanceAccount;

        },
        createTrasference() {


            if (this.amount <= 0 || this.description == "" || this.accountOrigin == "" || this.accountDestiny == "") {
                Swal.fire({
                    icon: 'error',
                    title: 'Algo salio mal',
                    text: 'Llene todos los parametros'
                })
            } else {

                Swal.fire({
                    title: '¿Estas Seguro de transferir?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Transferir!'
                }).then((result) => {

                    if (result.isConfirmed) {
                        axios.post('/api/transactions', 'amount=' + this.amount + '&description=' + this.description + '&accountOrigin=' + this.accountOrigin + '&accountDestiny=' + this.accountDestiny.toUpperCase())
                            .then(() => {

                                Swal.fire({
                                        position: 'top-end',
                                        icon: 'success',
                                        title: 'Transaccion echa con exito',
                                        showConfirmButton: false,
                                        timer: 1500
                                    })
                                    .then(() => {
                                        window.location.href = '/web/accounts.html'
                                    })

                            })
                            .catch(error => {

                                Swal.fire({
                                        icon: 'error',
                                        title: 'Algo salio mal',
                                        text: error.response.data,
                                    })
                                    /*  console.log([error]); */
                            })

                    }
                })
            }






        }

    }
}).mount('#app');