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
    'text!templates/equipment.html',
    'serialize',
    'models/equipment',
    'collections/equipments',
    'bootstrap',
    'views/equipmentTable',
    'views/addEquipment'
], function ($, _, Backbone, template, serialize, Equipment, Equipments, Bootstrap, EquipmentTableView, AddEquipment) {
    'use strict';

    // Our overall **AppView** is the top-level piece of UI.
    var EquipmentView = Backbone.View.extend({
        // Instead of generating a new element, bind to the existing skeleton of
        // the App already present in the HTML.
        el: '#clientapp',

        equipments: null,

        // Compile our stats template
        template: _.template(template),
        events: {
            'click #addBtn':		'addEquipment'
        },

        // Delegated events for creating new items, and clearing completed ones.

        // At initialization we bind to the relevant events on the `Todos`
        // collection, when items are added or changed. Kick things off by
        // loading any preexisting todos that might be saved in *localStorage*.

        initialize: function () {

            this.equipments = new Equipments();
            //this.listenTo(Backbone, 'client:add', this.clientView);
            this.listenTo(this.equipments, "add", this.EquipmentTableView);
            //this.listenTo(this.clients, 'change', this.clientView)
            // _.bindAll(this, "addView");
            _.bindAll(this, "EquipmentTableView");
            this.render();
        },

        addEquipment: function () {
            var view = new AddEquipment({collection: this.equipments});
            view.render();
            this.$("#addEquipment").html(view.el);
        },

        EquipmentTableView: function (model) {
            //console.log("clientView", model);
            var view = new EquipmentTableView({model: model, collection: this.equipments});
            view.render();
            this.$(".equipment").append(view.el);
        },

        // Re-rendering the App just means refreshing the statistics -- the rest
        // of the app doesn't change.
        render: function () {
            this.$el.html(this.template());
            this.addEquipment();
            this.equipments.fetch();
            return this;
        }
    });
    return EquipmentView;
});
