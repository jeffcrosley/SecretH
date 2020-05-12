<template>
  <div class="lobby">
    <h1>Game Lobby</h1>
    <new-game></new-game>
  </div>
</template>

<script>
import NewGame from '@/components/NewGame'

export default {
  name: 'lobby',
  props: ['currentUser'],
  components: {
    NewGame
  },
  created() {
      this.currentUser = this.$parent.currentUser;
  }
}
</script>