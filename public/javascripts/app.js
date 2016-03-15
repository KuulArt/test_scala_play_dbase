/**
 * Created by kuulart on 16.10.3.
 */
/*global require*/
'use strict';

// Require.js allows us to configure shortcut alias
require.config({
    paths: {
        jquery: '../lib/jquery/dist/jquery',
        underscore: '../lib/underscore/underscore',
        backbone: '../lib/backbone/backbone',
        text: '../lib/text/text',
        serialize: 'helpers/serialize'
    },
    // The shim config allows us to configure dependencies for
    // scripts that do not call define() to register a module
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        },
        serialize: ["jquery"]
    }
});

require([
    'backbone',
    'views/userclient',
    'router'
], function (Backbone, UserClientView, Router) {
    /*jshint nonew:false*/
    // Initialize routing and start Backbone.history()
    new Router();
    Backbone.history.start();
});