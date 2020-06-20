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
        });
      }

    },
    watch: {
      currentUser: function() {
        this.gameSetup();
      }
    },
    created() {
      this.gameSetup();      
    }
}
</script>

<style>

</style>