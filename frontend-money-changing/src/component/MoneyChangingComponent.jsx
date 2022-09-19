import React, {Component} from "react";
import MoneyDataService from "../service/MoneyDataService";
import { InputDecimal } from "./InputDecimal";
import './MoneyChangingComponent.css';
import axios from 'axios'

const ENDPOINT = 'change'
const JAVA_API_URL = 'http://localhost:8080'
const CHANGE_MONEY_API_URL = `${JAVA_API_URL}/${ENDPOINT}`
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
        this.onSubmit = this.onSubmit.bind(this)
    }
    componentDidMount() {
        this.onSubmit();
    }

    onSubmit() {
        return axios.get(`${CHANGE_MONEY_API_URL}?articlePrice=${parseFloat(this.ARTICLE_PRICE)}&givenMoney=${parseFloat(this.GIVEN_MONEY)}`);
    }
        render() {
            return (
                <div className="container">

                    <div className="change-money">
                        <h1>Welcome to change app !</h1>
                    </div>

                    <div className="change-money">   
                        <form className="form-horizontal">
                            <InputDecimal value={this.ARTICLE_PRICE}  type="InputDecimal" name="articlePrice" placeholder="Article price" />
                            <InputDecimal value={this.GIVEN_MONEY}  type="InputDecimal" name="givenMoney" placeholder="Money received from client" />
                            <button className="btn btn-success" type="submit">Send</button>
                        </form>
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Article price : {this.state.ARTICLE_PRICE} €</th>
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
        moneyToChange(ARTICLE_PRICE, GIVEN_MONEY) {
            return axios.get(`${CHANGE_MONEY_API_URL}?articlePrice=${ARTICLE_PRICE}&givenMoney=${GIVEN_MONEY}`);
        } 
}

export default MoneyChangingComponent
