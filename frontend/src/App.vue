<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      <router-link to="/newGame">Create a New Game</router-link>
      <a v-on:click="logOut">Log Out</a>
    </div>
    <router-view v-on:loggedIn="loggedIn"/>
  </div>
</template>

<script>
import auth from './auth'

export default {
  name: 'app',
  data() {
    return {
      currentUser: null
    }
  },
  methods: {
    loggedIn() {
      this.currentUser = auth.getUser();
    },
    logOut() {
      auth.logout();
      this.$router.push('/login');
    }
  },
  created() {
    this.loggedIn();
  }  
}
</script>

<style>

#nav {
  width: 100;
  
  display: flex;
  justify-content: space-evenly;
}

</style>