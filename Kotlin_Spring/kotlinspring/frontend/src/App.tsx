import React, {useEffect, useState} from 'react';
import logo from './logo.svg';

function App() {

  // auth form logic
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isLogin, setIsLogin] = useState(false);

const onEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
  const {target: {value}} = event;
  setEmail(value);
}

  const onPasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {target: {value}} = event;
    setPassword(value);
  }

  const onLogin = async (event: React.MouseEvent) => {
    event.preventDefault();
    const result = await fetch("/api/auth/signin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password,
      })
    });
    if (result.ok) setIsLogin(true);
  }

  const onSignUp = async (event: React.FormEvent) => {
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
    });
  }

  //todo form logic
  const [todo, setTodo] = useState("");
  const [todoList, setTodoList] = useState<string[]>([]);
  const onTodoChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {target: {value}} = event;
    setTodo(value);
  }

  const onTodoSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    const result = await fetch("/api/todo", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        createdBy: email,
        todo: todo,
      })
    });
    if (result.ok) {
      const response = await result.json()
      console.log(response)

      setTodoList(before => ([...before, todo]));
      setTodo("");
    }
  }

  return (
      <div>
        <form onSubmit={onSignUp}>
          <div>
            <label htmlFor="FormEmailInput">Email</label>
            <input id="FormEmailInput"
                   type="text"
                   placeholder="Email adress"
                   value={email}
                   onChange={onEmailChange}
                   disabled={isLogin}
                   required/>
          </div>
          <div>
            <label htmlFor="FormPasswordInput">Password</label>
            <input id="FormPasswordInput"
                   type="password"
                   placeholder="Password"
                   value={password}
                   onChange={onPasswordChange}
                   disabled={isLogin}
                   required/>
          </div>
          <button type="button" onClick={onLogin}>Sign In</button>
          <button type="submit">Sign Up</button>
        </form>
        {isLogin &&
            <form onSubmit={onTodoSubmit}>
              <div>
                <label htmlFor="FormTodoInput">Todo</label>
                <input id="FormTodoInput"
                       type="text"
                       placeholder="Todo Content"
                       value={todo}
                       onChange={onTodoChange}
                       required/>
              </div>
              <button type="submit">upload</button>
            </form>
        }
      </div>
  );
}

export default App;
