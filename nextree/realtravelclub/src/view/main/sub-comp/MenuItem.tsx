import React, { Component } from 'react';

class MenuItem extends Component {
	//
	render() {
		//
		const { children } = this.props;
		const style = {
			margin: '0 4px',
			width: '100px',
			height: '40px',
			lineHeight: '40px',
			display: 'inline-block',
		}

		return (
			<div style={style}>
				{children}
			</div>
		);
	}
}

export default MenuItem;