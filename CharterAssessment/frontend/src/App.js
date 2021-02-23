import React from 'react';
import './App.css';

export default class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            CustomerJson: null,
            Time : null
        };
    }


    componentDidMount() {
        this.CustomerInfo();
    }

    CustomerInfo() {
        fetch('/api/CustomerJson')
            .then(response => response.text())
            .then(jsonRes => {
                
                var test = JSON.parse(jsonRes).map((item) => {
                    return <tr key={item.id}>
                            <td>{`${item['name'].substring(0,item.name.indexOf(' '))}`}</td>
                            <td>{`${item['name'].substring(item.name.indexOf(' '))}`}</td>
                            <td>{`${this.getDateString(item['date'])}`}</td>
                            <td>{`$${item['price']}`}</td>
                            <td>{`${item['rewards']}`}</td>
                        </tr>;

                });

                console.log(test);
                this.setState({CustomerJson : test});
            });
    };

    getDateString(date) {
        const monthNames = ["January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"];
        var d = new Date(date);
        return `${monthNames[d.getMonth()].substring(0,3)}. ${d.getDate()}, ${d.getFullYear()}`
    }


    render() {

        return (
            <div className="App">
                <header className="App-header">
                    <div class="headerName"><h5> Andrew Tant</h5></div>
                    <div className="headerTitle"><h3>Rewards Calculator</h3></div>
                </header>
                <p>Refresh the Page to Generate New Dates and Prices</p>
                <table>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Date</th>
                        <th>Price</th>
                        <th>Rewards Points</th>
                    </tr>
                    {this.state.CustomerJson}
                </table>
            </div>
        );
    }
}
