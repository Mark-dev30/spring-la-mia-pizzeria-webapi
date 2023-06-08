<script >
import axios from "axios";
export default {
  name: 'HomePage',
  data() {
    return {
      name: "",
      urlpizze: "http://localhost:8080/api/v1/",
      pizze: [],
      newpizze: []

    }
  },
  methods: {
    getPizze() {
      axios.get(this.urlpizze + "pizze").then(res => {
        this.pizze = res.data;;
      }).catch(err => console.log(err));
    },
    deletePizza(id) {
      axios.delete(this.urlpizze + "pizza/" + id)
        .then(re => console.log(res.data)).catch(err => console.log(err));
    },
    searchPizze() {

      axios.get(this.urlpizze + "pizze?name=" + this.name)
        .then(res => {
          this.pizze = res.data;
        }).catch(err => console.log(err));

    }
  },
  mounted() {
    this.getPizze();
  },

}
</script>

<template>
  <router-link :to="{ name: 'create' }"><a>Create Pizza</a></router-link>
  <h1>Pizze:</h1>
  <input type="text" v-model="this.name">
  <button @click="searchPizze()">Search</button>
  <ul>
    <li v-for="pizza in pizze">
      {{ pizza.name }}
      <button @click="deletePizza(pizza.id)">Delete</button>
    </li>
  </ul>
</template>

<style scoped></style>
