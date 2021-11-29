import axios from 'axios'

const INSTRUCTOR = 'change'
const COURSE_API_URL = 'http://localhost:8080'
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/${INSTRUCTOR}`

class CourseDataService {

    moneyToChange(name) {
        return axios.get(`${INSTRUCTOR_API_URL}?articlePrice=20&givenMoney=100`);
    }
}



export default new CourseDataService()