<template>
    <div id="nomination-list">
        <h3>Chancellor Nominees</h3>
        <ul>
            <li v-for="nominee in nominees" :key="nominee.id" @click="nominate(nominee.id)">
                {{nominee.username}}
            </li>
        </ul>
    </div>
</template>

<script>
import auth from '../auth'
import api from '../api'

export default {
    name: 'nomination-list',
    props: ['currentUser'],
    data() {
        return {
            nominees: null
        }
    },
    methods: {
        getNominees() {
            const authToken = auth.getToken();
            const fetchConfigGet = api.fetchConfigGet(authToken);
            const gameId = this.$route.params.gameId;

            fetch(`${process.env.VUE_APP_REMOTE_API}/usersInGame/${gameId}`, fetchConfigGet)
            .then((response) => {
                return response.json();
            })
            .then((nominees) => {
                this.nominees = nominees.filter((player) => {
                    return player.id != this.currentUser.id;
                })
            });
        },
        nominate(nomineeId) {
            const authToken = auth.getToken();
            const fetchConfigPut = api.fetchConfigPut(authToken);
            const gameId = this.$route.params.gameId;

            fetch(`${process.env.VUE_APP_REMOTE_API}/nominateChancellor/${gameId}/${nomineeId}`, fetchConfigPut);            
        }
    },
    created() {
        this.getNominees();
    }
}
</script>

<style>

</style>