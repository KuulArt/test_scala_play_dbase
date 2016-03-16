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
    'views/edit'
], function ($, _, Backbone, template, serialize, Client, Clients, EditView) {
    'use strict';

    // Our overall **AppView** is the top-level piece of UI.
    var ClientView = Backbone.View.extend({

        tagName: "li",
        // Compile our stats template
        template: _.template(template),

        // Delegated events for creating new items, and clearing completed ones.
        events: {
            'click .delete':		'deleteRec'
            //'click .edit':          'editRec'
        },

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.
        initialize: function (opts) {
            if (opts.model) {
                this.model = opts.model;
            }
            this.listenTo(this.model, 'change', this.render);
            _.bindAll(this, "deleteRec");
        },

        deleteRec: function (){
            this.model.destroy();
            this.remove();
        },

        //editRec: function () {
        //    var view = new EditView(this.model.toJSON());
        //    view.render().showModal();
        //},
        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        //{client: this.model}
        render: function () {
            this.$el.html(this.template({client: this.model.toJSON}));
            return this;
        }
    });

    return ClientView;
});