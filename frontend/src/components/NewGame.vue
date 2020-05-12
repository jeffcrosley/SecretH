<template>
  <div id="new-game">
    <h2>Create a New Game</h2>
    <form class="form" @submit.prevent="createGame">
      <div class="form-group">
        <label for="name" class="label">Game Name</label>
        <input
        type="text"
        id="name"
        v-model="game.name"
        required
        autofocus
        />
      </div>
      <div class="form-group">
        <label for="numberOfPlayer" class="label">Number of Players (5-10)</label>
        <input
        type="number"
        id="numberOfPlayers"
        min=5
        max=10
        v-model="game.numberOfPlayers"
        required
        />
      </div>
      <button id="create-game" class="button" type="submit">
        Create game
      </button>              
    </form>
  </div>
</template>

<script>
import auth from '../auth'
import api from '../api'

export default {
  name: 'create-game',
  props: ['currentUser'],
  data() {
    return {
      game: {
        name: '',
        numberOfPlayers: 0
      }
    }
  },
  methods: {
    createGame() {
      const creatorId = this.currentUser.id;
      console.log("creatorId = " + creatorId);
      const authToken = auth.getToken();
      const fetchConfig = api.fetchConfigPostNewGame(authToken);
      
      fetch(`${process.env.VUE_APP_REMOTE_API}/game/${creatorId}`,             
        { 
          method: 'POST',
          headers: {
              Authorization: `Bearer ${authToken}`,
              Accept: 'application/json',
              'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.game)
        }
      )
      .then((response) => {
        if (response.ok) {
          this.$emit("newGame");
          this.$router.push({ path: '/lobby', query: { createGame: 'success' } });
        }
      })
      .then((err) => console.error(err));
    }
  }
}
</script>

<style>

</style>