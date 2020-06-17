<template>
  <div class="game-list">
    <h2>{{this.title}}</h2>
    <div v-for="game in games" :key="game.name" class="game">
      <p>{{game.name}}</p>
      <button v-if="title == 'Open Games'" v-on:click="joinGame(game.gameId)">Join</button>
    </div>
  </div>
</template>

<script>
import auth from '../auth'

export default {
  name: 'game-list',
  props: ['currentUser', 'title', 'games'],
  methods: {
    joinGame(gameId) {
      const userId = this.currentUser.id;
      const authToken = auth.getToken();
      
      fetch(`${process.env.VUE_APP_REMOTE_API}/joinGame/${gameId}/${userId}`,             
        { 
          method: 'POST',
          headers: {
              Authorization: `Bearer ${authToken}`,
              Accept: 'application/json',
              'Content-Type': 'application/json'
          }
        }
      )
      .then((response) => {
        if (response.ok) {
          this.$emit("joinGame");
          this.$router.push({ path: '/lobby', query: { joinGame: 'success' } });
        }
      })
      .then((err) => console.error(err));
    }
  }
}
</script>