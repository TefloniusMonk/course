<template>
    <v-app id="main-page">
        <v-navigation-drawer
                v-model="drawer"
                app
        >
            <v-list dense>
                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-action>

                    <v-list-item-content
                            @click="()=>goToProfile()"
                    >
                        <v-list-item-title>Профиль</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item link>

                    <v-list-item-action>
                        <v-icon>mdi-pencil</v-icon>
                    </v-list-item-action>

                    <v-list-item-content
                            @click="()=>goToPlan()"
                    >
                        <v-list-item-title>Тренировочные планы</v-list-item-title>
                    </v-list-item-content>

                </v-list-item>
                <v-list-item link>

                    <v-list-item-action>
                        <v-icon>mdi-dumbbell</v-icon>
                    </v-list-item-action>

                    <v-list-item-content
                            @click="()=>goToTraining()"
                    >
                        <v-list-item-title>Тренировки</v-list-item-title>
                    </v-list-item-content>

                </v-list-item>
                <v-list-item link>

                    <v-list-item-action>
                        <v-icon>mdi-more</v-icon>
                    </v-list-item-action>

                    <v-list-item-content
                            @click="()=>goToExercise()"
                    >
                        <v-list-item-title>Список упражнений</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-app-bar
                hide-on-scroll
                app
                color="indigo"
                dark
        >
            <v-app-bar-nav-icon
                    scroll-target="#scrolling-techniques-5"
                    scroll-threshold="500"
                    @click.stop="drawer = !drawer"/>
            <v-toolbar-title>Application</v-toolbar-title>
        </v-app-bar>
        <v-layout>
            <v-content>
                <router-view/>
            </v-content>
        </v-layout>
        <v-footer
                color="indigo"
                app
        >
            <span class="white--text">&copy; 2019</span>
        </v-footer>
    </v-app>

</template>

<script>

    export default {
        name: "main-page",
        props: [],
        data() {
            return {
                drawer: null,
            }
        },
        watch: {},
        mounted() {
        },
        methods: {
            goToProfile() {
                this.$router.push({
                    name: 'profile',
                })
            },
            goToPlan() {
                this.$router.push({
                    name: 'training-plan',
                })
            },
            goToTraining() {
                this.$router.push({
                    name: 'training',
                })
            },
            goToExercise() {
                this.$router.push({
                    name: 'exercise-list',
                })
            }
        }
    }
</script>

<style>
</style>