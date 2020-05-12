export default {

    fetchConfigGet(authToken) {
        return {
            method: 'GET',
            headers: {
                Authorization: `Bearer ${authToken}`
            }
        }
    }

}