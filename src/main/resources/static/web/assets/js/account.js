const app = Vue.createApp({

    data() {
        return {
            /* dato: null, */
            cuentas: [],
            transacciones: [],
            id: (new URLSearchParams(location.search)).get("id"),
            url: '/api/accounts/'
        }
    },
    created() {
        this.loadData(this.url + this.id);
    },
    methods: {
        loadData(url) {
            axios.get(url)
                .then((response) => {
                    this.cuentas = response.data;
                    console.log(this.cuentas);
                    this.transacciones = response.data.transaction;
                    console.log(this.transacciones);
                })
        }
    }


}).mount('#app');