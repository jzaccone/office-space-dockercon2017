var express = require('express'),
    async = require('async'),
    mysql = require('mysql'),
    cookieParser = require('cookie-parser'),
    bodyParser = require('body-parser'),
    methodOverride = require('method-override'),
    app = express(),
    server = require('http').Server(app),
    io = require('socket.io')(server);

io.set('transports', ['polling']);

var port = process.env.PORT || 4000;

io.sockets.on('connection', function(socket) {

    socket.emit('message', {
        text: 'Welcome!'
    });

    socket.on('subscribe', function(data) {
        socket.join(data.channel);
    });
});

async.retry({
        times: 1000,
        interval: 1000
    },
    function(callback) {

        var client = mysql.createConnection({
            host: "database",
            user: "gordon",
            password: "password",
            database: "dockercon2035"
        });

        console.log('Connecting')
        client.connect(function(err) {
            if (err) {
                console.error("Waiting for db");
            }
            callback(err, client);
        });

    },
    function(err, client) {
        if (err) {
            return console.err("Giving up");
        }
        console.log("Connected to db");
        getVotes(client);
    }
);

function getVotes(client) {

    console.log('Querying')
    var queryText = 'SELECT * FROM account WHERE id=12345'

    client.query(queryText, function(error, result) {
        if (error) {
            console.log(error)
        } else {

            console.log("JZJZ result is" + JSON.stringify(result))
            io.sockets.emit("account", JSON.stringify(result[0]))
        }

    setTimeout(function() {getVotes(client) }, 1000);
    });

}

function getBalanceResult(result) {
    var votes = {
        a: 0,
        b: 0
    };

    result.rows.forEach(function(row) {
        votes[row.vote] = parseInt(row.count);
    });

    return votes;
}

app.use(cookieParser());
app.use(bodyParser());
app.use(methodOverride('X-HTTP-Method-Override'));
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
    next();
});

app.use(express.static(__dirname + '/views'));

app.get('/', function(req, res) {
    res.sendFile(path.resolve(__dirname + '/views/index.html'));
});

server.listen(port, function() {
    var port = server.address().port;
    console.log('App running on port ' + port);
});