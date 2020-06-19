<template>
  <div id="game">
      GAME {{currentPlayerRole}}
  </div>
</template>

<script>
import auth from '../auth'
import api from '../api'

export default {
    name: 'game',
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

        

        fetch(`${process.env.VUE_APP_REMOTE_API}/userRole/${gameId}/${userId}`, fetchConfigGet)
        .then((response) => {
          return response.json();
        })
        .then((userRole) => {
          this.currentPlayerRole = userRole;
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