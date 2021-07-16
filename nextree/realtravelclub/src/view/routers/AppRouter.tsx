import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';

import Main from '../main';
import Club from '../club';
import Member from '../member';

const routes = [
	// Common
	{
		path: '',
		component: Main,
	},
	{
		path: 'main',
		component: Main,
	},
	{
		path: 'club',
		component: Club,
	},
	{
		path: 'member',
		component: Member,
	},
];

class AppRouter extends Component {
	render() {
		return (
			<Switch>
				{routes.map((singleRoute) => {
					const { path, ...otherProps } = singleRoute;
					return <Route key={path} path={`${path}`} component={Club} />;
				})}
			</Switch>
		);
	}
}

export default AppRouter;
