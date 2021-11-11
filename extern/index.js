const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const port = 3000;
const dModelRouter = require('./routes/3dModel');
const ModelRouter = require('./routes/Model');



app.use(bodyParser.json());
app.use(
    bodyParser.urlencoded({
        extended: true,
    })
);

app.get('/', (req, res) => {
    res.json({'message': 'ok'});
})

app.use('/3dModel', dModelRouter);
app.use('/Model', ModelRouter);

app.use((err, req, res, next) => {
    const statusCode = err.statusCode || 500;
    console.error(err.message, err.stack);
    res.status(statusCode).json({'message': err.message});


    return;
});

app.listen(port, () => {
    console.log(`Look http://localhost:${port}`)
});
