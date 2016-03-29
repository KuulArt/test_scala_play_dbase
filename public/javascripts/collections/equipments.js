/**
 * Created by kuulart on 16.15.3.
 */
/**
 * Created by kuulart on 16.15.3.
 */
/*global define*/
define([
    'underscore',
    'backbone',
    'models/equipment'
], function (_, Backbone, Equipment) {
    'use strict';

    var Equipments = Backbone.Collection.extend({
        url: '/equipments',
        model: Equipment
    });


    return Equipments;
});
