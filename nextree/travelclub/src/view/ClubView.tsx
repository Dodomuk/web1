import React, { PureComponent } from "react";
import {
  Grid,
  List,
  ListItem,
  Table,
  TableContainer,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Paper,
  Button,
  TextField,
} from "@material-ui/core";
import { Save, Delete, Update, Search } from "@material-ui/icons";
import { NavLink, Route } from "react-router-dom";
import { grey, blueGrey, lightGreen } from "@material-ui/core/colors";
import { withStyles } from "@material-ui/core/styles";
import TravelClub from "../entity/TravelClub";

interface Props {
  travelClub : TravelClub;
  createClub: Function;
  newTravelClub: Function;
}
class ClubView extends PureComponent<Props> {
  render() {
    const {travelClub , createClub , newTravelClub } = this.props;

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
    const links = (
      <form noValidate>
        <Grid container xs={12} spacing={3}>
          <Grid item xs={3}>
            <TextField
              margin="normal"
              id="outlined-basic"
              label="name"
              variant="standard"
              value={travelClub.setName}
              onChange={(event) => newTravelClub("name", event.target.value)}
            />
          </Grid>
          <Grid container xs={12} spacing={3}>
            <Grid item xs={3}>
              <TextField
                margin="normal"
                id="outlined-basic"
                label="intro"
                variant="standard"
                value={travelClub.setIntro}
                onChange={(event) =>
                  newTravelClub("intro", event.target.value)
                }
              />
            </Grid>
          </Grid>

          <Grid item>
            <AddButton
              variant="contained"
              color="primary"
              startIcon={<Save />}
              onClick={() => createClub}
            >
              Add
            </AddButton>
            &nbsp;&nbsp;
            <ColorButton
              variant="contained"
              color="primary"
              startIcon={<Search />}
              onClick={() => findClub}
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

    return (
      <Grid container spacing={2}>
        <Grid item alignItems="flex-end"></Grid>
        <Grid item alignItems="flex-end"></Grid>
        <Grid item alignItems="flex-end">
          {links}
        </Grid>
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="center">클럽</TableCell>
                <TableCell align="center">소개글</TableCell>
                <TableCell align="center">가입날짜</TableCell>
              </TableRow>
            </TableHead>
            <TableBody></TableBody>
          </Table>
        </TableContainer>
      </Grid>
    );
  }
}

export default ClubView;
