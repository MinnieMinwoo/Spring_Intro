import React, {useEffect, useState} from 'react';
import logo from './logo.svg';

function App() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

const onEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
  console.log(event.target);
  const {target: {value}} = event;
  setEmail(value);
}

  const onPasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {target: {value}} = event;
    setPassword(value);
  }

  const onLogin = async (event: React.FormEvent) => {
    event.preventDefault();
    await fetch("/api/auth/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password,
      })
    })
  }

  return (
      <div>
        <form onSubmit={onLogin}>
          <div>
            <label htmlFor="FormEmailInput">Email</label>
            <input id="FormEmailInput"
                   type="text"
                   placeholder="Email adress"
                   value={email}
                   onChange={onEmailChange}
                   required/>
          </div>
          <div>
            <label htmlFor="FormPasswordInput">Password</label>
            <input id="FormPasswordInput"
                   type="password"
                   placeholder="Password"
                   value={password}
                   onChange={onPasswordChange}
                   required/>
          </div>
          <button type="submit">Sign In</button>
          <button type="submit">Sign Up</button>
        </form>
      </div>
  );
}

export default App;
