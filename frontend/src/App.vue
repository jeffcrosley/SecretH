<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
      <router-link to="/lobby">Game Lobby</router-link>
      <a v-on:click="logOut">Log Out</a>
    </div>
    <router-view :currentUser="currentUser" @loggedIn="loggedIn"/>
    Icons made by <a href="http://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
  </div>
</template>

<script>
import auth from './auth'
import api from './api'

export default {
  name: 'app',
  data() {
    return {
      currentUser: {
        username: '',
        id: ''
      }
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
        this.currentUser.username = currentUser.username;
        this.currentUser.id = currentUser.id;
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