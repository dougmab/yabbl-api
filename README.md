
# Yabbl API

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


An API made for [Yabbl](https://github.com/dougmab/yabbl-api)




## Features

- User authentication
- Websocket authentication
- Websocket message broadcasting
- Room access and creation


## Todo

- [ ] Add exception handling
- [ ] ADMIN privileges
- [ ] Room moderation
- [ ] Message persistence
- [ ] Invite creation
- [ ] Friend system
- [ ] User blocking
## Run Locally

Clone the project

```bash
  git clone https://github.com/dougmab/yabbl-api
```

Go to the project directory

```bash
  cd yabbl
```

Build the package

you must have a private and public RSA key (**app.key** and **app.pub**) at "src/main/resources", check out [this article](https://www.scottbrady91.com/openssl/creating-rsa-keys-using-openssl) to see how you do it

```bash
./mvnw clean package
```

Start the server

```bash
java -jar ./target/yabbl-[version].jar
```

There isn't a production build yet, but it'll be implemented soon


## Environment Variables

To run this project, you will need to add the following environment variable

`CLIENT_URL` - URL of your client

There is no .env loading yet, but you can define this variable like this

```bash
CLIENT_URL=http://localhost:5173 java -jar ./target/yabbl-[version].jar
```

## Client

Your can check the client application repository [here](https://github.com/dougmab/yabbl).
## Tech Stack

**Client:** Vite, React, TailwindCSS

**Server:** Java, Spring

