const db = require('./db');
const helper = require('../helper');
const config = require('../config');
const fs = require("fs");
const util = require('util');

async function getModel(mname){



    if(mname != null) {
        const rows = await db.query(
            `SELECT id, name, link, imglink FROM files WHERE name = ?`,
            [mname]
        );
        if(rows.length==1){
            let contents = await readF(rows[0].link);
            return {data: {contents}, img:rows[0].imglink};
        }
    }
}

async function readF(filepath){
    const readFileContent = util.promisify(fs.readFile);
    const fetchFile = async (name) => {

        const buff = await readFileContent(filepath)

        return buff.toString()
    }
    return fetchFile(filepath);
}
module.exports = {
    getModel
}
