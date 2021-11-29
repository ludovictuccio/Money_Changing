import React, {Component} from "react";
import CourseDataService from "../service/CourseDataService";

const INSTRUCTOR = 'change'

class ListCoursesComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            money: [],
            message: null
        }
        this.refreshCourses = this.refreshCourses.bind(this)
        this.addCourseClicked = this.addCourseClicked.bind(this)
    }

    componentDidMount() {
        this.refreshCourses();
    }

    addCourseClicked() {
        this.props.history.push(`/courses`)
    }

    refreshCourses() {
        CourseDataService.moneyToChange(INSTRUCTOR)
            .then(
                response => {
                    console.log(response);
                    this.setState({money: JSON.stringify(response.data)})
                }
            )
        }    

        render() {
            return (
                <div className="container" style={{
                    backgroundColor: "lightyellow",
                    border: "1px solid yellow",
                    padding: "10px"
                  }}>
                  
                    <div className="container">
                        <div className="row">
                            <button className="btn btn-success" onClick={this.addCourseClicked}>Add price article</button>  
                        </div>
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Price article :</th>
                                    <th>Update</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.money}
                            </tbody>
                        </table>
                    </div>

                    <div className="container">
                         <div className="row">
                            <button className="btn btn-success" onClick={this.addCourseClicked}>Add giving money</button>  
                        </div>
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Giving money :</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.money}
                            </tbody>
                        </table>
                    </div>

                    <div className="container">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>Money to change :</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.money}
                            </tbody>
                        </table>
                    </div>

                </div>
            )
        }
}

export default ListCoursesComponent
