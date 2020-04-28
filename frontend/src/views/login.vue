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
                                    color="primary"
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
                                            prepend-icon="person"
                                            type="text"
                                    />
                                    <v-text-field
                                            id="password"
                                            label="Пароль"
                                            name="Пароль"
                                            placeholder="Enter 'password'"
                                            prepend-icon="lock"
                                            type="password"
                                    />
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer/>
                                <v-btn color="primary">Login</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        name: "login",
        props: {
            source: String,
        }
    }
</script>

<style scoped>
    #app {
        height: 100%;
    }
</style>