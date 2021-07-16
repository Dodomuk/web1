import { inject, observer } from "mobx-react";
import { Component } from "react";
import autobind from 'autobind-decorator';

@inject('clubStore')
@autobind
@observer
class ClubContainer extends Component{

    render(){
        const { clubStore } : any = this.props;
        return(
            <>
            <p>{clubStore.getName}</p>
            <p>{clubStore.getIntro}</p>
            </>
        );
    }

    
}
