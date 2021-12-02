import http from "./axiosSettings"

class JourneyService {
    getAll() {
        const response = http.get("/journey");
        console.log(response);
        return response;
    }

    get(id) {
        const response = http.get(`/journey/${id}`);
        console.log(response);
        return response;
    }

    create(data) {
        const response = http.post("/journey", data);
        console.log(response);
        return response;
    }

    update(id, data) {
        const response = http.put(`/journey/${id}`, data);
        console.log(response);
        return response;
    }

    delete(id) {
        const response = http.delete(`/journey/${id}`);
        console.log(response);
        return response;
    }

    findByTitle(title) {
        const response = http.get(`/journey?q=${title}`);
        console.log(response);
        return response;
    }
}

export default new JourneyService();