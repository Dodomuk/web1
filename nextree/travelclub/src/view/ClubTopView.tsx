import React, { PureComponent } from "react";
import {
  Grid,
  Button,
  TextField,
} from "@material-ui/core";
import { Save, Delete, Update, Search } from "@material-ui/icons";
import { NavLink, Route } from "react-router-dom";
import { grey, blueGrey, lightGreen } from "@material-ui/core/colors";
import { withStyles } from "@material-ui/core/styles";

interface Props {
  createClub: Function;
  newName: Function;
  newIntro: Function;
  name : string;
  intro : string;
}
class ClubView extends PureComponent<Props> {
  render() {
    const {createClub , newName , newIntro , name , intro } = this.props;

    //버튼 커스터마이징
    const AddButton = withStyles((theme) => ({
      root: {
        color: theme.palette.getContrastText(grey[500]),
        backgroundColor: grey[500],
        "&:hover": {
          backgroundColor: grey[700],
        },
      },
    }))(Button);

    const ColorButton = withStyles((theme) => ({
      root: {
        color: theme.palette.getContrastText(blueGrey[500]),
        backgroundColor: blueGrey[500],
        "&:hover": {
          backgroundColor: blueGrey[700],
        },
      },
    }))(Button);
    const TrashButton = withStyles((theme) => ({
      root: {
        color: theme.palette.getContrastText(lightGreen[500]),
        backgroundColor: lightGreen[500],
        "&:hover": {
          backgroundColor: lightGreen[700],
        },
      },
    }))(Button);

    //input
    return (
      <form noValidate>
        <Grid container xs={10} spacing={2}>
          <Grid item xs={3}>
            <TextField
              margin="normal"
              id="outlined-basic"
              label="name"
              variant="standard"
              value={name}
              onChange={(event) => newName(event.target.value)}
            />
          </Grid>
          <Grid container xs={12} spacing={2}>
            <Grid item xs={3}>
              <TextField
                margin="normal"
                id="outlined-basic"
                label="intro"
                variant="standard"
                value={intro}
                onChange={(event) =>
                  newIntro(event.target.value)
                }
              />
            </Grid>
          </Grid>

          <Grid item>
            <AddButton
              variant="contained"
              color="primary"
              startIcon={<Save />}
              onClick={() => createClub(name,intro)}
            >
              Add
            </AddButton>
            &nbsp;&nbsp;
            <ColorButton
              variant="contained"
              color="primary"
              startIcon={<Search />}
            >
              Find
            </ColorButton>
            &nbsp;&nbsp;
            <TrashButton
              variant="contained"
              color="default"
              startIcon={<Delete />}
            >
              Update
            </TrashButton>
            &nbsp;&nbsp;
            <Button variant="contained" color="default" startIcon={<Update />}>
              Delete
            </Button>
            &nbsp;&nbsp;
          </Grid>
        </Grid>
      </form>
    );
  }
}

export default ClubView;
