import React, { Component } from 'react';
import { inject, observer } from 'mobx-react';
import autobind from 'autobind-decorator';
import { RouteComponentProps } from 'react-router-dom';
import ClubStore from '../../stores/ClubStore';

interface Props extends RouteComponentProps {
    clubStore : ClubStore;
	name : string,
	intro : string,
}

@inject('clubStore')
@autobind
@observer
class Club extends Component<Props> {
	//
	constructor(props: Props) {
		super(props);

		this.goMemberPage = this.goMemberPage.bind(this);
	}

	goMemberPage() {
		const { history } = this.props;
		history.push('/member');
	}

	render() {
		//
		const { clubStore } = this.props;
		console.log(`${clubStore.getIntro}`);

		return (
			<>
			<button onClick={this.goMemberPage}>go member</button>
			<p>{clubStore.getName}저기요</p>
            <p>{clubStore.getIntro}여기요</p>
            </>
		);
	}
}
export default Club;