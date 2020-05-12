<template>
  <div class="lobby">
    <h1>Game Lobby</h1>
    <new-game></new-game>
    <game-list title="Active Games" :games="this.activeGames"></game-list>
    <game-list title="Pending Games" :games="this.pendingGames"></game-list>
  </div>
</template>

<script>
import NewGame from '@/components/NewGame'
import GameList from '@/components/GameList'

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
  created() {
      this.currentUser = this.$parent.currentUser;
  }
}
</script>