<template>
  <div id="ballot" class="ballot">
      <button class="ballot__button ballot__button--yes" @click="voteYes">APPROVE</button>
      <button class="ballot__button ballot__button--no" @click="voteNo">REJECT</button>
  </div>
</template>

<script>
import auth from '../auth'
import api from '../api'

export default {
  name: 'ballot',
  props: ['currentUser'],
  methods: {
    voteYes() {
      const authToken = auth.getToken();
      const fetchConfigPut = api.fetchConfigPut(authToken);
      const gameId = this.$route.params.gameId;
      const userId = this.$props.currentUser.id;

      fetch(`${process.env.VUE_APP_REMOTE_API}/vote/${gameId}/${userId}/true`, fetchConfigPut);
    },
    voteNo() {
      const authToken = auth.getToken();
      const fetchConfigPut = api.fetchConfigPut(authToken);
      const gameId = this.$route.params.gameId;
      const userId = this.$props.currentUser.id;

      fetch(`${process.env.VUE_APP_REMOTE_API}/vote/${gameId}/${userId}/false`, fetchConfigPut);

    }
  }
}
</script>

<style lang="scss">

.ballot {

  &__button {
    color: white;

    &--yes{
      background-color: green;
    }

    &--no{
      background-color: red;
    }
  }
}

</style>