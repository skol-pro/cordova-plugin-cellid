var exec = require('cordova/exec');

exports.getCellID = function(success, error) {
    exec(success, error, 'CellIDPlugin', 'getCellID', []);
};
