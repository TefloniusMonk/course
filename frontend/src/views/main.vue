<template>
    <v-app id="main-page">
        <v-navigation-drawer
                v-model="drawer"
                :clipped="$vuetify.breakpoint.lgAndUp"
                dark
                app
        >
            <v-list dense>
                <v-list-item link
                             @click="goToCatalog">
                    <v-list-item-action>
                        <v-icon>mdi-beer</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>
                            Каталог
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-list-item link
                             @click="goToBasket">
                    <v-list-item-action>
                        <v-icon>mdi-basket</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>
                            Корзина
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-list-item link
                             @click="goToBills">
                    <v-list-item-action>
                        <v-icon>mdi-content-copy</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>
                            Чеки
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>mdi-help-circle</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>
                            Помощь
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

            </v-list>
        </v-navigation-drawer>

        <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp"
                   app
                   color="blue darken-3"
                   dark>
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"/>
            <v-toolbar-title style="width: 300px"
                             class="ml-0 pl-4">
                <span class="hidden-sm-and-down">Магазин молодого бойца</span>
            </v-toolbar-title>
            <v-spacer/>
            <v-menu v-model="menu"
                    :close-on-content-click="true"
                    :nudge-width="200"
                    left
                    offset-x>
                <template v-slot:activator="{ on }">
                    <v-btn
                            icon
                            color="indigo"
                            dark
                            v-on="on"
                    >
                        <v-avatar size="32px"
                                  item>
                            <v-img :src="avatar"/>
                        </v-avatar>
                    </v-btn>
                </template>
                <v-card>
                    <v-list>
                        <v-list-item>
                            <v-list-item-avatar>
                                <v-img :src="avatar"/>
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title>{{this.customer.fullName}}</v-list-item-title>
                                <v-list-item-subtitle>{{this.customer.email}}</v-list-item-subtitle>
                            </v-list-item-content>

                        </v-list-item>
                    </v-list>
                    <v-divider></v-divider>
                    <v-card-actions>
                        <v-btn text @click="goToProfile">Редактировать</v-btn>
                        <v-spacer/>
                        <v-btn text @click="logout">Выйти</v-btn>
                    </v-card-actions>
                </v-card>
            </v-menu>
        </v-app-bar>
        <v-content>
            <router-view/>
        </v-content>
    </v-app>

</template>

<script>

    import axios from "axios";

    export default {
        name: "main-page",
        props: [],
        data: () => ({
            drawer: null,
            menu: false,
            customer: {
                id: null,
                fullName: null,
                email: null,
                birthDate: null,
            },
            avatar: require('@/assets/image/dog.jpg')
        }),
        watch: {},
        mounted() {
            this.loadProfile()
        }
        ,
        methods: {
            loadProfile() {
                axios.get("customer/")
                    .then(response => {
                            this.customer.fullName = response.data.fullName
                            this.customer.email = response.data.email
                            this.customer.birthDate = response.data.birthDate
                            this.customer.id = response.data.customerId
                        }
                    )
            },
            goToProfile() {
                this.$router.push({
                    name: 'profile',
                })
            },
            goToCatalog() {
                this.$router.push({
                    name: 'catalog'
                })
            },
            goToBasket() {
                this.$router.push({
                    name: 'basket'
                })
            },
            goToBills() {
                this.$router.push({
                    name: 'bill-list'
                })
            },
            logout() {
                localStorage.clear()
                this.$router.push({
                    name: 'login'
                })
            }
        }
    }
</script>

<style>
</style>