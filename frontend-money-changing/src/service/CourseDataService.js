import axios from 'axios'

const INSTRUCTOR = 'change'
const COURSE_API_URL = 'http://localhost:8080'
const INSTRUCTOR_API_URL = `${COURSE_API_URL}/${INSTRUCTOR}`

const ARTICLE_PRICE = '10'
const GIVEN_MONEY = '20'

class CourseDataService {

    moneyToChange(name) {
        return axios.get(`${INSTRUCTOR_API_URL}?articlePrice=${ARTICLE_PRICE}&givenMoney=${GIVEN_MONEY}`);
    }
}



export default new CourseDataService()