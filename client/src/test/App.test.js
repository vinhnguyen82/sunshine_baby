import React from 'react';
import ReactDOM from 'react-dom';
import App from '../main/App';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
});

describe('handleAddSpace', () => {
  it('updates the state of AddSpaceFormActiveToTrue when it is false', () => {
      const div = document.createElement('div');
      const app = ReactDOM.render(<App />, div);

      app.instance().handleAddSpaceForm()
      expect(app.state().addSpaceFormActive).toEqual(true)
  })
})