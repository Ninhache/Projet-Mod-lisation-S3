const express = require('express');

const fs = require('fs');

const api = express();
const cors = require('cors')

api.use(express.static(__dirname + "/public"));

api.post('/add', (req, res) => {
    console.log("post request received")
})

console.log(api._router.stack)

api.listen(3000, () => {
    console.log("API up and running")
})

api.get('/', (req, res) => {
    res.send("Hellow world !" + req);
})

var x = ["tmp.ply", "hello.ply", "uwu.ply"]; // remplacer par la co à la bdd

api.get('/list', (req, res) => {
    var tmp = "";
    x.forEach(x => tmp += x );
    res.send("Voici la liste :" + tmp);
    
})

api.get('/search', (req, res) => {

    if(req.query.model != null) {
        
        fs.readFile("./IdeaProjects/projetmodeg5/src/main/resources/" + req.query.model+ ".ply", 'utf8' , (err, data) => {
            if (err) {
            res.send("Modele non dispo");
            return
            }
            res.send({name:req.query.model, file:data})
        })
        
    }
})
