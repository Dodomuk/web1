import React, { Component } from 'react';
import TodoListView from '../views/TodoListView';
import { inject, observer } from 'mobx-react';
import autobind from 'autobind-decorator';

@inject('todoStore')
@autobind
@observer
class TodoListContainer extends Component {

  onSelectedTodo(todo){
    this.props.todoStore.selectedTodo(todo);
  }

  render() {

    let { todos , searchText } = this.props.todoStore;
    
    // 기본값이 ""로 세팅되어 있어서 바로 사용해도 무방
    todos = todos.filter( todo => todo.title.toLowerCase().indexOf(searchText.toLowerCase()) !== -1); 
    
    return (
      <TodoListView
        todos = { todos }
        onSelectedTodo = { this.onSelectedTodo }
      />
    )
  }
}

export default TodoListContainer;