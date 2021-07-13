import React from 'react';
import { Provider } from 'mobx-react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import clubStore from './service/ClubStore';

ReactDOM.render(
  <Provider clubStore = {new clubStore()}>
    <App />
  </Provider>,
  document.getElementById('root')
);

reportWebVitals();
