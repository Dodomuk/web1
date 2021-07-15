import React, { Component } from 'react';
import { RouteComponentProps } from 'react-router-dom';

interface Props extends RouteComponentProps {

}

class Club extends Component<Props> {
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
				club

				<button onClick={this.goMemberPage}>go member</button>
			</>
		);
	}
}

export default Club;