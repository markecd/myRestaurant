import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import HomePage from './pages/HomePage/HomePage';
import LoginPage from './pages/LoginPage/LoginPage';
import NarocilaPage from './pages/NarocilaPage/NarocilaPage';

function App() {
  return (
   <Router>
      <Switch>
        <Route path="/" exact component={LoginPage} />
        <Route path="/home" component={HomePage} />
        <Route path="/narocilo" component={NarocilaPage} />
      </Switch>
   </Router>
  );
}

export default App;
