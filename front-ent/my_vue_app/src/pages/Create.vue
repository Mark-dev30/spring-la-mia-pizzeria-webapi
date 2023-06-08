<script>
import axios from "axios";
export default {
    data() {
        return {
            urlpizze: "http://localhost:8080/api/v1/",
            pizza: {
                name: "",
                description: "",
                photo: "ghgh",
                price: ""
            }
        }
    },
    methods: {
        createPizza() {
            console.log(JSON.stringify(this.pizza));
            axios.post(this.urlpizze + "pizze", this.pizza).then(res => {
                this.$router.push("/");
            }).catch(err => console.log(err));
        }
    },
    mounted() {

    },
}
</script>
<template >
    <h1>Create Pizza</h1>
    <form @submit.prevent="createPizza()">
        <div>
            <label>Name</label>
            <input type="text" name="name" v-model="pizza.name">
        </div>
        <div>
            <label>Description</label>
            <input type="text" name="name" v-model="pizza.description">
        </div>
        <div>
            <label>Price</label>
            <input type="number" name="name" v-model="pizza.price">
        </div>
        <input type="submit" value="CREATE">
    </form>
</template>

<style scoped></style>