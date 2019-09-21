<template>
  <div class="app-container">
    <nav-bar class="nav-bar"></nav-bar>
    <router-view class="content-container"></router-view>
  </div>
</template>

<script>
import NavBar from "./views/NavBar";

export default {
  components: {
    NavBar
  }
};
</script>

<style lang="scss">
@import "~@/styles/mixin.scss";

.app-container {
  @include clearfix;
  height: 100%;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

.nav-bar {
  width: 100%;
}

.content-container {
  height: 100%;
  width: 100%;
}
</style>