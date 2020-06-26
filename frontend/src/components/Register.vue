<template>
  <form id="register" class="form-register" @submit.prevent="register">
    <h2 class="h3 mb-3 font-weight-normal">Create User</h2>
    <div class="alert alert-danger" role="alert" v-if="registrationErrors">
      There were problems registering this user.
    </div>
    <label for="username" class="sr-only">Username</label>
    <input
      type="text"
      id="username"
      class="form-control"
      placeholder="Username"
      v-model="user.username"
      required
      autofocus
    />
    <label for="password" class="sr-only">Password</label>
    <input
      type="password"
      id="password"
      class="form-control"
      placeholder="Password"
      v-model="user.password"
      required
    />
    <input
      type="password"
      id="confirmPassword"
      class="form-control"
      placeholder="Confirm Password"
      v-model="user.confirmPassword"
      required
    />
    <button class="btn btn-lg btn-primary btn-block" type="submit">
      Create Account
    </button>
  </form>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/', query: { registration: 'success' } });
          } else {
            this.registrationErrors = true;
          }
        })

        .then((err) => console.error(err));
    },
  },
};
</script>

<style>
</style>
