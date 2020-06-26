<template>
  <div id="game">
      {{currentGame.name}} / 
      Your Role: {{currentPlayerRole}} / 
      {{currentGame.numberOfPlayers}} Players / 
      {{currentGame.sheepPolicies}} Sheep Policies /
      {{currentGame.wolfPolicies}} Wolf Policies /
      President: {{currentGame.presidentName}} / 
      Chancellor: {{currentGame.chancellorName}}
  </div>
</template>

<script>
import auth from '../auth'
import api from '../api'

export default {
    name: 'game',
    props: ['currentUser'],
    data() {
      return {
        currentGame: null,
        currentPlayerRole: ''
      }
    },
    methods: { 
      gameSetup() {
        const authToken = auth.getToken();
        const fetchConfigGet = api.fetchConfigGet(authToken);
        const gameId = this.$route.params.gameId;
        const userId = this.currentUser.id;

        fetch(`${process.env.VUE_APP_REMOTE_API}/userRole/${gameId}/${userId}`, fetchConfigGet)
        .then((response) => {
          return response.text();
        })
        .then((userRole) => {
          this.currentPlayerRole = userRole;
          return fetch(`${process.env.VUE_APP_REMOTE_API}/game/${gameId}`, fetchConfigGet)
        })
        .then((response) => {
          return response.json();
        })
        .then((game) => {
          this.currentGame = game;
          return fetch(`${process.env.VUE_APP_REMOTE_API}/userId/${userId}`, fetchConfigGet)
        })
        .then((response) => {
          return response.json();
        })
        .then((president) => {
          this.currentGame.presidentName = president.username;
          return fetch(`${process.env.VUE_APP_REMOTE_API}/userId/${this.currentGame.presidentId}`, fetchConfigGet)
        })
        .then((response) => {
          return response.json();
        })
        .then((chancellor) => {
          this.chancellorName = chancellor.username;
        })
        .catch((reason) => {
          console.log("Something went wrong with the fetches: " + reason);
        });
      }
    },
    watch: {
      currentUser: function() {

      }
    },
    created() {
      setTimeout(this.gameSetup(), 5000);
    }
}
</script>

<style>

</style>