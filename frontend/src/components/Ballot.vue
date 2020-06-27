<template>
  <div id="ballot" class="ballot">
      <button class="ballot__button ballot__button--yes">APPROVE</button>
      <button class="ballot__button ballot__button--no">REJECT</button>
  </div>
</template>

<script>
export default {

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