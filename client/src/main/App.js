import React, {Component} from 'react';
import '../App.css';
class App extends Component {
    constructor() {
        super()
        this.state = {
            addSpaceFormActive: false
        }
        this.handleAddSpaceForm = this.handleAddSpaceForm.bind(this)
    }
    handleAddSpaceForm() {
        this.setState({
            addSpaceFormActive: !this.state.addSpaceFormActive
        })
    }
    handleCreateSpace(formData) {
        Event.preventDefault()
        let data = {
            name: formData.target.Name.value,
            memory: parseInt(formData.target.Memory.value),
            disk: parseInt(formData.target.Disk.value)
        }
        fetch('localhost:8080/spaces', {
            method: "POST",
            body: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json"
            }
        }).then((response) => alert(response))
    }
    get addSpaceForm() {
        if(this.state.addSpaceFormActive) {
            return(
                <form onSubmit={this.handleCreateSpace}>
                    <label>Name</label>
                    <input name="Name" />
                    <label>Memory</label>
                    <input name="Memory" />
                    <label>Disk</label>
                    <input name="Disk" />
                    <button>Create</button>
                </form>
            )
        } else {
            return null
        }
    }
    render() {
        return (
            <div className="App">
                {this.addSpaceForm}
                <button className={"addSpaceButton"} onClick={this.handleAddSpaceForm}>Add Space</button>
            </div>
        );
    }
}
export default App;