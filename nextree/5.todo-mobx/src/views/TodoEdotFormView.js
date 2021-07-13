import 'date-fns';
import React, { PureComponent } from 'react';
import { TextField, Grid, Button } from '@material-ui/core';
import SaveIcon from '@material-ui/icons/Save';
import DeleteIcon from '@material-ui/icons/Delete';
import UpdateIcon from '@material-ui/icons/Update';
import DateFnsUtils from '@date-io/date-fns';
import { MuiPickersUtilsProvider, KeyboardDatePicker } from '@material-ui/pickers';

class TodoEditFormView extends PureComponent {
  render(){

    const { todo , onSetTodoProps , onAddTodo , onUpdateTodo , onDeleteTodo } = this.props;

    return(
      <form noValidate>
        <Grid container xs={12} spacing={3}>
          <Grid item xs={3}>
            <TextField 
              margin="normal"
              id="outlined-basic" 
              label="Title" 
              variant="standard"
              value={ todo && todo.title ? todo.title : '' }
              onChange={ (event) => onSetTodoProps('title', event.target.value)}
              />
          </Grid>
          <Grid item xs={3}>
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <KeyboardDatePicker
                margin="normal"
                id="date-picker-dialog"
                label="Date"
                format="yyyy-MM-dd"
                value={ todo && todo.date ? todo.date : null}
                onChange={ (date) => onSetTodoProps('date', date.valueOf())}
                KeyboardButtonProps={{
                  'aria-label': 'change date',
                }}
              />
            </MuiPickersUtilsProvider>
          </Grid>
        </Grid>
        <Grid item >
         <Button variant='contained' color='primary' startIcon={<SaveIcon />} onClick={onAddTodo}>Add</Button>&nbsp;&nbsp;
         <Button variant='contained' color='default' startIcon={<UpdateIcon />} onClick={onUpdateTodo}>Update</Button>&nbsp;&nbsp;
         <Button variant='contained' color='secondary' startIcon={<DeleteIcon />} onClick={onDeleteTodo}>Delete</Button>&nbsp;&nbsp;
          
        </Grid>
      </form>
    )
  }
}

export default TodoEditFormView;