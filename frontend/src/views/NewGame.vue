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
  props: ['currentUser'],
  data() {
    return {
      game: {
        organizerId: null,
        name: '',
        numberOfPlayers: ''
      }
    }
  },
  created() {
    this.organizerId = this.currentUser.id;
  },
  methods: {
    createGame() {
      
    }
  }
}
</script>

<style>

</style>