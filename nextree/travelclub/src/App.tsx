import './App.css';
import React, { Component } from "react";
import { Route, Switch , BrowserRouter as Router} from "react-router-dom";
import Main from "./view/Main";
import Nav from "./view/Nav";
import ClubContainer from './containers/ClubContainer';
import { PropTypes } from 'mobx-react';


class App extends Component{
  render(){
    return (
      <Router>
      <div className="App">
        <Nav />
        <Switch>
        <Route exact path='/' component={Main} />
        <Route path='/club' component={ClubContainer}/>
        <Route component={() => <h2>Not Found!</h2>} />
        </Switch>
      </div>
      </Router>
    );
  } 
}

export default App;
