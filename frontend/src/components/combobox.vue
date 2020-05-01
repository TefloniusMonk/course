<template>
    <v-combobox
            v-model="model"
            :filter="filter"
            :hide-no-data="!search"
            :items="items"
            :search-input.sync="search"
            hide-selected
            label="Search for an option"
    >
        <template v-slot:no-data>
            <v-list-item>
                <v-chip
                        label
                        small
                >
                    {{ search }}
                </v-chip>
            </v-list-item>
        </template>
        <template v-slot:selection="{ attrs, item, parent, selected }">
            <v-list-item
                    v-if="item === Object(item)"
                    v-bind="attrs"
                    :input-value="selected"
                    label
                    small
            >
          <span class="pr-2">
            {{ item.text }}
          </span>
            </v-list-item>
        </template>
        <template v-slot:item="{ index, item }">
            <v-text-field
                    v-if="editing === item"
                    v-model="editing.text"
                    autofocus
                    flat
                    background-color="transparent"
                    hide-details
                    solo
                    @keyup.enter="edit(index, item)"
            ></v-text-field>
            {{ item.text }}
        </template>
    </v-combobox>
</template>

<script>
    export default {
        props: {
            items: Array,
            model: Array
        },
        data: () => ({
            activator: null,
            attach: null,
            editing: null,
            index: -1,
            nonce: 1,
            menu: false,
            x: 0,
            search: null,
            y: 0,
        }),
        watch: {
            model(val, prev) {
                if (val.length === prev.length) return
                this.model = val
            },
        },
        methods: {
            edit(index, item) {
                if (!this.editing) {
                    this.editing = item
                    this.index = index
                } else {
                    this.editing = null
                    this.index = -1
                }
            },
            filter(item, queryText, itemText) {
                if (item.header) return false
                const hasValue = val => val != null ? val : ''
                const text = hasValue(itemText)
                const query = hasValue(queryText)
                return text.toString()
                    .toLowerCase()
                    .indexOf(query.toString().toLowerCase()) > -1
            },
        },
    }
</script>