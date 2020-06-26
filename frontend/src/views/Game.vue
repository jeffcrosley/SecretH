<template>
  <div id="game">
      {{currentGame.name}} / 
      Your Role: {{currentPlayerRole}} / 
      Players: {{currentGame.numberOfPlayers}} / 
      Sheep Policies: {{currentGame.sheepPolicies}} /
      Wolf Policies: {{currentGame.wolfPolicies}} /
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
        const userId = this.$props.currentUser.id;
        let presidentId = null;
        let chancellorId = null;

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
          presidentId = this.currentGame.presidentId;
          return fetch(`${process.env.VUE_APP_REMOTE_API}/userId/${presidentId}`, fetchConfigGet)
        })
        .then((response) => {
          return response.json();
        })
        .then((president) => {
          this.currentGame.presidentName = president.username;
          chancellorId = this.currentGame.chancellorId;
          return fetch(`${process.env.VUE_APP_REMOTE_API}/userId/${chancellorId}`, fetchConfigGet)
        })
        .then((response) => {
          return response.json();
        })
        .then((chancellor) => {
          this.currentGame.chancellorName = chancellor.username;
        })
        .catch((reason) => {
          console.log("Something went wrong with the fetches: " + reason);
        });
      }
    },
    created() {
      this.gameSetup();
    }
}
</script>

<style>

</style>