// Header.js
import './Header.css';
import React from 'react';
import { NavLink, useHistory } from 'react-router-dom';
import { Button } from 'react-bootstrap';

function Header() {
  const history = useHistory();

  const handleLogout = () => {
    sessionStorage.removeItem('pozicija');
    history.push('/');
  };

  return (
    <div className="header-container">
      <div className="logo">
        <NavLink to="/home">myRestaurant</NavLink>
      </div>
      <div className="nav-links">
        <Button variant="danger" className="logout-button" onClick={handleLogout}>
          Odjava
        </Button>
      </div>
    </div>
  );
}

export default Header;
