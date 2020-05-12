<template>
  <div class="lobby">
    <h1>Game Lobby</h1>
    <new-game :currentUser="currentUser" @newGame="populateGames"></new-game>
    <game-list title="Open Games" :games="openGames"></game-list>
    <game-list title="Pending Games" :games="pendingGames"></game-list>
  </div>
</template>

<script>
import NewGame from '@/components/NewGame'
import GameList from '@/components/GameList'
import auth from '../auth'
import api from '../api'

export default {
  name: 'lobby',
  props: ['currentUser'],
  components: {
    NewGame,
    GameList
  },
  data() {
    return {
      pendingGames: [],
      openGames: []
    }
  },
  methods: {
    populateGames() {
      const authToken = auth.getToken();
      const fetchConfigGet = api.fetchConfigGet(authToken);

      fetch(`${process.env.VUE_APP_REMOTE_API}/allGames`, fetchConfigGet
      )
      .then((response) => {
        return response.json();
      })
      .then((games) => {
        console.log('games: ' + games);
        const pendingGames = games.filter(game => game.president == 0);
        const activeGames = games.filter(game => game.president != 0);

        this.pendingGames = pendingGames;
        this.activeGames = activeGames;
      });      
    }
  },
  created() {
      this.currentUser = this.$parent.currentUser;

      this.populateGames();
  }
}
</script>