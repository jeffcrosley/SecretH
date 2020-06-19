<template>
  <div class="home">
    <h1>Home</h1>
    <p>Your name is {{currentUser.username}}</p>
    <p>You must be authenticated to see this</p>
    <p v-if="this.currentUser.role == 'admin'">You must be an administrator to see this</p>
    <register v-if="this.currentUser.role == 'admin'"></register>
    <game-list title="Active Games" :games="activeGames" :currentUser="currentUser"></game-list>

  </div>
</template>

<script>
import GameList from '@/components/GameList'
import Register from '@/components/Register'
import auth from '../auth'
import api from '../api'

export default {
  name: 'home',
  props: ['currentUser'],
  components: {
    GameList,
    Register
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
      console.log(userId);

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
  watch: {
    currentUser: function() {
      this.getActiveGames();
    }
  },
  created() {
    this.getActiveGames();
  }
}
</script>
