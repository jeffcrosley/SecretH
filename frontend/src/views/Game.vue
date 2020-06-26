<template>
  <div id="game">
    <div class="game__info">
      {{currentGame.name}} / 
      Your Role: {{currentPlayerRole}} / 
      Players: {{currentGame.numberOfPlayers}} / 
      Sheep Policies: {{currentGame.sheepPolicies}} /
      Wolf Policies: {{currentGame.wolfPolicies}} /
      President: {{currentGame.presidentName}} / 
      Chancellor: {{currentGame.chancellorName}}
    </div>
    <nomination-list v-if="currentUser.id == currentGame.presidentId" :currentUser="currentUser"></nomination-list>
  </div>
</template>

<script>
import NominationList from '@/components/NominationList'
import auth from '../auth'
import api from '../api'

export default {
    name: 'game',
    components: {
      NominationList
    },
    props: ['currentUser'],
    data() {
      return {
        currentGame: null,
        playersInGame: null,
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

        fetch(`${process.env.VUE_APP_REMOTE_API}/usersInGame/${gameId}`, fetchConfigGet)
        .then((response) => {
          return response.json();
        })
        .then((players) => {
          this.playersInGame = players;
        });
      }
    },
    created() {
      this.gameSetup();
    }
}
</script>

<style scoped>
#game > * {
  margin: 2rem;
}
</style>