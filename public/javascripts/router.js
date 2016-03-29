/**
 * Created by kuulart on 16.10.3.
 */
/*global define*/
define([
    'jquery',
    'backbone',
    'views/home',
    'views/equipment'
], function ($, Backbone, Home, Equipment) {
    'use strict';

    var Router = Backbone.Router.extend({
        routes: {
            "home":                 "home",    // #help
            "equipment":            "equipment",     // add CLients
            "search/:query":        "search",  // #search/kiwis
            "search/:query/p:page": "search"   // #search/kiwis/p7
        },

        home: function () {
            // Initialize the application view
            var view = new Home();
            view.render();
        },

        equipment: function () {
            var view = new Equipment();
            view.render();
        },
    });

    return Router;
});
