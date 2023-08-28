import React, {useEffect, useState} from 'react';
import logo from './logo.svg';

function App() {
  const [data, setData] = useState("");
  useEffect(() => {
    fetch("/api/hello").then(result => result.text()).then(
      text => {
      setData(text);
    }
    )
  }, [])
  return (
      <div>
        <p>{data}</p>
      </div>
  );
}

export default App;
