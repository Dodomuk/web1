import {observable , action , computed, makeObservable, toJS} from 'mobx';

class TodoStore{
    
    constructor(){
        makeObservable(this);
    }

    @observable
    _todo = {} // id, title, date

    @observable
    _todos = [];

    @observable
    _searchText = '';

    get todo(){
        return this._todo;
    }

    @computed
    get todos(){
        return toJS(this._todos);
    }

    get searchText(){
        return this._searchText;
    }

    @action
    setTodoProps(name, value){
        this._todo = {
            ...this._todo,
            [name] : value
        }
    }

    @action
    setSearchText(searchText){
        this._searchText = searchText;
    }

    @action
    addTodo(todo){
        this._todos.push(todo);
    }

    @action
    selectedTodo(todo){
        this._todo = todo;
    }

    @action
    updateTodo(){
        let foundTodo = this._todos.find( (todo) => todo.id === this._todo.id);
        foundTodo.title = this._todo.title;
        foundTodo.date = this._todo.date;

        this._todo = {};
    }

    @action
    deleteTodo(){
        let index = this._todos.findIndex( todo => todo.id === this.todo.id);
  
        if(index > -1){
            this._todos.splice(index,1);
        }

        this._todo = {};
    }

}

export default new TodoStore();