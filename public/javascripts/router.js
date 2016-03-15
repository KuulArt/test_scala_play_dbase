/**
 * Created by kuulart on 16.10.3.
 */
/*global define*/
define([
    'jquery',
    'backbone',
    'views/home',
    'views/add'
], function ($, Backbone, Home, AddView) {
    'use strict';

    var Router = Backbone.Router.extend({
        routes: {
            "home":                 "home",    // #help
            "add":                  "add",     // add CLients
            "search/:query":        "search",  // #search/kiwis
            "search/:query/p:page": "search"   // #search/kiwis/p7
        },

        home: function () {
            // Initialize the application view
            var view = new Home();
            view.render();
        },

        add: function () {
            var view = new AddView();
            view.render();
        },
    });

    return Router;
});