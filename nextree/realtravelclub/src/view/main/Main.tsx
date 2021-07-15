import React, { Component } from 'react';
import { Link , RouteComponentProps } from 'react-router-dom';
import MenuItem from './sub-comp/MenuItem';

interface Props extends RouteComponentProps {

}

class Main extends Component<Props> {
	//
	constructor(props: Props | Readonly<Props>) {
		super(props);

		this.goMemberPage = this.goMemberPage.bind(this);
	}

	goMemberPage() {
		const { history } = this.props;
		history.push('/member');
	}

	render() {
		//
		const menuList = ['main', 'club', 'member'];

		return (
			<>
				{
					menuList.map(menu => (
						<MenuItem key={menu}>
							<Link to={`/${menu}`}>{menu}</Link>
						</MenuItem>
					))
				}
			</>
		);
	}
}

export default Main;