import React, { Component } from 'react'
import { NavLink } from 'react-router-dom';

class Nav extends Component {
  render(){
    return (
      <nav className='navtop'>
        <h2><NavLink exact to='/'>TravelClub</NavLink></h2>
        <ul className='nav-links'>
          <li><NavLink exact to='/club'>Clubs</NavLink></li>
          <li><NavLink to='/'>Members</NavLink></li>
          <li><NavLink to='/'>Boards</NavLink></li>
          <li><NavLink to='/'>Posts</NavLink></li>
        </ul>
      </nav>
    )
  }
}

export default Nav;