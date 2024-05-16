import logo from "../logo.svg";
import "../App.css";
import { useState, useEffect } from "react";

import { Button } from "@mui/material";
function App() {
  const [data, setData] = useState([]);
  useEffect(() => {
    fetch("/hi")
      .then((res) => {
        return res.json();
      })
      .then(function (result) {
        setData(result);
      });
  }, []);
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <br />
        <ul>
          {data.map((v, index) => (
            <li key={`${index}-${v}`}>{v}</li>
          ))}
        </ul>
        <Button variant="contained" href="/writer">
          Move
        </Button>
      </header>
    </div>
  );
}

export default App;
