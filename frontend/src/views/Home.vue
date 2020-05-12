<template>
  <div class="home">
    <h1>Home</h1>
    <p>Your name is {{currentUser.username}}</p>
    <p>You must be authenticated to see this</p>
    <p v-if="this.currentUser.role == 'admin'">You must be an administrator to see this</p>
  </div>
</template>

<script>
export default {
  name: 'home',
  props: ['currentUser']
}
</script>
