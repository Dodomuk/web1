import TravelClub from '../entity/club/TravelClub';
import clubStore from '../model/TravelClubStore';

class ClubCoordinator {

    private _clubStore: any;

    constructor() {
        this._clubStore = clubStore;
    }

    hasClubs(name: string): boolean {
        return this._clubStore.exists(name);
    }

    register(newClub : TravelClub): string {
        return this._clubStore.create(newClub);
    }

    findClub(name: string): TravelClub {
        return this._clubStore.retrieve(name);
    }

    modify(name: string, intro: string): void {
        if (!this._clubStore.exists(name)) {
            return;
        }
        let foundClub = this._clubStore.retrieve(name);
        foundClub.setIntro = intro;
        this._clubStore.update(foundClub);

    }

    remove(name: string) {
        if (!this._clubStore.exists(name)) {
            return;
        }
        this._clubStore.delete(name);
    }
}

export default ClubCoordinator;