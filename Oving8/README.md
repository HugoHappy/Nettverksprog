# Example WebSocket application

## Prerequisites
install `Node.js`, `npm` and `git`.

## Clone repository
```sh
git clone https://github.com/ntnu-tdat2004/websocket-example
cd websocket-example
```

## Install dependencies
* Install global npm packages:
  ```sh
  npm install -g nodemon
  ```
  If you receive an error, try add `sudo` before the above command.

* Install JavaScript dependencies (settings file: package.json):
  ```sh
  cd server
  npm install
  cd ..
  ```

## Build and run
* Run node server with newest JS standard (harmony) enabled:
  ```sh
  cd server
  nodemon --harmony server.js
  ```
  `nodemon` ensures that the server restarts when the source files have been changed.

* Open http://localhost:3000
