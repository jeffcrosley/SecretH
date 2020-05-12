<template>
  <div class="game-list">
    <h2>{{this.title}}</h2>
    <div v-for="game in games" :key="game.name" class="game">
      <p>{{game.name}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'game-list',
  props: ['title', 'games']
}
</script>