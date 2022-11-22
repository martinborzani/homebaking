const app = Vue.createApp({

    data() {
        return {

            id: 0,
            amount: 0,
            payment: 0,
            numberAccount: "",
            urlPrestamo: "http://localhost:8080/api/loans",
            urlAccounts: "http://localhost:8080/api/clients/current",
            loans: [],
            accountClient: [],
            nameLoan: "",
            loanChecked: null,
            maxPayment: null,
            maxAmount: 0,
            containLoan: null,
            totalAmount: 0,




        }
    },
    created() {
        this.loadData(this.urlPrestamo);
        this.loadAccount(this.urlAccounts);
    },
    methods: {

        loadData(url) {
            axios.get(url)
                .then((response) => {
                    this.loans = response.data;

                    console.log(this.loans);




                })
        },
        loadAccount(url) {
            axios.get(url)
                .then((response) => {
                    this.clientes = response.data;

                    console.log(this.clientes);

                    this.accountClient = this.clientes.account;


                })
        },
        createLoan() {

            this.containLoan = this.clientes.clientLoan.find(loan => loan.idLoan == this.id);

            if (this.nameLoan == "Hipotecario") {
                this.totalAmount = this.amount * 1.20;
            } else if (this.nameLoan == "Personal") {
                this.totalAmount = this.amount * 1.10;
            } else {
                this.totalAmount = this.amount * 1.15;
            }

            if (this.id == 0 || this.amount <= 0 || this.payment == 0 || this.numberAccount == "") {
                Swal.fire({
                    icon: 'error',
                    title: 'Algo salio mal',
                    text: 'Llene todos los parametros'
                })
            } else if (this.containLoan !== undefined) {
                Swal.fire({
                    icon: 'error',
                    title: 'Algo salio mal',
                    text: 'Ya has pedido el prestamo asignado'
                })
            } else {
                Swal.fire({
                    title: '¿Estas Seguro de sacar el prestamo?',
                    icon: 'warning',
                    text: `tendrias que pagar $ ${this.totalAmount}`,
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '¡Solicitar!'
                }).then((result) => {

                    if (result.isConfirmed) {
                        axios.post('/api/loans', { id: this.id, amount: this.amount, payment: this.payment, numberAccount: this.numberAccount })
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
    },
    computed: {
        getLoan() {

            this.loanChecked = this.loans.find(loan => loan.nameLoan == this.nameLoan);


            if (this.loanChecked !== undefined) {

                this.id = this.loanChecked.id;
                this.maxPayment = this.loanChecked.payments;
                this.maxAmount = this.loanChecked.maxAmount;



            }

        }
    }
}).mount('#app');