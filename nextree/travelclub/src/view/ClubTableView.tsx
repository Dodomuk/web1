import {
  TableContainer,
  Paper,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
} from "@material-ui/core";
import { observer } from "mobx-react";
import { PureComponent } from "react";

interface Props {
  list: Map<string, string>[];
  club: Map<string, string>;
  getName: string;
  getIntro: string;
}
@observer
class ClubTableView extends PureComponent<Props> {
  render() {
    const { list, club, getName, getIntro } = this.props;
    console.log(`${list}`);
    // const map =list.forEach((key, value) => (
    //     <TableRow>
    //       <TableCell>{key}</TableCell>
    //       <TableCell>{value}</TableCell>
    //     </TableRow>
    //   ));
    return (
      <>
        {list.length}
        <TableContainer component={Paper}>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell align="center">클럽</TableCell>
                <TableCell align="center">소개글</TableCell>
                <TableCell align="center">가입날짜</TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {list.map((key, value) => (
                <TableRow>
                  <TableCell>{key}</TableCell>
                  <TableCell>{value}</TableCell>
                </TableRow>
              ))}

            </TableBody>
          </Table>
        </TableContainer>
      </>
    );
  }
}

export default ClubTableView;
