const express = require('express');
const router = express.Router();
const dModel = require('../services/3dModel');

router.get('/', async function(req, res, next) {
    try {
        res.json(await dModel.getMultiple(req.query.page));
    } catch (err) {
        console.error(`Error while getting 3dModel `, err.message);
        next(err);
    }
});

module.exports = router;
