import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'mobx-react';
import { BrowserRouter } from 'react-router-dom';
import clubStore from '../src/stores/ClubStore';

import App from './App';

ReactDOM.render(
	<BrowserRouter>
		<Provider clubStore={clubStore}>
			<App />
		</Provider>
	</BrowserRouter>,
	document.getElementById('root'),
);
