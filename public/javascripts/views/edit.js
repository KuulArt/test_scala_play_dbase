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

    //var EditView = ModalView.extend({
    //    template: _.template(template),
    //    events: {
    //        "submit form": "update"
    //    },
    //    render: function () {
    //        $(this.el).html( this.template());
    //
    //        return this;
    //    },
    //    initialize: function () {
    //        _.bindAll(this, "render");
    //
    //    },
    //    update: function (e) {
    //        e.preventDefault();
    //        var data = $(e.target).serializeObject();
    //
    //        console.log("test")
    //    }
    //
    //});
    //return EditView;
});