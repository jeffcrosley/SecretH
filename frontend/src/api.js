export default {

    fetchConfigGet(authToken) {
        return {
            method: 'GET',
            headers: {
                Authorization: `Bearer ${authToken}`
            }
        }
    },

    fetchConfigPostNewGame(authToken) {
        return {
            method: 'POST',
            headers: {
                Authorization: `Bearer ${authToken}`,
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.game)
        }
    },

    fetchConfigPut(authToken) {
        return {
            method: 'PUT',
            headers: {
                Authorization: `Bearer ${authToken}`
            }
        }
    },
}