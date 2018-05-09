import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IState, State, IStates } from './board/board.component';

@Injectable()
export class RemoteService {

  private GAME_ENGINE_URL_FOR_NEXT_STATE = 'http://localhost:8080/tic-tac-toe/next-state';
  private GAME_ENGINE_URL_FOR_REPORTING_LOSS = 'http://localhost:8080/tic-tac-toe/report-loss';

  constructor (private http: Http) {}

  getNextState(state: State): Observable<IState> {
      let headers = new Headers({'Content-Type': 'application/json'});
      let options = new RequestOptions({headers});
      return this.http.post(this.GAME_ENGINE_URL_FOR_NEXT_STATE, state, options)
            .map(this.parseData)
            .catch(this.handleError);
  }

  reportLoss(state: State): Observable<IStates> {
      let headers = new Headers({'Content-Type': 'application/json'});
      let options = new RequestOptions({headers});
      return this.http.post(this.GAME_ENGINE_URL_FOR_REPORTING_LOSS, state, options)
            .map(this.parseData)
            .catch(this.handleError);
  }

  private parseData(res: Response)  {
      return res.json() || [];
  }

  private handleError(error: Response | any) {
      return error.message ? error.message : error.toString();
  }

}
