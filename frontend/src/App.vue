<template>
    <v-app id="app">
        <router-view/>
    </v-app>
</template>

<script>
    export default {
        name: "App",
        data() {
            return {}
        }
    }
</script>

<style lang="scss">
    @import "assets/scss/global.scss";

    body {
        margin: 0 auto !important;
    }
</style>