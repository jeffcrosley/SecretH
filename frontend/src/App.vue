<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      <router-link to="/newGame">Create a New Game</router-link>
      <a v-on:click="logOut">Log Out</a>
    </div>
    <router-view v-on:loggedIn="loggedIn" v-bind:currentUser="currentUser"/>
  </div>
</template>

<script>
import auth from './auth'
import api from './api'

export default {
  name: 'app',
  data() {
    return {
      currentUser: null
    }
  },
  methods: {
    loggedIn() {

      const username = auth.getUser().sub;
      const authToken = auth.getToken();
      const fetchConfigGet = api.fetchConfigGet(authToken);
      
      fetch(`${process.env.VUE_APP_REMOTE_API}/user/${username}`, fetchConfigGet
      )
      .then((response) => {
        return response.json();
      })
      .then((currentUser) => {
        this.currentUser = currentUser;
      });

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