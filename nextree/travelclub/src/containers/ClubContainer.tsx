import React, { Component } from 'react';
import { inject, observer } from 'mobx-react';
import autobind from 'autobind-decorator';
import ClubStore from '../service/ClubStore';
import ClubTableView from '../view/ClubTableView';
import ClubTopView from '../view/ClubTopView';

interface Props{
   clubStore : ClubStore;
   name : string;
   intro : string;
}

@inject('clubStore')
@autobind
@observer
class ClubContainer extends Component<Props>{

    newName = (name : string) => {
        console.log(name);
        this.props.clubStore.setName(name);
    }

    newIntro = (intro : string) => {
        console.log(intro);
        this.props.clubStore.setIntro(intro);
    }

    createClub = () => {
        
        this.props.clubStore.register(this.props.clubStore.getName,this.props.clubStore.getIntro);
        console.log(this.props.clubStore.getList);
    }
    render(){

        const { clubStore } = this.props;
        return(
            < >
            <ClubTopView
             name = {this.props.name}
             intro = {this.props.intro}
             createClub = {this.createClub}
             newName = {this.newName}
             newIntro = {this.newIntro}
            />
            <ClubTableView
             list = {clubStore.getList}
             club = {clubStore.getClub}
             getName = {clubStore.getName}
             getIntro = {clubStore.getIntro}
            />
            </>
        )
    }
}
export default ClubContainer;