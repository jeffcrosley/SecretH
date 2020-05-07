<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>
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

