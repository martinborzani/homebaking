const app = Vue.createApp({

    data() {
        return {

            typeCard: "CREDITO",
            colorCard: "GOLD",

        }
    },
    created() {

    },
    methods: {

        createCard() {
            axios.post('/api/clients/current/cards', "cardType=" + this.typeCard + "&cardColor=" + this.colorCard)
                .then(() => {
                    Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: '¡Tarjeta creada con exito!',
                            showConfirmButton: false,
                            timer: 1700
                        })
                        .then(() => {
                            window.location.href = '/web/cards.html'
                        })
                })
        }

    }

}).mount('#app');