const app = Vue.createApp({
    data() {
        return {

            url: "https://alpha-vantage.p.rapidapi.com/query",
            datos: [],


        }
    },
    created() {
        this.loadData(this.url);
    },
    methods: {

        loadData(url) {
            axios.get(url)
                .then((response) => {
                    console.log(response.data);
                })
        }

    },
    params: {
        interval: '5min',
        function: 'TIME_SERIES_INTRADAY',
        symbol: 'MSFT',
        datatype: 'json',
        output_size: 'compact'
    },
    headers: {
        'X-RapidAPI-Key': '35eacebc5cmsh0fbe89edade71b5p1b55f5jsn76c554bcfdf6',
        'X-RapidAPI-Host': 'alpha-vantage.p.rapidapi.com'
    }
}).mount('#app');