<template>
    <v-app id="login">
        <v-content>
            <v-container
                    class="fill-height"
                    fluid>
                <v-row align="center"
                       justify="center">
                    <v-col cols="12"
                           sm="8"
                           md="4">
                        <v-card class="elevation-12">
                            <v-toolbar color="indigo"
                                       dark
                                       flat>
                                <v-toolbar-title>Вас приветствует Курс Молодого Бойца</v-toolbar-title>
                                <v-spacer/>
                            </v-toolbar>
                            <v-card-text>
                                <v-form>
                                    <v-text-field
                                            label="Логин"
                                            name="Логин"
                                            placeholder="Введите логин"
                                            prepend-icon="mdi-login"
                                            type="text"
                                            v-model="login"/>
                                    <v-text-field
                                            id="email"
                                            label="Email"
                                            name="Email"
                                            placeholder="Введите email"
                                            prepend-icon="mdi-email"
                                            v-model="email"
                                    />
                                    <v-text-field
                                            id="password"
                                            label="Пароль"
                                            name="Пароль"
                                            placeholder="Введите ваш пароль"
                                            prepend-icon="mdi-lock"
                                            type="password"
                                            v-model="password"
                                    />
                                    <v-text-field
                                            id="password-repeat"
                                            label="Повторите Пароль"
                                            name="Пароль"
                                            placeholder="Повторите Пароль"
                                            prepend-icon="mdi-lock"
                                            type="password"
                                            v-model="passRepeat"
                                            :rules="rules.equals"
                                    />
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer/>
                                <v-btn color="indigo"
                                       dark
                                       @click="sendForm"
                                       v-on:keypress.enter="sendForm">Войти
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
    import axios from "axios";
    import router from "@/router";

    export default {
        name: "sign-up",
        props: {
            source: String,
        },
        watch: {},
        data() {
            return {
                login: null,
                password: null,
                email: null,
                passRepeat: null,
                rules: {
                    equals: [repeat => !!repeat || "Пароли не совпадают"]
                }
            }
        },
        methods: {
            sendForm() {
                return axios.post('user/sign-up',
                    `{
                    "userId": null,
                    "password": "${this.password}",
                    "login": "${this.login}",
                    "email": "${this.email}"}`)
                    .then(response => {
                        if (response.status === 200) {
                            router.push("login")
                        }
                    })
            },
        }
    }
</script>

<style scoped>

</style>