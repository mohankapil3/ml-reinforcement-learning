import { BoardComponent } from './board/board.component';
import { Component } from '@angular/core';
import { RemoteService } from './remote.service';
import { IState, State } from './board/board.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [RemoteService],
})
export class AppComponent {

  constructor (private remoteService: RemoteService) {}

  title = 'Tic Tac Toe';
  board = new BoardComponent();
  boardDimension = Math.round(Math.sqrt(this.board.size));
  rows = Array.from(Array(this.boardDimension).keys());
  columns = Array.from(Array(this.boardDimension).keys());
  gameStatus = 'In Play'

  boardClicked(position: number): void {
    if (this.board.isPositionEmpty(position) && !this.board.isOver()) {
      this.board.placeMark('O', position)
      if (this.board.isOver()) {
        this.updateGameStatus();
      } else {
        this.remoteService
            .getNextState(this.board.getCurrentState())
            .subscribe(
                nextState => this.updateState(nextState),
                error => console.log('error in invocation - ' + error)
            )
      }
    }
  }

  startAgain() {
    this.board.reset();
    this.gameStatus = 'In Play'
  }

  private updateState(state: IState) {
    this.board.updateState(state);
    this.updateGameStatus();
  }

  private updateGameStatus() {
    if (this.board.isWon()) {
      this.gameStatus = 'Game Finished'
      // report machine loss to game engine
      if (this.board.whoHasWon() === 'O') {
        this.remoteService
            .reportLoss(this.board.getCurrentState())
            .subscribe(
                states => console.log('catelogue of lost states - ' + states),
                error => console.log('error in invocation - ' + error)
             )
      }
    } else if (this.board.isDraw()) {
      this.gameStatus = 'Game Draw'
    }
  }

}

