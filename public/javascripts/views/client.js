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
    'collections/clients',
    'views/edit',
    'bootstrap'
], function ($, _, Backbone, template, serialize, Client, Clients, EditView, Bootstrap) {
    'use strict';

    // Our overall **AppView** is the top-level piece of UI.
    var ClientView = Backbone.View.extend({

        tagName: "tr",
        // Compile our stats template
        template: _.template(template),

        // Delegated events for creating new items, and clearing completed ones.
        events: {
            'click .delete':		'deleteRec',
            'click .edit':          'editRec'
        },

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.
        initialize: function (opts) {
            if (opts.model) {
                this.model = opts.model;
            }
            if (opts.collection) {
                this.collection = opts.collection;
            }
            //this.client = opts.client;
            this.listenTo(this.model, 'change', this.render);
            _.bindAll(this, "deleteRec");
            _.bindAll(this, "render");
        },

        deleteRec: function (){
            this.model.destroy();
            this.remove();
        },

        editRec: function () {
            var view = new EditView({model: this.model, collection: this.collection});
            view.render();
            // this.$(".editModal").append(view.el);
        },
        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        //{client: this.model}
        render: function () {
            //console.log("ClientView.render", {client: this.model.toJSON()})
            this.$el.html(this.template({client: this.model.toJSON()}));
            return this;
        }
    });

    return ClientView;
});
