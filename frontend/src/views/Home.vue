<template>
  <div id="home">
    <game-list title="Active Games" :games="activeGames" :currentUser="currentUser"></game-list>
    <register v-if="this.currentUser.role == 'admin'"></register>
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
    this.getActiveGames();
  }
}
</script>

<style>
#home > * {
  margin: 2rem;
}
</style>