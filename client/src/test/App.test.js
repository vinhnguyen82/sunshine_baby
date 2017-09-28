import React from 'react';
import ReactDOM from 'react-dom';
import App from '../main/App';
import {shallow, mount} from 'enzyme';
it('renders without crashing', () => {
    const div = document.createElement('div')
    ReactDOM.render(<App/>, div);
});
describe('handleAddSpace', () => {
    let app
    beforeAll(() => {
        app = shallow(<App/>)
    })
    beforeEach(() => {
        app.state().addSpaceFormActive = false
    })
    it('updates the state of AddSpaceFormActive to true when it is false', () => {
        app.instance().handleAddSpaceForm()
        expect(app.state().addSpaceFormActive).toEqual(true)
    })
    it('updates the state of AddSpaceFormActive to true when it is false', () => {
        app.find('.addSpaceButton').simulate('click')
        expect(app.state().addSpaceFormActive).toEqual(true)
    })
})
