/**
 * Created by kuulart on 16.15.3.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/edit.html',
    'serialize',
    'models/client',
    'collections/clients',
    'helpers/modalView'
], function ($, _, Backbone, template, serialize, Client, Clients, ModalView) {
    'use strict';

    var EditView = Backbone.View.extend({
        template: _.template(template),
        el: '.editModal',
        events: {
            "submit form": "update"
        },
        render: function () {
            $(this.el).html( this.template());
            return this;
        },
        initialize: function (opts) {
            if (opts){
                this.model = opts.model;
                this.collection = opts.collection;
            }
            _.bindAll(this, "render");

        },
        update: function (e) {
            e.preventDefault();
            var data = $(e.target).serializeObject();
            console.log(data);
            var clients = this.collection;
            var client = this.model;

            client.save(data, {
                success: function (model) {
                    console.log("Update successful:", arguments);
                    clients.update(model);
                },
                error: function () {
                    console.log("Update error:", arguments)
                }

            //console.log("test")
        });
        },

    });
    return EditView;
});
