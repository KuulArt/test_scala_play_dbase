/**
 * Created by kuulart on 16.10.3.
 */
/*global define*/
define([
    'jquery',
    'backbone',
    'views/userclient',
    'views/add'
], function ($, Backbone, UserClientView, AddView) {
    'use strict';

    var Router = Backbone.Router.extend({
        routes: {
            "home":                 "home",    // #help
            "add":                  "add",      // add CLients
            "search/:query":        "search",  // #search/kiwis
            "search/:query/p:page": "search"   // #search/kiwis/p7
        },

        home: function () {
            // Initialize the application view
            var view = new UserClientView();
            view.render();
        },

        add: function () {
            var view = new AddView();
            view.render();
        },

        setFilter: function (param) {
            // Set the current filter to be used
            //Common.TodoFilter = param || '';
            //
            //// Trigger a collection filter event, causing hiding/unhiding
            //// of the Todo view items
            //Todos.trigger('filter');
        }
    });

    return Router;
});