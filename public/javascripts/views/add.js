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
    'text!templates/add.html',
    'serialize',
    'models/client',
    'collections/clients',
    'views/client'
], function ($, _, Backbone, template, serialize, Client, Clients, ClientView) {
    'use strict';

    // Our overall **AppView** is the top-level piece of UI.
    var AddView = Backbone.View.extend({

        clients: null,

        // Instead of generating a new element, bind to the existing skeleton of
        // the App already present in the HTML.
        el: '#clientapp',

        // Compile our stats template
        template: _.template(template),

        // Delegated events for creating new items, and clearing completed ones.
        events: {
            'submit .submitAdd':		'add',
        },

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.
        initialize: function () {
            this.clients = new Clients();
            this.listenTo(this.clients, "add", this.renderClient);
        },

        renderClient: function (model) {
          // ...
            var view = new ClientView({model: model});
            view.render();
            this.$("ul").append(view.el);
        },



        add: function (e) {
            e.preventDefault();
            var data = $(e.target).serializeObject();
            //var addData = new Client;
            //addData.save(data);

            var clients = this.clients;

            var client = new Client();
            client.save(data, {
                success: function (model) {
                    console.log("success:", arguments)
                    clients.add(model);
                },
                error: function () {
                    console.log("error:", arguments)
                }
            });
            //console.log(client) ;

        },

        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        render: function () {
            this.$el.html(this.template());
            this.clients.fetch();

        }
    });

    return AddView;
});