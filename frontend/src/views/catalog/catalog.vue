<template>
    <v-content id="catalog">
        <v-row style="margin-left: 0; padding-left: 10px; padding-right: 20px">
            <v-select filled
                      v-model="filter"
                      label="Категории товаров"
                      outlined
                      clearable
                      :items=catalogNames
                      @select="filteredProducts"/>
        </v-row>
        <v-row>
            <v-col cols="4" v-for="product in filteredProducts"
                   :key="product.productId">
                <v-card height="270px"
                        hover>
                    <v-row>
                        <v-col cols="4">
                            <v-img max-height="150px"
                                   contain
                                   :src=image>
                            </v-img>
                            <v-row align="center"
                                   justify="center">
                                <v-card-text style="padding-left: 50px">
                                    Цена: {{product.price}}р.
                                </v-card-text>
                                <v-btn v-if="filteredProducts"
                                       small
                                       @click="toBasket(product)">
                                    В корзину
                                </v-btn>
                            </v-row>
                        </v-col>
                        <v-col cols="8">
                            <v-card-title>
                                {{product.productName}}
                            </v-card-title>
                            <v-card-text v-if="product.productDesc.length > 200">
                                {{cutBySpace(product.productDesc, 200, 250) + "..."}}
                            </v-card-text>
                            <v-card-text v-else>
                                {{product.productDesc}}
                            </v-card-text>
                        </v-col>
                    </v-row>
                </v-card>
            </v-col>
        </v-row>
        <v-btn bottom
               color="primary"
               dark
               fab
               fixed
               right
               @click="dialog = !dialog">
            <v-icon>mdi-plus</v-icon>
        </v-btn>
        <v-dialog
                v-model="dialog"
                width="800px">
            <v-card>
                <v-card-title>
                    Добавить товар
                </v-card-title>
                <v-container>
                    <v-row class="mx-2">
                        <v-col class="align-center justify-space-between"
                               cols="12">
                            <v-row align="center"
                                   class="mr-0">
                                <v-text-field label="Название товара"
                                              placeholder="Название товара"
                                              v-model="product.productName"/>
                            </v-row>
                        </v-col>
                        <v-col class="align-center justify-space-between"
                               cols="12">
                            <v-row align="center"
                                   class="mr-0">
                                <v-text-field label="Цена"
                                              type="number"
                                              placeholder="Цена"
                                              v-model="product.price"/>
                            </v-row>
                        </v-col>
                        <v-col class="align-center justify-space-between"
                               cols="12">
                            <v-row align="center"
                                   class="mr-0">
                                <v-combobox label="Категория товара"
                                            placeholder="Категория товара"
                                            :items="catalogNames.filter(c => c !== 'Все товары')"
                                            v-model="category"
                                            item-text="name"
                                            item-value="name"
                                            small-chips>
                                    <template slot="selection"
                                              slot-scope="data">
                                        <v-chip :input-value="data.selected"
                                                :close="true"
                                                @input="data.parent.selectItem(data.item)">
                                            {{ data.item }}
                                        </v-chip>
                                    </template>
                                </v-combobox>
                                <v-col class="align-center justify-space-between"
                                       cols="12">
                                    <v-row align="center"
                                           class="mr-0">
                                        <v-textarea label="Описание товара"
                                                    placeholder="Описание товара"
                                                    v-model="product.productDesc"/>
                                    </v-row>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-container>
                <v-card-actions>
                    <v-spacer/>
                    <v-btn text
                           color="primary"
                           @click="dialog = false">
                        Отмена
                    </v-btn>
                    <v-btn text
                           @click="saveProduct">
                        Добавить
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-content>
</template>

<script>
    import axios from "axios";

    let all = "Все товары"
    export default {
        name: "catalog",
        data: () => {
            return {
                dialog: false,
                image: require('@/assets/image/imageNotFound.png'),
                filter: null,
                catalogNames: [all],
                category: null,
                catalogs: [],
                products: [],
                basket: null,
                search: null,
                product: {
                    categoryName: null,
                    price: null,
                    productName: null,
                    productDesc: null
                }
            }
        },
        methods: {
            loadCatalogs() {
                axios.get("catalog/")
                    .then(response => {
                            this.catalogs = response.data
                            this.catalogs.forEach(cat => this.catalogNames.push(cat.catalogName))
                            this.catalogs
                                .forEach(catalog => catalog.products
                                    .forEach(product => {
                                        product.catalogName = catalog.catalogName
                                        this.products.push(product)
                                    }))
                        }
                    )
            },
            loadBasket() {
                axios.get("basket/")
                    .then(response => {
                        this.basket = response.data
                    })
            },
            cutBySpace(str, min, max) {
                let chars = str.split("")
                for (let i = min; i < max; i++) {
                    if (chars[i] === ' ') {
                        return str.substring(0, i)
                    }
                }
                return str
            },
            toBasket(product) {
                this.basket.products.push(product)
                this.basket.totalCost = this.basket.products.map(prod => prod.price).reduce((a, b) => {
                    return a + b
                }, 0)
                let resp = JSON.stringify(this.basket, (key, val) => {
                    if (key !== "catalogName") {
                        return val
                    }
                })
                axios.post("basket/",
                    resp).then(response => this.basket = response.data)

            },
            saveProduct() {
                this.dialog = false
                axios.post("product/",
                    JSON.stringify(this.product, (key, val) => {
                        if (key !== "categoryName") {
                            return val
                        }
                    })).then(response => this.refreshCategory(response.data))
            },
            refreshCategory(product) {
                console.log(this.catalogNames.length)
                for (let i = 0; i < this.catalogNames.length; i++) {
                    if (this.catalogNames[i] === this.category) {
                        let tmp = this.catalogs.filter(cat => cat.catalogName === this.category)[0]
                        tmp.products.push(product)
                        tmp.products.forEach(prod => delete prod.catalogName)
                        axios.post("catalog/", JSON.stringify(tmp))
                        location.reload()
                        return
                    }
                }
                axios.post("catalog/",
                    JSON.stringify({
                            catalogId: null,
                            catalogName: this.category,
                            products: [product]
                        }
                    )).then(response => {
                    this.catalogs = response.data
                    this.category = null
                    location.reload()
                })
            }
        },
        mounted() {
            this.loadCatalogs()
            this.loadBasket()
        },
        watch: {
            categoryName(val, prev) {
                if (val.length === prev.length) return
                this.categoryName.push(val)
            },
        },
        computed: {
            filteredProducts() {
                if (!this.filter || this.filter === all) {
                    return this.products
                }
                return this.products.filter(prod => prod.catalogName === this.filter)
            }
        }
    }
</script>

<style scoped>
    #catalog {
        padding: 10px 10px 10px 10px !important;
    }
</style>