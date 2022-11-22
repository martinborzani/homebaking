const app = Vue.createApp({

    data() {
        return {


            loanType: "",
            maxDues: 0,
            maxAmount: 0,



        }
    },
    created() {


    },
    methods: {

        createLoan() {


            if (this.maxDues == 0 || this.maxAmount == 0 || this.loanType == "") {
                Swal.fire({
                    icon: 'error',
                    title: 'Algo salio mal',
                    text: 'Llene todos los parametros'
                })
            } else {
                Swal.fire({
                    title: '¿Estas Seguro de crear el prestamo?',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '¡Crear!'
                }).then((result) => {

                    if (result.isConfirmed) {
                        axios.post('/api/newLoan', "loanType=" + this.loanType + "&maxDues=" + this.maxDues + "&maxAmount=" + this.maxAmount)
                            .then(() => {

                                Swal.fire({
                                        position: 'top-end',
                                        icon: 'success',
                                        title: 'Prestamo creado con exito',
                                        showConfirmButton: false,
                                        timer: 1500
                                    })
                                    .then(() => {
                                        window.location.href = '/web/createnewloan.html'
                                    })

                            })
                            .catch(error => {

                                Swal.fire({
                                    icon: 'error',
                                    title: 'Algo salio mal',
                                    text: error.response.data,
                                })

                            })

                    }
                })
            }


        }
    },
    computed: {

    }
}).mount('#app');