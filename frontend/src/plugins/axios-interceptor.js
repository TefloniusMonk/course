import axios from 'axios'
import router from 'vue-router'

export default function () {
    axios.interceptors.response.use(response => {
            return response
        },
        response => {
            if (response.response.status === 403 || response.response.status === 401) {
                router.replace({name: "login"})
            }
        })
}