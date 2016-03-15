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

        // Compile our stats template
        template: _.template(template),

        // Delegated events for creating new items, and clearing completed ones.

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.

        initialize: function () {
            this.render();
            this.eventBus = _.extend({}, Backbone.Events);
            //this.eventBus.listenTo(this.eventBus, 'entry:add', this.clientView);
        },
        
        addView: function () {
            var view = new AddView({
                eventBus : this.eventBus
            });
            view.render();
            this.$(".home").append(view.el);
        },

        clientView: function (){
            var view = new ClientView({
                eventBus : this.eventBus
            });
            view.render();
            this.$(".home").append(view.el);
        },

        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        render: function () {
            this.$el.html(this.template());
            this.addView();
            this.clientView();
            return this;
        }
    });

    return HomeView;
});