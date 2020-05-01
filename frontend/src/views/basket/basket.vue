<template>
    <v-content id="basket">
        <v-card height="100%"
                raised
                style="margin: 1%; padding:0 2% 1% 2%"
        >
            <v-row>
                <v-icon>mdi-basket</v-icon>
                <v-card-title>Моя корзина</v-card-title>
            </v-row>
            <v-card-text v-if="basket.products.length === 0">Вы еще не добавили товары в корзину</v-card-text>
            <v-row v-for="product in basket.products"
                   :key="product.productId"
                   style="padding:0 2% 5px 0">
                <v-card outlined
                        hover
                        width="99%">
                    <v-row>
                        <v-col cols="6">
                            <v-card-title>{{product.productName}}</v-card-title>
                        </v-col>
                        <v-col cols="2">
                            <v-row justify="center">
                                <v-card-text>Цена</v-card-text>
                            </v-row>
                            <v-row justify="center"
                                   align="center">
                                <v-card-text class="headline">{{product.price}}р.</v-card-text>
                            </v-row>
                        </v-col>
                        <v-col cols="4">
                            <v-img max-height="100px"
                                   contain
                                   :src=image>
                            </v-img>
                        </v-col>
                    </v-row>
                </v-card>
            </v-row>
            <v-row
                    align="center">
                <v-col cols="4">
                    <v-card-title>Общая стоимость: {{basket.totalCost}}р.</v-card-title>
                </v-col>
                <v-btn color="primary"
                       dark
                       @click="buyAll">
                    Оформить заказ
                </v-btn>
            </v-row>
        </v-card>
    </v-content>
</template>

<script>
    import axios from "axios";

    export default {
        name: "basket",
        data: () => {
            return {
                image: require('@/assets/image/imageNotFound.png'),
                basket: null,
            }
        },
        methods: {
            loadBasket() {
                axios.get("basket/")
                    .then(response => {
                        this.basket = response.data
                    })
            },
            buyAll() {
                axios.post("bill/",
                    JSON.stringify(this.basket)).then(response => this.basket = response.data)
                location.reload()
            }
        },
        mounted() {
            this.loadBasket()
        }
    }
</script>

<style scoped lang="scss">
    #basket {
        padding: 0 !important;
    }
</style>