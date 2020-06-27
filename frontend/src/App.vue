<template>
  <div id="app">
    <header id="nav">
      <router-link to="/">Home</router-link>
      <router-link to="/lobby">Game Lobby</router-link>
      <a v-on:click="logOut">Log Out</a>
      <p v-if="this.currentUser != null">{{currentUser.username}}</p>
    </header>
    <router-view v-if="currentUser.id || this.$route.fullPath == '/login'" :currentUser="currentUser" @loggedIn="loggedIn"/>
    <footer id="footer">
    Icons made by <a href="http://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
    </footer>
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
        id: null      
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

*,
*::before,
*::after {  
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  min-height: 100vh;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

#nav {
  width: 100;
  
  display: flex;
  justify-content: space-evenly;
}



</style>