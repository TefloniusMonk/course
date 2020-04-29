import axios from 'axios'
import router from 'vue-router'

export default function () {
    axios.interceptors.request.use(request => {
        request.headers['Access-Control-Allow-Origin'] = '*'
        request.headers['Content-Type'] = "application/json"
        request.headers['Authorization'] = localStorage.getItem("TOKEN") ? `Bearer ${localStorage.getItem("TOKEN")}` : null
        return request
    })
    axios.interceptors.response.use(response => {
            return response
        },
        response => {
            if (response.response.status === 403 || response.response.status === 401) {
                router.replace({name: "login"})
            }
        })
}