<template>
    <v-app id="login">
        <v-content>
            <v-container
                    class="fill-height"
                    fluid
            >
                <v-row
                        align="center"
                        justify="center"
                >
                    <v-col
                            cols="12"
                            sm="8"
                            md="4"
                    >
                        <v-card class="elevation-12">
                            <v-toolbar
                                    color="indigo"
                                    dark
                                    flat
                            >
                                <v-toolbar-title>Вас приветствует Курс Молодого Бойца</v-toolbar-title>
                                <v-spacer/>
                            </v-toolbar>
                            <v-card-text>
                                <v-form>
                                    <v-text-field
                                            label="Логин"
                                            name="Логин"
                                            placeholder="Enter 'admin'"
                                            prepend-icon="mdi-login"
                                            type="text"
                                            v-model="login"
                                    />
                                    <v-text-field
                                            id="password"
                                            label="Пароль"
                                            name="Пароль"
                                            placeholder="Enter 'password'"
                                            prepend-icon="mdi-lock"
                                            type="password"
                                            v-model="password"
                                            v-on:keypress.enter="sendForm"
                                    />
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer/>
                                <v-btn color="indigo"
                                       dark
                                       @click="sendForm"
                                       v-on:keypress.enter="sendForm"
                                >
                                    Войти
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import axios from 'axios'
    import router from "@/router";

    export default {
        name: "login",
        props: {
            source: String,
        },
        watch: {},
        data() {
            return {
                login: null,
                password: null,
            }
        },
        methods: {
            sendForm() {
                return axios.post('user/auth',
                    `{"login": "${this.login}", "password":"${this.password}"}`)
                    .then(response => {
                        if (response.status === 200) {
                            localStorage.setItem("TOKEN", response.data)
                            router.push("profile")
                        }
                    })
            },
            get() {
                return axios.get("/product/")
            }
        },
        mounted() {
            this.get()
        }
    }
</script>

<style scoped>
    #login {
        height: 100%;
    }
</style>