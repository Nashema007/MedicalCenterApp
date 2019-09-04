const express = require('express')
const bodyParser = require('body-parser')
const Chatkit = require('@pusher/chatkit-server')

const app = express()
const chatkit = new Chatkit.default({
  key: 'f5a18c11-297e-4020-bdc8-419e61801392:owsOcCXmMl9hoiw5BA58GhlU0ysS17hRtpx3TCJ9p9g=',
  instanceLocator: 'v1:us1:87b7072f-7203-4cf8-8c66-ae5faf2261e4'
})

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({
  extended: false
}))

app.get('/users', (req, res) => {
  const username = req.query.username

  chatkit.createUser({
    id: username,
    name: username
  })
    .then(r => res.json({ username }))
    .catch(e => res.json({
      error: e.error_description,
      type: e.error_type
    }))
})

app.post('/auth', (req, res) => {
  // const userId = req.query.user_id
  const authData = chatkit.authenticate({
    userId: req.query.user_id
  })
  res.status(authData.status)
    .send(authData.body)

//   res.json(chatkit.authenticate({
//     userId: userId
//   }))
})

app.get('/', (req, res, next) => {
  res.json('Working!')
})
const PORT = process.env.PORT | 3000
app.listen(PORT, () => console.log(`Running application... at port ${PORT}`))
