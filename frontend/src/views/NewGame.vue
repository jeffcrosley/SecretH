<template>
  <div id="new-game">
    <h1>Create a New Game</h1>
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
        <label for="numberOfPlayer" class="label">Number of Players</label>
        <input
        type="number"
        id="numberOfPlayers"
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
export default {
  name: 'create-game',
  data() {
    return {
      game: {
        organizer: this.$parent.currentUser.sub,
        name: '',
        numberOfPlayers: ''
      }
    }
  },
  methods: {
    createGame() {
      
    }
  }
}
</script>

<style>

</style>