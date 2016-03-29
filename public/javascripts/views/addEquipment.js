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
    'text!templates/addEquipment.html',
    'serialize',
    'models/equipment',
    'collections/equipments'
], function ($, _, Backbone, template, serialize, Equipment, Equipments) {
    'use strict';

    // Our overall **AppView** is the top-level piece of UI.
    var AddEquipmentView = Backbone.View.extend({

        // Instead of generating a new element, bind to the existing skeleton of
        // the App already present in the HTML.
        // el: '.home',

        // Compile our stats template
        template: _.template(template),

        // Delegated events for creating new items, and clearing completed ones.
        events: {
            'submit #addEquip':		'add'
        },

        initialize: function (opts) {
            this.equipments = opts.collection;
            //this.listenTo(this.clients, "add", this.renderTrigger);
            _.bindAll(this, "add");
        },

        //renderTrigger: function (model) {
        //  // ...
        //    Backbone.trigger('client:add', {model: model});
        //},

        add: function (e) {
            e.preventDefault();
            var data = $(e.target).serializeObject();
            //var addData = new Client;
            //addData.save(data);

            var equipments = this.equipments;
            var equipment = new Equipment();
            equipment.save(data, {
                success: function (model) {
                    console.log("success:", arguments);
                    equipments.add(model);
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
            //this.clients.fetch();
            return this;
        }
    });

    return AddEquipmentView;
});
