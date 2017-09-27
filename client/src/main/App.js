import React, {Component} from 'react';
import '../App.css';

class App extends Component {
  constructor() {
    super()
      this.state = {
        addSpaceFormActive: false
      }

  }

  handleAddSpace() {

  }

  render() {
    return (
      <div className="App">
        <button>Add Space</button>
      </div>
    );
  }
}

export default App;
