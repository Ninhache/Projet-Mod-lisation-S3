const express = require('express');
const router = express.Router();
const Model = require('../services/Model');

router.get('/', async function(req, res, next) {
    try {
        res.json(await Model.getModel(req.query.name));
    } catch (err) {
        console.error(`Error while getting Model `, err.message);
        next(err);
    }
});

module.exports = router;
