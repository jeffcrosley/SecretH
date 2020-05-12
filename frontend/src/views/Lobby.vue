<template>
  <div class="lobby">
    <h1>Lobby</h1>
  </div>
</template>

<script>
export default {
  name: 'lobby',
  props: ['currentUser'],
  created() {
      this.currentUser = this.$parent.currentUser;
  }
}
</script>