import React, { Component } from 'react';
import { inject, observer } from 'mobx-react';
import autobind from 'autobind-decorator';
import ClubStore from '../service/ClubStore';
import ClubView from '../view/ClubView';
import TravelClub from '../entity/TravelClub';

interface Props{
   clubStore : ClubStore;
   travelClub : TravelClub;
   name : string;
   intro : string;
}
class ClubContainer extends Component<Props>{

    newTravelClub(name : string,intro : string){
        this.props.clubStore.setTravelClub(name,intro);
    }

    createClub(){
        let club = this.props.clubStore;
        this.props.clubStore.register(club);
    }

    render(){

        const { clubStore } = this.props;
        
        return(
            <ClubView
             travelClub = {this.props.travelClub}
             createClub = {this.createClub}
             newTravelClub = {this.newTravelClub}
            />
        )
    }

}
export default ClubContainer;