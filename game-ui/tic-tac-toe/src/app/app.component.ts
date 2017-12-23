import { BoardComponent } from './board/board.component';
import { Component } from '@angular/core';
import { RemoteService } from './remote.service';

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

  boardClicked(position: number): void {
    this.board.placeMark('O', position)
    this.remoteService.getNextState(this.board.getCurrentState())
                      .subscribe(
                          nextState => this.board.updateState(nextState),
                          error => console.log('error in invocation - ' + error)
                      )
  }

}

