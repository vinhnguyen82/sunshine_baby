import React from 'react';
import ReactDOM from 'react-dom';
import App from '../main/App';
import { shallow } from 'enzyme';


it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App/>, div);
});

describe('handleAddSpace', () => {
    let app

    beforeAll(() => {
        app = shallow(<App/>)
    })


    it('updates the state of AddSpaceFormActiveToTrue when it is false', () => {
        app.instance().handleAddSpaceForm()

        expect(app.state().addSpaceFormActive).toEqual(true)
    })
})