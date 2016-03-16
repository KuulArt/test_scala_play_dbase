/**
 * Created by kuulart on 16.15.3.
 */
/**
 * Created by kuulart on 16.10.3.
 */
/**
 * Created by kuulart on 16.10.3.
 */
/*global define*/
define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/home.html',
    'serialize',
    'models/client',
    'collections/clients',
    'views/add',
    'views/client'
], function ($, _, Backbone, template, serialize, Client, Clients, AddView, ClientView) {
    'use strict';

    // Our overall **AppView** is the top-level piece of UI.
    var HomeView = Backbone.View.extend({
        // Instead of generating a new element, bind to the existing skeleton of
        // the App already present in the HTML.
        el: '#clientapp',

        clients: null,

        // Compile our stats template
        template: _.template(template),

        // Delegated events for creating new items, and clearing completed ones.

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.

        initialize: function () {

            this.clients = new Clients();
            //this.listenTo(Backbone, 'client:add', this.clientView);
            this.listenTo(this.clients, "add", this.clientView);
            //this.listenTo(this.clients, 'change', this.clientView)
            _.bindAll(this, "addView");
            _.bindAll(this, "clientView");
            this.render();
        },

        addView: function () {
            var view = new AddView({collection: this.clients});
            view.render();
            this.$(".home").append(view.el);
        },

        clientView: function (model) {
            //console.log("clientView", model);
            var view = new ClientView({model: model, collection: this.clients});
            view.render();
            this.$(".home").append(view.el);
        },

        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        render: function () {
            this.$el.html(this.template());
            this.addView();
            this.clients.fetch();
            return this;
        }
    });
    return HomeView;
});