import React, {Component} from "react";
import CourseDataService from "../service/CourseDataService";
import { InputDecimal } from "./InputDecimal";
import './MoneyChangingComponent.css';

const INSTRUCTOR = 'change'
const ARTICLE_PRICE = '0.00'
const GIVEN_MONEY = '0.00'
const MONEY_TO_CHANGE = '0.00'

class MoneyChangingComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            ARTICLE_PRICE: [],
            GIVEN_MONEY: [],
            MONEY_TO_CHANGE: [],
            value:''
        }
        this.refreshCourses = this.refreshCourses.bind(this)
        this.addCourseClicked = this.addCourseClicked.bind(this)     
        this.handleSubmit = this.handleSubmit.bind(this);
        this.onSubmit = this.onSubmit.bind(this)
    }
    componentDidMount() {
        this.refreshCourses();
    }
    addCourseClicked() {
        this.props.history.push(INSTRUCTOR)
    }
    refreshCourses() {
        CourseDataService.moneyToChange(INSTRUCTOR)
            .then(
                response => {
                    console.log(response);
                    this.setState({MONEY_TO_CHANGE: JSON.stringify(response.data)})
                }
            )
        }
    handleSubmit(){
    }
    onSubmit(values) {
            CourseDataService.updateChange(this.state.ARTICLE_PRICE, this.state.GIVEN_MONEY)
                .then(() => this.props.history.push('/change'))
        console.log(values);
    }

        render() {
            return (
                <div className="container">
                  
                  <div className="change-money">
                        <form className="form-horizontal">
                            <InputDecimal value={this.ARTICLE_PRICE}  type="InputDecimal" name="articlePrice" placeholder="Article price" />
                            <InputDecimal value={this.GIVEN_MONEY}  type="InputDecimal" name="givenMoney" placeholder="Given money" />
                            <input type="submit" onClick={this.addCourseClicked} />
                        </form>
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Article price : {this.ARTICLE_PRICE} €</th>
                                    <th>Given money : {GIVEN_MONEY}€</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.GIVEN_MONEY}
                            </tbody>
                        </table>
                    </div>
                    <div className="change-money">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Money to change :</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.MONEY_TO_CHANGE}
                            </tbody>
                        </table>
                    </div>

                </div>
            )
        }
}

export default MoneyChangingComponent
