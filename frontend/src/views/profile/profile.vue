<template>
    <v-content id="profile">
        <v-card height="100%"
                raised
                style="margin: 10px">
            <v-row>
                <v-col class="col-6">
                    <v-card-title>Информация о покупателе</v-card-title>
                    <v-card-title>
                        <v-text-field label="ФИО"
                                      :disabled="!fullName"
                                      v-model="customer.fullName"
                                      :value=this.customer.fullName>
                        </v-text-field>
                        <v-btn icon
                               @click="fullName = !fullName">
                            <v-icon>mdi-pencil</v-icon>
                        </v-btn>
                    </v-card-title>
                    <v-card-title>
                        <v-text-field label="Email"
                                      :disabled="!email"
                                      v-model="customer.email"
                                      :value=this.customer.email>
                        </v-text-field>
                        <v-btn icon
                               @click="email = !email">
                            <v-icon>mdi-pencil</v-icon>
                        </v-btn>
                    </v-card-title>
                    <v-card-title>
                        <v-text-field label="Дата рождения"
                                      disabled
                                      :value=this.customer.birthDate>
                        </v-text-field>
                        <v-menu v-model="birthDate"
                                :close-on-content-click="false"
                                :nudge-width="200"
                                offset-x
                        >
                            <template v-slot:activator="{ on }">
                                <v-btn

                                        v-on="on"
                                        icon>
                                    <v-icon>mdi-pencil</v-icon>
                                </v-btn>
                            </template>
                            <v-card>
                                <v-date-picker :value=this.customer.birthDate
                                               locale="ru"
                                               v-model="customer.birthDate"

                                ></v-date-picker>
                            </v-card>
                        </v-menu>
                    </v-card-title>
                    <v-btn style="margin-left: 16px"
                           @click="changeProfile">Применить
                    </v-btn>
                </v-col>
                <v-col class="col-3">
                    <v-img :src="avatar"
                           style="margin: 20px 0 0 20px">
                    </v-img>
                </v-col>
            </v-row>
        </v-card>
    </v-content>
</template>

<script>
    import axios from "axios";

    export default {
        name: "profile",
        data: () => {
            return {
                fullName: false,
                email: false,
                birthDate: false,
                avatar: require('@/assets/image/dog.jpg'),
                customer: {
                    id: null,
                    fullName: null,
                    email: null,
                    birthDate: null,
                    userId: null,
                    basketId: null
                },
            }
        },
        methods: {
            loadProfile() {
                axios.get("customer/")
                    .then(response => {
                            this.customer.fullName = response.data.fullName
                            this.customer.email = response.data.email
                            this.customer.birthDate = response.data.birthDate
                            this.customer.id = response.data.customerId === undefined ? null : response.data.customerId
                            this.customer.userId = response.data.userId === undefined ? null : response.data.userId
                            this.customer.basketId = response.data.basketId === undefined ? null : response.data.basketId
                        }
                    )
            },
            changeProfile() {
                axios.post("customer/",
                    `{
                "customerId":"${this.customer.id}",
                "email":"${this.customer.email}",
                "fullName":"${this.customer.fullName}",
                "birthDate":"${this.customer.birthDate}",
                "basketId":"${this.customer.basketId}",
                "userId":"${this.customer.userId}"
                }
                `
                )
                    .then(response => {
                            this.customer.fullName = response.data.fullName
                            this.customer.email = response.data.email
                            this.customer.birthDate = response.data.birthDate
                            this.customer.id = response.data.customerId
                        }
                    )
            },

        },
        mounted() {
            this.loadProfile()
        }
    }
</script>

<style scoped>
    #profile {
        height: 100%;
        width: 100%;
        padding: 0 !important;
    }
</style>