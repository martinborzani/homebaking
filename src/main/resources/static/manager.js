const app = Vue.createApp({
    data() {

        return {

            dato: null,
            clientes: [],
            url: "http://localhost:8080/api/clients",
            firstName: "",
            lastName: "",
            email: "",
            id: null,


        }

    },
    created() {
        this.loadData(this.url);
    },
    methods: {

        loadData(url) {
            axios.get(url)
                .then((response) => {
                    this.dato = response;
                    this.clientes = this.dato.data;
                    console.log(this.clientes);
                })

        },
        addClient() {
            let client = {
                firstName: this.firstName,
                lastName: this.lastName,
                email: this.email
            }

            this.postClient(client);

        },
        postClient(cliente) {
            axios.post(this.url, cliente).then(() => {
                this.loadData(this.url);
            })
        },
        eliminarCliente(clientes) {

            axios.delete(clientes.id).then(() => {
                this.loadData(this.url);
            })

        },
        modificarCliente(client) {

            this.firstName = client.firstName;
            this.lastName = client.lastName;
            this.email = client.email;
            this.id = client._links.cliente.href;

        },
        updateClient() {


            axios.put(this.id, { firstName: this.firstName, lastName: this.lastName, email: this.email }).then(() => {
                this.loadData(this.url);
            })

        },

    },
    computed: {

    }
}).mount('#app');