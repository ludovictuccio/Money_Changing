import React, { Component } from 'react';
import './App.css';
import MoneyChangingApp from './component/MoneyChangingApp';

class App extends Component {
  render() {
    return (
      <div className="container">
        <MoneyChangingApp />
      </div>
    );
  }
}

export default App;
