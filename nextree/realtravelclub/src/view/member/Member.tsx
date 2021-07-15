import React, { Component } from 'react';
import { RouteComponentProps } from 'react-router-dom';

interface Props extends RouteComponentProps {

}

class Member extends Component<Props> {
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
		return (
			<>
				Member
			</>
		);
	}
}

export default Member;