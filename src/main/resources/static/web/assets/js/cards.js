const app = Vue.createApp({

    data() {
        return {

            datos: [],
            cards: [],
            cardsNotDelete: [],
            cardsDebit: [],
            cardsCredit: [],
            url: "http://localhost:8080/api/clients/current",
            typeCard: "CREDITO",
            colorCard: "GOLD",
            newDate: "",
            yearNewDate: "",
            dayNewData: "",
            prueba: "",

        }
    },
    created() {
        this.loadData(this.url);
    },
    methods: {

        loadData(url) {
            axios.get(url)
                .then((response) => {

                    this.datos = response.data;

                    this.cards = this.datos.card;
                    console.log(this.cards);

                    this.newDate = new Date();
                    this.yearNewDate = this.newDate.getFullYear();
                    this.prueba = this.yearNewDate.toString();

                    this.cardsNotDelete = this.cards.filter(card => card.enable == true);

                    this.cardsDebit = this.cardsNotDelete.filter(card => card.type === "DEBITO");
                    this.cardsCredit = this.cardsNotDelete.filter(card => card.type === "CREDITO");

                })
        },
        deleteCard(card) {
            console.log(card);


            Swal.fire({
                title: 'Estas seguro de eliminar?',
                text: "No podras volver a utilizar la tarjeta",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, Eliminar!'
            }).then((result) => {
                if (result.isConfirmed) {

                    axios.post('/api/clients/current/cards/delete?id=' + card.id)
                        .then(() => {
                            window.location.href = '/web/cards.html'
                        })


                }
            })


        }


    }

}).mount('#app');