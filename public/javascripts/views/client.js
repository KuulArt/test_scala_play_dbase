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
    'text!templates/client.html',
    'serialize',
    'models/client',
    'collections/clients'
], function ($, _, Backbone, template, serialize, Client, Clients) {
    'use strict';

    // Our overall **AppView** is the top-level piece of UI.
    var ClientView = Backbone.View.extend({

        tagName: "li",
        // Compile our stats template
        template: _.template(template),

        // Delegated events for creating new items, and clearing completed ones.
        events: {
            'click .delete':		'deleteRec',
        },

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.
        initialize: function (opts) {
            if (opts.model) {
                this.model = opts.model;
            }
            _.bindAll(this, "deleteRec");
        },

        deleteRec: function (){
            this.model.destroy();
            this.remove();
        },
        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        render: function () {
            this.$el.html(this.template({client: this.model.toJSON()}));

        }
    });

    return ClientView;
});