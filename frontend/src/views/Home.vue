<template>
  <div class="home">
    <h1>Home</h1>
    <p>Your name is {{currentUser.username}}</p>
    <p>You must be authenticated to see this</p>
    <p v-if="this.currentUser.role == 'admin'">You must be an administrator to see this</p>
    <game-list title="Active Games" :games="activeGames"></game-list>

  </div>
</template>

<script>
import GameList from '@/components/GameList'
import auth from '../auth'
import api from '../api'

export default {
  name: 'home',
  props: ['currentUser'],
  components: {
    GameList
  },
  data() {
    return {
      activeGames: []
    }
  },
  methods: {
    getActiveGames() {
      const authToken = auth.getToken();
      const fetchConfigGet = api.fetchConfigGet(authToken);
      // THIS LINE IS THE ONE THAT'S MAKING THE TROUBLE
      const userId = this.currentUser.id;

      fetch(`${process.env.VUE_APP_REMOTE_API}/activeGames/${userId}`, fetchConfigGet
      )
      .then((response) => {
        return response.json();
      })
      .then((games) => {
        this.activeGames = games;
      });
    }
  },
  created() {
    this.currentUser = this.$parent.currentUser;

    this.getActiveGames();
  }
}
</script>
